package com.mitocode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class CustmErrorResponse {
    private LocalDateTime dateTime;
    private String message;
    private String detail;
}
