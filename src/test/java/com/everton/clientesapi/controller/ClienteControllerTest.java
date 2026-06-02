package com.everton.clientesapi.controller;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.dto.ClienteResponse;
import com.everton.clientesapi.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
@DisplayName("Integration Tests: ClienteController")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ClienteService service;

    @Test
    @DisplayName("Should return 201 when creating a valid client")
    void cadastrar_ValidData_ReturnsCreated() throws Exception {
        ClienteRequest request = new ClienteRequest();
        request.setNome("John Doe");
        request.setEmail("john@example.com");
        request.setTelefone("11999999999");

        ClienteResponse response = ClienteResponse.builder()
                .id(1L)
                .nome("John Doe")
                .email("john@example.com")
                .telefone("11999999999")
                .build();

        when(service.cadastrar(any())).thenReturn(response);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("John Doe"));
    }

    @Test
    @DisplayName("Should return 400 when name is invalid")
    void cadastrar_InvalidName_ReturnsBadRequest() throws Exception {
        ClienteRequest request = new ClienteRequest();
        request.setNome("Jo"); // Min size is 3
        request.setEmail("john@example.com");

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Dados inválidos"))
                .andExpect(jsonPath("$.fields[0].name").value("nome"));
    }

    @Test
    @DisplayName("Should return 200 when listing all clients")
    void listar_ReturnsOk() throws Exception {
        when(service.listar()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
