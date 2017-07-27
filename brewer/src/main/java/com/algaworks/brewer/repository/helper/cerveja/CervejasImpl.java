package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

//aqui as queries são implementadas
public class CervejasImpl implements CervejasQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Cerveja> filtrar(CervejaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		if(filtro != null){
			
			
			
			if(!StringUtils.isEmpty(filtro.getSku())){ // se houver SKU no filtro
				criteria.add(Restrictions.eq("sku", filtro.getSku())); // filtra cervejas pelo SKU
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())){ //se houver nome no filtro
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE)); //filtrar pelo nome
			}
			
			if(isEstiloPresente(filtro)){ // filtrar pelo estilo caso selecionado
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo())); // filtra pelo estilo
			}
			
			if(filtro.getSabor() != null ){ //filtrar pelo sabor caso selecionado
				criteria.add(Restrictions.eq("sabor",filtro.getSabor())); // filtra pelo sabor
			}
			
			if(filtro.getOrigem() != null ){ // filtra pela origem caso selecionado
				criteria.add(Restrictions.eq("origem",filtro.getOrigem())); // filtra pela origem
			}
			
			if(filtro.getValorDe() != null ){ //filtra pelo valor caso digitado
				criteria.add(Restrictions.ge("valor",filtro.getValorDe())); // filtra por valores maiores ou iguais ao digitaod
			}
			
			if(filtro.getValorAte() != null ){ // filtra pelo valor caso digitado
				criteria.add(Restrictions.le("valor",filtro.getValorAte())); // filtra por valores menores ou iguais ao digitaod
			}
			
			
			
			
		}
		
		
		return criteria.list();
	}
	
	//método para verificar se o estilo será necessário para o filtro
	private boolean isEstiloPresente(CervejaFilter filtro){
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null; 
	}

}
