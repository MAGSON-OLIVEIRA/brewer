package com.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brewer.model.Cerveja;
import com.brewer.model.Origem;
import com.brewer.model.Sabor;
import com.brewer.repository.Estilos;
import com.brewer.service.CadastroCervejaService;




@Controller
public class CervejasController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja){
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
//	model.addAttribute(new Cerveja());
//		return "cerveja/CadastroCerveja";
		return mv;
		
	}
	
	
	@RequestMapping(value="/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return novo(cerveja);
		}
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Certo");	
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
}
