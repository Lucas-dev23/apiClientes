package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ClientePostRequestDto;
import br.com.cotiinformatica.dtos.ClientePutRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping()
	@Operation(
	    summary = "Cadastrar cliente",
	    description = "Endpoint responsável por cadastrar clientes. Certifique-se de inserir o token de acesso."
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso."),
	    @ApiResponse(responseCode = "400", description = "Requisição inválida."),
	    @ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
	    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
	})
	public ResponseEntity<String> post(@RequestBody @Valid ClientePostRequestDto dto) throws Exception {
	    return clienteService.cadastrar(dto);
	}

	@PutMapping()
	@Operation(
	    summary = "Atualizar cliente",
	    description = "Endpoint responsável por atualizar clientes. Certifique-se de inserir o token de acesso."
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Cliente atualizado com sucesso."),
	    @ApiResponse(responseCode = "400", description = "Requisição inválida."),
	    @ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
	    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
	})
	public ResponseEntity<String> put(@RequestBody @Valid ClientePutRequestDto dto) throws Exception{
		return clienteService.atualizar(dto);
	}
	
	
	@DeleteMapping("{id}")
	@Operation(
		    summary = "Excluir cliente",
		    description = "Endpoint responsável por excluir clientes. Certifique-se de inserir o token de acesso."
		)
		@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Cliente excluído com sucesso."),
		    @ApiResponse(responseCode = "400", description = "Requisição inválida."),
		    @ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
		    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
		})
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		return clienteService.excluir(id);
	}
	
	@GetMapping()
	@Operation(
		    summary = "Consultar clientes",
		    description = "Endpoint responsável por consultar todos os clientes. Certifique-se de inserir o token de acesso."
		)
		@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso."),
		    @ApiResponse(responseCode = "204", description = "Lista de clientes vazia."),
		    @ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
		    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
		})
	public ResponseEntity<List<Cliente>> getAll() throws Exception {
		return clienteService.consultarTodos();
	}
	
	@GetMapping("{id}")
	@Operation(
		    summary = "Consultar cliente por id",
		    description = "Endpoint responsável por consultar cliente por id. Certifique-se de inserir o token de acesso."
		)
		@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso."),
		    @ApiResponse(responseCode = "204", description = "Cliente não encontrado, verifique o id."),
		    @ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
		    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
		})
	public ResponseEntity<Cliente> getById(@PathVariable UUID id) throws Exception {
		return clienteService.consultarPorId(id);
	}
	
}
