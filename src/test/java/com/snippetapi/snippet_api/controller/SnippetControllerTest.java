package com.snippetapi.snippet_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snippetapi.snippet_api.dto.CreateSnippetRequest;
import com.snippetapi.snippet_api.dto.SnippetDto;
import com.snippetapi.snippet_api.service.SnippetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SnippetController.class)
class SnippetControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    SnippetService service;

    @Test
    @DisplayName("POST /api/snippets → 201 + body")
    void createSnippet() throws Exception {
        // 1. Preparar petición
        CreateSnippetRequest req = CreateSnippetRequest.builder()
            .title("Test")
            .code("System.out.println(\"Hi\");")
            .description("desc")
            .language("Java")
            .tags(Set.of("test"))
            .build();

        // 2. Mockear el servicio
        SnippetDto resp = SnippetDto.builder()
            .id(42L)
            .title(req.getTitle())
            .code(req.getCode())
            .description(req.getDescription())
            .language(req.getLanguage())
            .tags(req.getTags())
            .build();
        Mockito.when(service.createSnippet(Mockito.any())).thenReturn(resp);

        // 3. Ejecutar y aserciones
        mvc.perform(post("/api/snippets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req))
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/snippets/42"))
            .andExpect(jsonPath("$.id").value(42))
            .andExpect(jsonPath("$.title").value("Test"))
            .andExpect(jsonPath("$.code").value("System.out.println(\"Hi\");"));
    }
}
