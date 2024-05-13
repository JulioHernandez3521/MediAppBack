package com.mitocode.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.model.IConsultProjectionDTO;
import com.mitocode.model.MediaFile;
import com.mitocode.service.IConsultService;
import com.mitocode.service.IMediaFileService;
import com.mitocode.service.dto.ConsultDTO;
import com.mitocode.service.dto.ConsultListExamDTO;
import com.mitocode.service.dto.ConsultProcDTO;
import com.mitocode.service.dto.FiltersCosultDTO;
import com.mitocode.service.mappers.ConsultMapper;
import com.mitocode.service.mappers.ExamMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final IConsultService service;
    private final ConsultMapper mapper;
    private final ExamMapper examMapper;
    private final Cloudinary cloudinary;
    private final IMediaFileService mediaFileService;

    private final Logger log = LoggerFactory.getLogger(ConsultController.class);
    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll(){
        return ResponseEntity.ok(this.mapper.toDtoList(this.service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.mapper.toDto(service.findById(id)));
    }

    /*@PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultDTO dto){
        Consult obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();

        return ResponseEntity.created(location).build();
    }*/
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) throws Exception{
        Consult cons = this.mapper.toEntity(dto.getConsult());
        List<Exam> exams = this.examMapper.toEntityList(dto.getListExam());
        Consult obj = service.saveTransactional(cons, exams);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ConsultDTO dto){
        dto.setIdConsult(id);
        Consult obj = service.update(id, this.mapper.toEntity(dto));
        return ResponseEntity.ok(this.mapper.toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id){
        EntityModel<ConsultDTO> resource = EntityModel.of(this.mapper.toDto(service.findById(id)));
        //generar un link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
        resource.add(link1.withRel("consult-info-byId"));
        resource.add(link2.withRel("consult-all-info"));
        return resource;
    }
    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> findAllByExam(@RequestBody FiltersCosultDTO dto){
        return ResponseEntity.ok(this.mapper.toDtoList(this.service.search(dto.getDni(), dto.getFullName())));
    }
    @GetMapping("/search/dates")
    public ResponseEntity<List<ConsultDTO>> searchConsultByDate(@RequestParam(value = "date1", defaultValue = "2024-04-11") String date, @RequestParam(value = "date2", defaultValue = "2024-04-11") String date2){
        return  ResponseEntity.ok(this.mapper.toDtoList(this.service.searchbyDates(LocalDateTime.parse(date), LocalDateTime.parse(date2))));
    }

    @GetMapping("/callProcedureProjection")
    public ResponseEntity<List<IConsultProjectionDTO>> callProcedureProjection(){
        return ResponseEntity.ok(this.service.consultProjection());
    }
    @GetMapping("/callProcedureProjectionNative")
    public ResponseEntity<List<ConsultProcDTO>> callProcedureProjectionNative(){
        return ResponseEntity.ok(this.service.consultProjectionNative());
    }

//    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_PDF_VALUE)
    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReport() throws Exception{
        return ResponseEntity.ok(this.service.generateReport());
    }

    @PostMapping(value = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        File file = this.convertToFile(multipartFile);
        Map response = this.cloudinary.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
        JSONObject obj = new JSONObject(response);
        String url = obj.getString("url");
        log.info(url);
        return  ResponseEntity.ok().build();
    }

    @PostMapping(value = "/saveFileLocal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFileLocal(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        MediaFile file = new MediaFile();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFileType(multipartFile.getContentType());
        file.setContent(multipartFile.getBytes());
        this.mediaFileService.save(file);
        return  ResponseEntity.ok().build();
    }

    @GetMapping(value = "/readFile/{idFile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFileById(@PathVariable("idFile") Integer id){
        return ResponseEntity.ok(this.mediaFileService.findById(id).getContent());
    }

    public File convertToFile(MultipartFile multipartFile) {
        try {
            File file= new File(multipartFile.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
            return file;
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
