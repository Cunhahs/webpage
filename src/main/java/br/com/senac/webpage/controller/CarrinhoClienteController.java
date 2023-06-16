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
import org.springframework.web.bind.annotation.PathVariable;
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
    	 double valor = 0;
    	System.out.println("carrinho cliente get");
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	if (ListCarrinho.carrinho != null) {
    		 produtos = produtoDAO.getProdutoCarrinho(ListCarrinho.carrinho);		
		}

    	double soma = 0;
    	for (ProdutoAllDto produtoAllDto : produtos) {
			produtoAllDto.setQuantidade(1);
			System.out.println(produtoAllDto.getValorTotal());
			soma = soma + (produtoAllDto.getPreco() * produtoAllDto.getQuantidade());
		}
    	
    	

        mv.addObject("soma",soma);
          mv.addObject("produtos", produtos);

         return mv;
    	
    }  

	@GetMapping("alterarQuantidade/{nome}/{acao}")
	public String alterarQuantidade(@PathVariable String nome, @PathVariable Integer acao) throws SQLException {
		ProdutoDAO produtoDAO = new ProdutoDAO();
    	
		 java.util.List<ProdutoAllDto> produtos = null;
		 if (ListCarrinho.carrinho != null) {
    		 produtos = produtoDAO.getProdutoCarrinho(ListCarrinho.carrinho);		
		}
		 
		
		for(ProdutoAllDto p : produtos) {
			if(p.getNome().equals(nome)) {
				if(acao == 1) {
					p.setQuantidade(p.getQuantidade() + 1);
					p.setValorTotal(0.); //nao tem valor total
					p.setValorTotal(p.getValorTotal() + (p.getQuantidade() * p.getPreco()));
				} else if (acao == 0) {
					if(p.getQuantidade() <= 0) {
						break;
					}
					p.setQuantidade(p.getQuantidade() - 1);
					p.setValorTotal(0.);
					p.setValorTotal(p.getValorTotal() + (p.getQuantidade() * p.getPreco()));
				}
				break;
			}
		}

		return "redirect:/carrinhoCliente";
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