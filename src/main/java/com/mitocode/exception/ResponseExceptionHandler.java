package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {

//    @ExceptionHandler(ModelNotFounException.class)
//    public ResponseEntity<CustmErrorResponse> handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
//        CustmErrorResponse err = new CustmErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
//    }

//    /**
//     * Method with record jus work with java 14+
//     * @param ex
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(ModelNotFounException.class)
//    public ResponseEntity<CustmoErroRecord> handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
//        CustmoErroRecord err = new CustmoErroRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
//    }

//    /**
//     *
//     * @param ex
//     * @param request
//     * @return
//     */
////    Just spring boot 3
//    @ExceptionHandler(ModelNotFounException.class)
//    public ProblemDetail handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
//        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        pd.setTitle("Model not foun Exception");
//        pd.setType(URI.create(request.getDescription(false)));
//        pd.setProperty("val1",12);
//        pd.setProperty("val2",20);
//        return pd;
//    }

    @ExceptionHandler(ModelNotFounException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFounException ex, WebRequest request){
        return ErrorResponse.builder(ex,HttpStatus.NOT_FOUND,ex.getMessage())
                .title("")
                .type(URI.create(request.getDescription(false)))
                .property("21",232)
                .property("e",232)
                .build();
    }
}
