package com.cepheid.cloud.skel.exceptions;

public class CreateException extends BadRequestException {

    public CreateException(String entityName) {
        super(entityName, "POST", "idexist");
    }
}
