package com.snippetapi.snippet_api.service;

import com.snippetapi.snippet_api.dto.*;

import java.util.List;

/**
 * Service interface for managing code snippets.
 * 
 * Defines operations for creating, retrieving, updating, deleting, and searching snippets.
 */
public interface SnippetService {

    /**
     * Create a new snippet.
     */
    SnippetDto createSnippet(CreateSnippetRequest request);

    /**
     * Retrieve a snippet by its ID.
     */
    SnippetDto getSnippetById(Long id);

    /**
     * Retrieve all snippets.
     */
    List<SnippetDto> getAllSnippets();

    /**
     * Update an existing snippet.
     */
    SnippetDto updateSnippet(Long id, UpdateSnippetRequest request);

    /**
     * Delete a snippet by its ID.
     */
    void deleteSnippet(Long id);

    /**
     * Find snippets by programming language.
     */
    List<SnippetDto> getSnippetsByLanguage(String language);

    /**
     * Find snippets by tag.
     */
    List<SnippetDto> getSnippetsByTag(String tag);
}
