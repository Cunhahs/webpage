package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.ProdutoDAO;
import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.model.ProdutoDto;
import br.com.senac.webpage.model.UsuarioAllDto;
import br.com.senac.webpage.util.ListCarrinho;
import jakarta.persistence.PostUpdate;

@Controller
@RequestMapping("/carrinhoCliente")
public class CarrinhoClienteController {


	int i;
    @GetMapping    
    public String init(final Model model) throws SQLException {  
    	System.out.println("carrinho cliente get");
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	if (ListCarrinho.carrinho != null) {
    		java.util.List<ProdutoAllDto> listaProdutosCarrinho = produtoDAO.getProdutoCarrinho(ListCarrinho.carrinho);		
		}
		
    	return "carrinhoClienteLogado";
    }  
    
    @PostMapping
    public ModelAndView result(@ModelAttribute ProdutoAllDto produtoAllDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
		for (String s : ListCarrinho.carrinho) {
			if (s.equals(produtoAllDto.getDescricao())) {
				i=1;
			}
		}
    	if (i==0) {
    		ListCarrinho.carrinho.add(produtoAllDto.getDescricao());	
    		System.out.println(ListCarrinho.carrinho.get(0));
		}
    	
    	i=0;
    	
    
        	ModelAndView modelAndView = new ModelAndView("redirect:carrinhoCliente");
        	return modelAndView;	
		
    }      
    
//    @PostMapping
//    public ModelAndView result(ProdutoDto produtoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
//    	
//       	ModelAndView modelAndView = new ModelAndView("redirect:paginaCheckout");
//        	return modelAndView;	
//    }
}