package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/paginaCheckout")
public class paginaCheckoutController {
	 ArrayList<ProdutoAllDto> produtos = CarrinhoClienteController.produtos;	
	
    @GetMapping
    public String init(final Model model) {
    	
    	System.out.println("pagina checkout");
    	return "PaginaCheckout";
    }
  
    @PostMapping
    public String finalizacao(final Model model) {
    	
    	System.out.println("pagina checkout");
    	return "PaginaCheckout";
    }
}