package br.com.cotiinformatica.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.repositories.PlanoRepository;

@Service
public class PlanoService {

	public ResponseEntity<List<Plano>> consultar() {

		try {

			PlanoRepository planoRepository = new PlanoRepository();
			List<Plano> planos = planoRepository.findAll();

			if (planos.size() == 0)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(planos);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	public ResponseEntity<Plano> consultarPorId(UUID id) {
		
		try {

			PlanoRepository planoRepository = new PlanoRepository();
			Plano plano = planoRepository.findById(id);

			if (plano == null)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(plano);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}