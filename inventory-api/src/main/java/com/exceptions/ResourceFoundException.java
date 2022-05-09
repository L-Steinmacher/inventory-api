package com.exceptions;

/**
 * A custom exception to be used when a resource is found but is not supposed to be utilized in the manor that it is called
 */
public class ResourceFoundException
        extends RuntimeException
{
    public ResourceFoundException(String message)
    {
        super("Error from " + message);
    }
}