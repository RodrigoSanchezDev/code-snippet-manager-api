package com.snippetapi.snippet_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entity representing a code snippet.
 * 
 * Fields:
 * - id: Unique identifier for the snippet.
 * - title: Title of the snippet (required).
 * - code: The actual code content (required, stored as TEXT).
 * - description: Optional description of the snippet.
 * - language: Programming language of the snippet (required).
 * - tags: Set of tags associated with the snippet.
 * 
 * JPA annotations are used for ORM mapping.
 * Lombok annotations generate boilerplate code (getters, setters, etc.).
 */
@Entity
@Table(name = "snippets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Snippet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;

    private String description;

    @Column(nullable = false)
    private String language;

    @ElementCollection
    @CollectionTable(
        name = "snippet_tags",
        joinColumns = @JoinColumn(name = "snippet_id")
    )
    @Column(name = "tag")
    private Set<String> tags;
}
