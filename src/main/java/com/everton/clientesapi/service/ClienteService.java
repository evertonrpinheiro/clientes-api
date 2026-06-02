package com.everton.clientesapi.service;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.dto.ClienteResponse;
import com.everton.clientesapi.exception.BusinessException;
import com.everton.clientesapi.exception.ResourceNotFoundException;
import com.everton.clientesapi.mapper.ClienteMapper;
import com.everton.clientesapi.model.Cliente;
import com.everton.clientesapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Transactional
    public ClienteResponse cadastrar(ClienteRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new BusinessException(String.format("E-mail %s já cadastrado", request.getEmail()));
        }

        Cliente cliente = mapper.toEntity(request);
        return mapper.toResponse(repository.save(cliente));
    }

    public List<ClienteResponse> listar() {
        return mapper.toResponseList(repository.findAll());
    }

    public ClienteResponse buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cliente de ID %d não encontrado", id)));
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cliente de ID %d não encontrado", id)));

        if (repository.existsByEmail(request.getEmail()) && !cliente.getEmail().equals(request.getEmail())) {
            throw new BusinessException(String.format("E-mail %s já cadastrado por outro cliente", request.getEmail()));
        }

        mapper.updateEntity(request, cliente);
        return mapper.toResponse(repository.save(cliente));
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cliente de ID %d não encontrado", id)));
        repository.delete(cliente);
    }
}