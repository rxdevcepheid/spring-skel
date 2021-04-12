package com.cepheid.cloud.skel.exceptions;

public abstract class BadRequestException extends Exception {


    public BadRequestException(String entityName, String requestType, String error) {
        super(" cannot perform " + requestType + " operation on " + entityName + " because " + error);
    }

}
