package com.everton.clientesapi.controller;

import com.everton.clientesapi.dto.ClienteRequest;
import com.everton.clientesapi.dto.ClienteResponse;
import com.everton.clientesapi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Management of library members/clients")
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new client", description = "Creates a new client in the system. Email must be unique.")
    @ApiResponses({
            @ApiResponse(responseCode = "211", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data or email already exists")
    })
    public ClienteResponse cadastrar(@RequestBody @Valid ClienteRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    @Operation(summary = "List all clients", description = "Returns a complete list of registered clients.")
    public List<ClienteResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find client by ID", description = "Returns detailed information about a specific client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client", description = "Updates an existing client's information.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete client", description = "Removes a client from the system by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}