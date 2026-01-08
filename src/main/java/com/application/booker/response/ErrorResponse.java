package com.application.booker.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String errorMsg;
    private Map<String,String> errorMap;

    public ErrorResponse(LocalDateTime timestamp,String errorMsg){
        this.timestamp = timestamp;
        this.errorMsg = errorMsg;
    }
}
