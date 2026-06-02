package com.everton.clientesapi.service;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.dto.ClienteResponse;
import com.everton.clientesapi.exception.BusinessException;
import com.everton.clientesapi.exception.ResourceNotFoundException;
import com.everton.clientesapi.mapper.ClienteMapper;
import com.everton.clientesapi.model.Cliente;
import com.everton.clientesapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Tests: ClienteService")
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteService service;

    private ClienteRequest request;
    private Cliente cliente;
    private ClienteResponse response;

    @BeforeEach
    void setUp() {
        request = new ClienteRequest();
        request.setNome("John Doe");
        request.setEmail("john@example.com");
        request.setTelefone("11999999999");

        cliente = Cliente.builder()
                .id(1L)
                .nome("John Doe")
                .email("john@example.com")
                .telefone("11999999999")
                .build();

        response = ClienteResponse.builder()
                .id(1L)
                .nome("John Doe")
                .email("john@example.com")
                .telefone("11999999999")
                .build();
    }

    @Test
    @DisplayName("Should successfully register a new client")
    void cadastrar_Success() {
        when(repository.existsByEmail(anyString())).thenReturn(false);
        when(mapper.toEntity(any())).thenReturn(cliente);
        when(repository.save(any())).thenReturn(cliente);
        when(mapper.toResponse(any())).thenReturn(response);

        ClienteResponse result = service.cadastrar(request);

        assertNotNull(result);
        assertEquals(request.getEmail(), result.getEmail());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should throw BusinessException when email already exists")
    void cadastrar_EmailAlreadyExists() {
        when(repository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(BusinessException.class, () -> service.cadastrar(request));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should successfully find a client by ID")
    void buscarPorId_Success() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(mapper.toResponse(any())).thenReturn(response);

        ClienteResponse result = service.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when client not found")
    void buscarPorId_NotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(1L));
    }
}
