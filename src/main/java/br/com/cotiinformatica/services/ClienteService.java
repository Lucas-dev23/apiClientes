package br.com.cotiinformatica.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.dtos.ClientePostRequestDto;
import br.com.cotiinformatica.dtos.ClientePutRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.repositories.ClienteRepository;
import br.com.cotiinformatica.repositories.PlanoRepository;

@Service
public class ClienteService {

	public ResponseEntity<String> cadastrar(ClientePostRequestDto dto) {

		try {

			Cliente cliente = new Cliente();
			cliente.setId(UUID.randomUUID());
			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setTelefone(dto.getTelefone());

			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(dto.getPlanoId());

			if (plano == null)
				return ResponseEntity.status(400).body("Plano não encontrado. Verifique o ID informado.");

			cliente.setPlano(plano);

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.insert(cliente);

			return ResponseEntity.status(201).body("Cliente cadastrado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	public ResponseEntity<String> atualizar(ClientePutRequestDto dto) {

		try {

			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(dto.getId());

			if (cliente == null)
				return ResponseEntity.status(400).body("Cliente não encontrado. Verifique o ID informado.");

			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(dto.getPlanoId());

			if (plano == null)
				return ResponseEntity.status(400).body("Plano não encontrado. Verifique o ID informado.");

			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setTelefone(dto.getTelefone());
			cliente.setPlano(plano);

			clienteRepository.update(cliente);

			return ResponseEntity.status(200).body("Cliente atualizado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public ResponseEntity<String> excluir(UUID id) {

		try {

			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(id);

			if (cliente == null)
				return ResponseEntity.status(400).body("Cliente não encontrado. Verifique o ID informado.");

			clienteRepository.delete(cliente);

			return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public ResponseEntity<List<Cliente>> consultarTodos() {
		try {

			ClienteRepository clienteRepository = new ClienteRepository();
			List<Cliente> clientes = clienteRepository.findAll();

			if (clientes.size() == 0) // se a lista está vazia
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(clientes);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	public ResponseEntity<Cliente> consultarPorId(UUID id) {
		try {

			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(id);

			if (cliente == null)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(cliente);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
