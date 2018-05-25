package com.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brewer.model.Estilo;
import com.brewer.repository.Estilos;
import com.brewer.service.exception.NomeEstiloJaCadastradoExcetion;



@Service
public class CadastroEstiloService {
	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo){
		Optional <Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		if(estiloOptional.isPresent()){
			throw new NomeEstiloJaCadastradoExcetion("Nome de Estilo já cadastrado");
			
		}
		return estilos.saveAndFlush(estilo);
		
	}

}
