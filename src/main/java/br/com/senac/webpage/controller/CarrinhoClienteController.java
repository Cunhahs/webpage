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
    public ModelAndView init(final Model model) throws SQLException {  
    	 ModelAndView mv = new ModelAndView("carrinhoClienteLogado");
    	 java.util.List<ProdutoAllDto> produtos = null;

    	System.out.println("carrinho cliente get");
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	if (ListCarrinho.carrinho != null) {
    		 produtos = produtoDAO.getProdutoCarrinho(ListCarrinho.carrinho);		
		}
          
    	System.out.println(produtos.get(0).getPreco());
          mv.addObject("produtos", produtos);

         return mv;
    	
    }  
    
//    @PostMapping
//    public ModelAndView result(@ModelAttribute ProdutoAllDto produtoAllDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//    	
//
//		
//    }      
    
//    @PostMapping
//    public ModelAndView result(ProdutoDto produtoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
//    	
//       	ModelAndView modelAndView = new ModelAndView("redirect:paginaCheckout");
//        	return modelAndView;	
//    }
}