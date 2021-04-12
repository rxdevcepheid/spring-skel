package com.cepheid.cloud.skel.exceptions;

/**
 * CreateException is thrown during create operation
 */
public class CreateException extends BadRequestException {

    public CreateException(String entityName) {
        super(entityName, "POST", "idexist");
    }
}
