package br.com.cotiinformatica.services;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.repositories.PlanoRepository;

@Service
@Slf4j
public class PlanoService {

	public ResponseEntity<List<Plano>> consultar() {
		log.info("Iniciando o processo de consultar todos os planos.");

		try {
			log.info("Consultando planos.");
			PlanoRepository planoRepository = new PlanoRepository();
			List<Plano> planos = planoRepository.findAll();

			if (planos.isEmpty()) {
				log.warn("Lista de planos vazia.");
				return ResponseEntity.status(204).body(null);
			}

			log.info("Retornando a lista de planos.");
			return ResponseEntity.status(200).body(planos);
		} catch (Exception e) {
			log.error("Erro ao consultar planos.");
			return ResponseEntity.status(500).body(null);
		}
	}

	public ResponseEntity<Plano> consultarPorId(UUID id) {
		log.info("Iniciando o processo de consultar plano por ID");

		try {

			log.info("Buscando plano com ID: {}", id);
			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(id);

			if (plano == null) {
				log.warn("Plano não encontrado com o ID: {}", id);
				return ResponseEntity.status(204).body(null);
			}

			log.info("Retornando o plano encontrado.");
			return ResponseEntity.status(200).body(plano);
		} catch (Exception e) {
			log.error("Erro ao consultar plano por id.");
			return ResponseEntity.status(500).body(null);
		}
	}
}