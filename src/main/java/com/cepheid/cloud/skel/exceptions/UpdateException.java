package com.cepheid.cloud.skel.exceptions;

public class UpdateException extends BadRequestException{

    public UpdateException(String entityName) {
        super(entityName, "PUT", "idDoNotExist");
    }
}
