package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.ProdutoDAO;
import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.ProdutoDto;
import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/carrinhoClienteLogado")
public class carrinhoClienteLogadoController {

    @GetMapping    
    public String init(final Model model) {  	
    	model.addAttribute("produtoDto", new ProdutoDto());
    	System.out.println("init");
    	
    	return "carrinhoClienteLogado";
    }  
    
    @PostMapping
    public ModelAndView result(ProdutoDto produtoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
			
    	System.out.println(produtoDto.getTenis1());
    	ProdutoDAO produtoDao = new ProdutoDAO();
    	produtoDao.atualizaQuantidade(produtoDto);

       	ModelAndView modelAndView = new ModelAndView("redirect:paginaCheckout");
        	return modelAndView;	
    }
}