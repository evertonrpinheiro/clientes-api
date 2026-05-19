package com.everton.clientesapi.service;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.model.Cliente;
import com.everton.clientesapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente cadastrar(ClienteRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();

        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, ClienteRequest request) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());

        return repository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}