package com.mitocode.exception;

import java.time.LocalDateTime;

public record CustmoErroRecord(
        LocalDateTime dateTime,
        String message,
        String details
) {
}
