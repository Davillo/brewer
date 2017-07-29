package com.algaworks.brewer.repository.helper.cerveja;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

//cervejasQueries recebe todas as queries customizadas do sistema
public interface CervejasQueries {
	
	//
	public List<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);

}
