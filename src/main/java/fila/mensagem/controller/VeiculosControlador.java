package fila.mensagem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fila.mensagem.jms.Consumidor;
import fila.mensagem.jms.Produtor;
import fila.mensagem.model.Veiculo;
import fila.mensagem.repository.VeiculosRepositorio;

@Controller
@RequestMapping("/classificados")
public class VeiculosControlador {

	@Autowired
	private Produtor produtor;

	@Autowired
	private Consumidor consumidor;

	@Autowired
	private VeiculosRepositorio veiculosRepositorio;

	@GetMapping("/adicionar")
	public ModelAndView adicionar() {
		ModelAndView modelAndView = new ModelAndView("form-novo-veiculo.html");
		modelAndView.addObject("veiculo", new Veiculo());
		return modelAndView;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		consumidor.consumir();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculos = veiculosRepositorio.findAll();
		ModelAndView modelAndView = new ModelAndView("classificados.html");
		modelAndView.addObject("veiculos", veiculos);
		return modelAndView;
	}

	@PostMapping("/adicionar")
	public ModelAndView adicionar(@ModelAttribute("veiculo") Veiculo veiculo) {
		veiculo.setDataPublicacao(new Date());
		produtor.enviarParaFila(veiculo);
		return new ModelAndView("redirect:/classificados/listar");
	}

}
