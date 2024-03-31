package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustmErrorResponse> handleException(
            Exception ex,
            WebRequest request){
        CustmErrorResponse err = new CustmErrorResponse(LocalDateTime.now(),
                                                        ex.getMessage(),
                                                        request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    /**
     *  Devuelve un json con la estructua del modelo CustomErrorResponse
     * @param ex Es la excepcion que se produce
     * @param request La request que produjo la excepcion
     * @return un json con el body de CustomErrorREsponse
     */
//    @ExceptionHandler(ModelNotFounException.class)
//    public ResponseEntity<CustmErrorResponse> handleModelNotFoundException(
//            ModelNotFounException ex,
//            WebRequest request){
//        CustmErrorResponse err = new CustmErrorResponse(LocalDateTime.now(),
//                                                        ex.getMessage(),
//                                                        request.getDescription(false));
//        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
//    }

    /**
     * Method with record jus work with java 14+
     * @param ex that exception produced in the request
     * @param request the request that produce the exception
     * @return el json with the data of error
     */
//    @ExceptionHandler(ModelNotFounException.class)
//    public ResponseEntity<CustmoErroRecord> handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
//        CustmoErroRecord err = new CustmoErroRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
//    }

    /**
     * this method use a Model ProblemDetail just work in spring boot 3 and spring framework 6
     * @param ex Is the exception produced in the request
     * @param request is the request from the user
     * @return a body like a model ProblemDetail
     */
////    Just spring boot 3
//    @ExceptionHandler(ModelNotFounException.class)
//    public ProblemDetail handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
//        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        pd.setTitle("Model not foun Exception");
//        pd.setType(URI.create(request.getDescription(false)));
//        pd.setProperty("val1",12);}// with setProperty we can add more properties
//        pd.setProperty("val2",20);
//        return pd;
//    }

    /**
     *  this method use a ErrorResponse that provides SpringBoot
     * @param ex is the exception produced by the request
     * @param request is the request from the user
     * @return return a object ErrorResponse structure
     */
    @ExceptionHandler(ModelNotFounException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
        return ErrorResponse.builder(ex,HttpStatus.NOT_FOUND,ex.getMessage())
                .title("")
                .type(URI.create(request.getDescription(false)))
                .property("Extra-Value","Something here")
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleModelNotFoundException(MethodArgumentNotValidException ex, WebRequest request){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>();
           fieldErrors.forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        return ErrorResponse.builder(ex,HttpStatus.BAD_REQUEST,ex.getMessage())
                .title("")
                .type(URI.create(request.getDescription(false)))
                .property("Errors:",errors)
                .build();
    }
}
