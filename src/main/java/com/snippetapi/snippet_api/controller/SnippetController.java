package com.snippetapi.snippet_api.controller;

import com.snippetapi.snippet_api.dto.*;
import com.snippetapi.snippet_api.service.SnippetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/snippets")
@RequiredArgsConstructor
public class SnippetController {

    private final SnippetService service;

    @PostMapping
    public ResponseEntity<SnippetDto> createSnippet(
            @Valid @RequestBody CreateSnippetRequest request) {
        System.out.println("⚙️ Received CreateSnippetRequest → " + request);
        SnippetDto created = service.createSnippet(request);
        return ResponseEntity
                .created(URI.create("/api/snippets/" + created.getId()))
                .body(created);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SnippetDto> getSnippetById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSnippetById(id));
    }

    @GetMapping
    public ResponseEntity<List<SnippetDto>> getAllSnippets() {
        return ResponseEntity.ok(service.getAllSnippets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SnippetDto> updateSnippet(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSnippetRequest request) {
        return ResponseEntity.ok(service.updateSnippet(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSnippet(@PathVariable Long id) {
        service.deleteSnippet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SnippetDto>> searchSnippets(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String tag) {
        if (language != null) {
            return ResponseEntity.ok(service.getSnippetsByLanguage(language));
        } else if (tag != null) {
            return ResponseEntity.ok(service.getSnippetsByTag(tag));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
