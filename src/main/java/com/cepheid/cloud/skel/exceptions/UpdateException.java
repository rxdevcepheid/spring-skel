package com.cepheid.cloud.skel.exceptions;

/**
 * UpdateException is thrown during update operation
 */
public class UpdateException extends BadRequestException{

    public UpdateException(String entityName) {
        super(entityName, "PUT", "idDoNotExist");
    }
}
