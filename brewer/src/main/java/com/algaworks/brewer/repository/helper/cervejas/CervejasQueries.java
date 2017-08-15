package com.algaworks.brewer.repository.helper.cervejas;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

//cervejasQueries recebe todas as queries customizadas do sistema
public interface CervejasQueries {
	
	//
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);

}
