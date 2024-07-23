package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fileName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName,String fileName, Long fieldValue){

        super(String.format("%s Not found with %s: '%s'", resourceName, fileName, fieldValue));
        this.resourceName=resourceName;
        this.fileName = fileName;
        this. fieldValue = fieldValue;

    }
}
