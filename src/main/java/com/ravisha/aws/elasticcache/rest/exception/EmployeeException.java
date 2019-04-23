package com.ravisha.aws.elasticcache.rest.exception;

/**
 * Represents the employee exception that can be thrown as part of
 * employee search procesing by the search APIs.
 *
 * @author ravi Katta.
 */
public class EmployeeException extends RuntimeException{

    private static final long serialVersionUID = 5776681206288518465L;
    
    public EmployeeException(String message)
    {
       super(message);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}