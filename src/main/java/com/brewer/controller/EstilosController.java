package com.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brewer.model.Estilo;
import com.brewer.service.CadastroEstiloService;
import com.brewer.service.exception.NomeEstiloJaCadastradoExcetion;

@Controller
@RequestMapping("/estilos")
public class EstilosController {
	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo){
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");

//	model.addAttribute(new Cerveja());
//		return "cerveja/CadastroCerveja";
		return mv;
		
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return novo(estilo);
		}
		try{
		cadastroEstiloService.salvar(estilo);
		}catch(NomeEstiloJaCadastradoExcetion e){
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		attributes.addFlashAttribute("mensagem", "Cadastro de estilo realizado com Sucesso.");	
		return new ModelAndView("redirect:/estilos/estilo");
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		//try { -- Esse tratamento de erro estar sendo feito pelo pkg handler apanas para metodos ResponseBody
			cadastroEstiloService.salvar(estilo);
		//}catch (NomeEstiloJaCadastradoExcetion e) {
		//	return ResponseEntity.badRequest().body(e.getMessage());
		//}
			//System.out.println(estilo.getNome());
		return ResponseEntity.ok(estilo);
	}

}
