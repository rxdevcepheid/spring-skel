package com.cepheid.cloud.skel.exceptions;

/**
 * parent class for all Request related exception
 */
public abstract class BadRequestException extends Exception {


    public BadRequestException(String entityName, String requestType, String error) {
        super(" cannot perform " + requestType + " operation on " + entityName + " because " + error);
    }

}
