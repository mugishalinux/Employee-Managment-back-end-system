package com.example.EmployeeManagementSystem.exception;


import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyResourcesNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    private String fieldName;
    private Object fieldValue;



    public CompanyResourcesNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("% not found with %s : '%s'" , resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CompanyResourcesNotFoundException(String message, String resourceName, String fieldName, Object fieldValue) {
        super(message);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CompanyResourcesNotFoundException(String message) {
        super(message);
    }

    public CompanyResourcesNotFoundException(String message, Throwable cause, String resourceName, String fieldName, Object fieldValue) {
        super(message, cause);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CompanyResourcesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
