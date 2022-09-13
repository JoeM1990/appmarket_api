package com.monkilatech.appmarket.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class BodyResponse<T> {

    @JsonInclude(Include.NON_EMPTY)
    private String status;
    @JsonInclude(Include.NON_EMPTY)
    private String message;
    @JsonInclude(Include.NON_EMPTY)
    private T data;
}
