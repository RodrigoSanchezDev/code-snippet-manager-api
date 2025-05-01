package com.snippetapi.snippet_api.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnippetDto {
    
    private Long id;
    private String title;
    private String code;
    private String description;
    private String language;
    private Set<String> tags;
}
