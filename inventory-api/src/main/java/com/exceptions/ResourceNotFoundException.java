package com.exceptions;

public class ResourceNotFoundException
    extends RuntimeException
{
    public ResourceNotFoundException(String message) {super(("Error from a " + message));}
}
