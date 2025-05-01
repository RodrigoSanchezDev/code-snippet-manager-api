package com.snippetapi.snippet_api.service;

import com.snippetapi.snippet_api.dto.CreateSnippetRequest;
import com.snippetapi.snippet_api.exception.ResourceNotFoundException;
import com.snippetapi.snippet_api.model.Snippet;
import com.snippetapi.snippet_api.repository.SnippetRepository;
import com.snippetapi.snippet_api.service.impl.SnippetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SnippetServiceTest {

    @Mock
    SnippetRepository repo;

    @InjectMocks
    SnippetServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndGetById() {
        CreateSnippetRequest req = CreateSnippetRequest.builder()
            .title("T")
            .code("C")
            .description("D")
            .language("L")
            .tags(Set.of("t"))
            .build();
        Snippet saved = Snippet.builder()
            .id(5L).title("T").code("C").description("D").language("L").tags(Set.of("t"))
            .build();

        when(repo.save(any(Snippet.class))).thenReturn(saved);
        when(repo.findById(5L)).thenReturn(Optional.of(saved));

        // crear
        var dto = service.createSnippet(req);
        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getCode()).isEqualTo("C");

        // obtener
        var dto2 = service.getSnippetById(5L);
        assertThat(dto2.getTitle()).isEqualTo("T");
    }

    @Test
    void deleteNonexistentThrows() {
        doThrow(new EmptyResultDataAccessException(1)).when(repo).deleteById(99L);
        assertThatThrownBy(() -> service.deleteSnippet(99L))
            .isInstanceOf(ResourceNotFoundException.class);
    }
}
