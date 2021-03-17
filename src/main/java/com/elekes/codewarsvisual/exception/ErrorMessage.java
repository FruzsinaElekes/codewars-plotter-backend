package com.elekes.codewarsvisual.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String exceptionName;
    private String description;

    public ErrorMessage(Exception e) {
        this.exceptionName = e.getClass().getSimpleName();
        this.description = e.getMessage();
    }

}
