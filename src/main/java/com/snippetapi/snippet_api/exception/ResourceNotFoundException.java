package com.snippetapi.snippet_api.exception;

/**
 * Custom exception thrown when a requested resource is not found in the system.
 * 
 * Usage:
 * Throw this exception in your service or controller layer when an entity (e.g., Snippet)
 * cannot be found by its identifier or other unique field.
 * 
 * Example:
 * throw new ResourceNotFoundException("Snippet", "id", 123L);
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a detailed message.
     * 
     * @param resourceName the name of the resource (e.g., "Snippet")
     * @param fieldName the field used for lookup (e.g., "id")
     * @param fieldValue the value of the field (e.g., 123)
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s = '%s'", resourceName, fieldName, fieldValue));
    }
}
