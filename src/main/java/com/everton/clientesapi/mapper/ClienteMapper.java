package com.everton.clientesapi.mapper;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.dto.ClienteResponse;
import com.everton.clientesapi.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    public List<ClienteResponse> toResponseList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntity(ClienteRequest request, Cliente cliente) {
        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
    }
}
