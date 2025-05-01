package com.snippetapi.snippet_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSnippetRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Code must not be blank")
    private String code;

    private String description;

    @NotBlank(message = "Language must not be blank")
    private String language;

    @NotEmpty(message = "At least one tag is required")
    private Set<@NotBlank(message = "Tag must not be blank") String> tags;
}
