package com.mvc.model;

import lombok.Data;

@Data
public class ResultResponse {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
