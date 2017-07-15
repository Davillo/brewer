package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.service.CadastroEstiloService;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
public class EstilosController {
	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	
	@RequestMapping("/estilos/novo")
	public ModelAndView novo(Estilo estilo){
		ModelAndView mv = new ModelAndView("/estilo/CadastroEstilo");
		return mv;
	}
	
	
	@RequestMapping(value = "/estilos/novo" , method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(estilo);
		}
		
		
		try{
			cadastroEstiloService.salvar(estilo);
		}catch (NomeEstiloJaCadastradoException ex) {
			result.rejectValue("nome", ex.getMessage(), ex.getMessage());
			return novo(estilo);
		}
		
		
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return new ModelAndView("redirect:/estilos/novo");
	}
	
	
	@RequestMapping(value ="/estilos", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo,BindingResult result){
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		try{
			estilo = cadastroEstiloService.salvar(estilo);
		}catch (NomeEstiloJaCadastradoException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.ok(estilo);
	}
	
}
