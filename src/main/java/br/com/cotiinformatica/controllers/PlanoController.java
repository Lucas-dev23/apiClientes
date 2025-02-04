package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.services.PlanoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/planos")
public class PlanoController {

	@Autowired
	private PlanoService planoService;
	
	@Operation(
			summary = "Consultar todos os planos",
			description = "Endpoint responsável por consultar todos os planos. Certifique-se de inserir o token de acesso."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de planos retornada com sucesso."),
			@ApiResponse(responseCode = "204", description = "Lista de planos vazia."),
			@ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
	})
	@GetMapping()
	public ResponseEntity<List<Plano>> getAll() throws Exception {
		return planoService.consultar();
	}
	
	@Operation(
			summary = "Consultar plano por id",
			description = "Endpoint responsável por consultar plano por id. Certifique-se de inserir o token de acesso."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Plano retornado com sucesso."),
			@ApiResponse(responseCode = "204", description = "Plano não encontrado, verifique o id."),
			@ApiResponse(responseCode = "401", description = "Não autorizado. Token ausente ou inválido."),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
	})
	@GetMapping("{id}")
	public ResponseEntity<Plano> getById(@PathVariable UUID id) throws Exception {
		return planoService.consultarPorId(id);
	}
}
