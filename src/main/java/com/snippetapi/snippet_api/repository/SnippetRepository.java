package com.snippetapi.snippet_api.repository;

import com.snippetapi.snippet_api.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Snippet entities.
 * 
 * Extends JpaRepository to provide CRUD operations.
 * 
 * Custom query methods:
 * - findByLanguageIgnoreCase: Find snippets by language (case-insensitive).
 * - findByTagsIgnoreCase: Find snippets by tag (case-insensitive).
 */
@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    List<Snippet> findByLanguageIgnoreCase(String language);
    List<Snippet> findByTagsIgnoreCase(String tag);
}
