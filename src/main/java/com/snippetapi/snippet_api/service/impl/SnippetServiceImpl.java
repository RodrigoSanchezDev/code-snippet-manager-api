package com.snippetapi.snippet_api.service.impl;

import com.snippetapi.snippet_api.dto.CreateSnippetRequest;
import com.snippetapi.snippet_api.dto.SnippetDto;
import com.snippetapi.snippet_api.dto.UpdateSnippetRequest;
import com.snippetapi.snippet_api.exception.ResourceNotFoundException;
import com.snippetapi.snippet_api.model.Snippet;
import com.snippetapi.snippet_api.repository.SnippetRepository;
import com.snippetapi.snippet_api.service.SnippetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of SnippetService.
 * 
 * Handles business logic for creating, retrieving, updating, deleting, and searching snippets.
 * Uses SnippetRepository for data access.
 */
@Service
@RequiredArgsConstructor
public class SnippetServiceImpl implements SnippetService {

    private static final Logger log = LoggerFactory.getLogger(SnippetServiceImpl.class);

    private final SnippetRepository repository;

    /**
     * Create a new snippet and save it to the database.
     */
    @Override
    public SnippetDto createSnippet(CreateSnippetRequest request) {
        Snippet snippet = new Snippet();
        snippet.setTitle(request.getTitle());
        snippet.setCode(request.getCode());
        snippet.setDescription(request.getDescription());
        snippet.setLanguage(request.getLanguage());
        snippet.setTags(request.getTags());

        log.info("🔍 Mapped Snippet before save → {}", snippet);

        Snippet saved = repository.save(snippet);
        return toDto(saved);
    }

    /**
     * Retrieve a snippet by its ID.
     * Throws ResourceNotFoundException if not found.
     */
    @Override
    public SnippetDto getSnippetById(Long id) {
        Snippet snippet = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Snippet", "id", id));
        return toDto(snippet);
    }

    /**
     * Retrieve all snippets.
     */
    @Override
    public List<SnippetDto> getAllSnippets() {
        return repository.findAll().stream()
                         .map(this::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Update an existing snippet.
     * Throws ResourceNotFoundException if not found.
     */
    @Override
    public SnippetDto updateSnippet(Long id, UpdateSnippetRequest request) {
        Snippet snippet = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Snippet", "id", id));

        snippet.setTitle(request.getTitle());
        snippet.setCode(request.getCode());
        snippet.setDescription(request.getDescription());
        snippet.setLanguage(request.getLanguage());
        snippet.setTags(request.getTags());

        Snippet updated = repository.save(snippet);
        return toDto(updated);
    }

    /**
     * Delete a snippet by its ID.
     * Throws ResourceNotFoundException if not found.
     */
    @Override
    public void deleteSnippet(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Snippet", "id", id);
        }
        repository.deleteById(id);
    }

    /**
     * Find snippets by programming language (case-insensitive).
     */
    @Override
    public List<SnippetDto> getSnippetsByLanguage(String language) {
        return repository.findByLanguageIgnoreCase(language).stream()
                         .map(this::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Find snippets by tag (case-insensitive).
     */
    @Override
    public List<SnippetDto> getSnippetsByTag(String tag) {
        return repository.findByTagsIgnoreCase(tag).stream()
                         .map(this::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Helper method to convert a Snippet entity to a SnippetDto.
     */
    private SnippetDto toDto(Snippet snippet) {
        return SnippetDto.builder()
                .id(snippet.getId())
                .title(snippet.getTitle())
                .code(snippet.getCode())
                .description(snippet.getDescription())
                .language(snippet.getLanguage())
                .tags(snippet.getTags())
                .build();
    }
}
