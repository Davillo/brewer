package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.algaworks.brewer.model.Cerveja;

@Controller
public class CervejasController {
	
	@RequestMapping("/cervejas/novo")
	public String novo(){
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Validated Cerveja cerveja,BindingResult result){
		System.out.println(">>>>>>>> SKU:"+cerveja.getSku());
		if(result.hasErrors()){
			System.out.println("Tem erro!");
		}
		return "cerveja/CadastroCerveja";
		
	}
}
