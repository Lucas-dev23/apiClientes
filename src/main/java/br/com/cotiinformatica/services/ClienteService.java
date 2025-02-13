package br.com.cotiinformatica.services;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.dtos.ClientePostRequestDto;
import br.com.cotiinformatica.dtos.ClientePutRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.repositories.ClienteRepository;
import br.com.cotiinformatica.repositories.PlanoRepository;

@Service
@Slf4j
public class ClienteService {

	public ResponseEntity<String> cadastrar(ClientePostRequestDto dto) {
		log.info("Iniciando o processo de cadastro do cliente: {}", dto.getNome());

		try {
			Cliente cliente = new Cliente();
			cliente.setId(UUID.randomUUID());
			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setTelefone(dto.getTelefone());

			log.info("Buscando plano com o ID: {}", dto.getPlanoId());
			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(dto.getPlanoId());

			if (plano == null) {
				log.warn("Plano não encontrado com o ID: {}", dto.getPlanoId());
				return ResponseEntity.status(400).body("Plano não encontrado. Verifique o ID informado.");
			}

			cliente.setPlano(plano);

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.insert(cliente);
			log.info("CLiente: {} cadastrado com sucesso.", dto.getNome());

			return ResponseEntity.status(201).body("Cliente cadastrado com sucesso.");
		} catch (Exception e) {
			log.error("Erro ao cadastrar cliente.");
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public ResponseEntity<String> atualizar(ClientePutRequestDto dto) {
		log.info("Iniciando o processo de alteração de cliente");

		try {
			log.info("Buscando o cliente com ID: {}", dto.getId());
			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(dto.getId());

			if (cliente == null) {
				log.warn("Cliente não encontrado com o ID: {}", dto.getId());
				return ResponseEntity.status(400).body("Cliente não encontrado. Verifique o ID informado.");
			}

			log.info("Buscando plano com ID: {}", dto.getPlanoId());
			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(dto.getPlanoId());

			if (plano == null) {
				log.warn("Plano não encontrado com o ID: {}", dto.getPlanoId());
				return ResponseEntity.status(400).body("Plano não encontrado. Verifique o ID informado.");
			}

			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setTelefone(dto.getTelefone());
			cliente.setPlano(plano);

			clienteRepository.update(cliente);
			log.info("Cliente: {} atualizado com sucesso.", dto.getNome());

			return ResponseEntity.status(200).body("Cliente atualizado com sucesso.");
		} catch (Exception e) {
			log.error("Erro ao atualizar cliente.");
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public ResponseEntity<String> excluir(UUID id) {
		log.info("Iniciando o processo de exclusão do cliente: {}", id);

		try {
			log.info("Buscando o cliente com ID: {}", id);
			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(id);

			if (cliente == null) {
				log.warn("Cliente não encontrado com ID: {}", id);
				return ResponseEntity.status(400).body("Cliente não encontrado. Verifique o ID informado.");
			}

			clienteRepository.delete(cliente);
			log.info("Cliente excluído com sucesso.");

			return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
		} catch (Exception e) {
			log.error("Erro ao excluir cliente.");
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	public ResponseEntity<List<Cliente>> consultarTodos() {
		log.info("Iniciando o processo de consultar todos os clientes.");

		try {
			log.info("Buscando clientes.");
			ClienteRepository clienteRepository = new ClienteRepository();
			List<Cliente> clientes = clienteRepository.findAll();

			if (clientes.isEmpty()) {
				log.error("Lista de clientes vazia.");
				return ResponseEntity.status(204).body(null);
			}

			log.info("Retornando a lista de clientes.");
			return ResponseEntity.status(200).body(clientes);
		} catch (Exception e) {
			log.error("Erro ao consultar clientes.");
			return ResponseEntity.status(500).body(null);
		}
	}

	public ResponseEntity<Cliente> consultarPorId(UUID id) {
		log.info("Iniciando o processo de consultar um cliente por id");

		try {

			log.info("Buscando o cliente com o ID: {}", id);
			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(id);

			if (cliente == null) {
				log.warn("Cliente não encontrado com ID: {}", id);
				return ResponseEntity.status(204).body(null);
			}

			log.info("Retornando o cliente encontrado.");
			return ResponseEntity.status(200).body(cliente);
		} catch (Exception e) {
			log.error("Erro ao buscar cliente por id.");
			return ResponseEntity.status(500).body(null);
		}
	}
}
