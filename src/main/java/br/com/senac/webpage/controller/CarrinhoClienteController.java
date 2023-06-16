package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.ProdutoDAO;
import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.model.ProdutoDto;
import br.com.senac.webpage.model.UsuarioAllDto;
import br.com.senac.webpage.util.IdSession;
import br.com.senac.webpage.util.ListCarrinho;
import jakarta.persistence.PostUpdate;

@Controller
@RequestMapping("/carrinhoCliente")
public class CarrinhoClienteController {
	public static ArrayList<ProdutoAllDto> produtos = new ArrayList<>();	
	 double soma = 0;
	 double frete = 0;
		
    @GetMapping    
    public ModelAndView init(final Model model) throws SQLException { 
    	 soma = 0;
 	
    	double somatemp = 0;
    	 ModelAndView mv = new ModelAndView("carrinhoClienteLogado"); 
    	System.out.println("carrinho cliente get");
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	
		if (produtos.size() != ListCarrinho.carrinho.size()) {
			for (String s : ListCarrinho.carrinho) {
				System.out.println(s);
				{
					boolean encontrei = false;
					for (ProdutoAllDto produtoAllDto : produtos) {
						if (produtoAllDto.getNome().equals(s)) {
							encontrei = true;
						}

					}
					if (encontrei == false) {
						produtos.add(produtoDAO.getProdutoCarrinho(s));
					}

				}
			}
		} 
    	for (ProdutoAllDto produto : produtos) {
    		if(produto.getQuantidade()>5)produto.setQuantidade(0);
		}
    	
    	for (ProdutoAllDto produtoAllDto : produtos) {
    		System.out.println("--------------------" + produtoAllDto.getNome());
			somatemp = produtoAllDto.getPreco() * produtoAllDto.getQuantidade();
			soma += somatemp;
		}
    	
    	soma+=frete;
    	
        mv.addObject("soma",soma);
        mv.addObject("produtos", produtos);

        return mv;
    	
    }  

	@GetMapping("/{nome}/{quantidade}")
	public ModelAndView alterarQuantidade(@PathVariable("nome") String nome, @PathVariable("quantidade") Integer acao) throws SQLException {
		ModelAndView mv = new ModelAndView("redirect:/carrinhoCliente"); 
		System.out.println("PEGAR QUANTIDADE E NOME " + acao + " " + nome);
    		 
		
		for(ProdutoAllDto p : produtos) {
			if(p.getNome().equals(nome)) {
				if(acao == 1) {
					p.setQuantidade(p.getQuantidade() + 1);
				} else if (acao == 0) {
					if(p.getQuantidade() <= 0) {
						break;
					}
					p.setQuantidade(p.getQuantidade() - 1);
					System.out.println("PASSEI");
				}
				break;
			}
		}

        mv.addObject("soma",soma);
        mv.addObject("produtos", produtos);
        return mv;
	}

	@GetMapping("/{nome}")
	public ModelAndView alterarQuantidade(@PathVariable("nome") String nome) throws SQLException {
		ModelAndView mv = new ModelAndView("redirect:/carrinhoCliente"); 
		System.out.println("---------------");
		
		for (int i = 0; i < produtos.size(); i++) {
			ProdutoAllDto p = produtos.get(i);
			if (p.getNome().equals(nome)) {
				// Encontrou uma pessoa cadastrada com nome "Pedro".

				// Remove.
				produtos.remove(p);
				System.out.println("Remove 1");

				// Sai do loop.
				break;
			}
		}
		for (int i = 0; i < ListCarrinho.carrinho.size(); i++) {
			String p = ListCarrinho.carrinho.get(i);
			if (p.equals(nome)) {
				// Encontrou uma pessoa cadastrada com nome "Pedro".

				// Remove.
				ListCarrinho.carrinho.remove(p);

				System.out.println("Remove 2");

				// Sai do loop.
				break;
			}
		}
		

        mv.addObject("soma",soma);
        mv.addObject("produtos", produtos);
        return mv;
	}

	@GetMapping("/cinco")
	public ModelAndView freteBasico(String cinco) throws SQLException {
		ModelAndView mv = new ModelAndView("redirect:/carrinhoCliente"); 
		frete=5;
        return mv;
	}
	@GetMapping("/dez")
	public ModelAndView freteRapido(String dez) throws SQLException {
		ModelAndView mv = new ModelAndView("redirect:/carrinhoCliente"); 
		frete=10;
        return mv;
	}	@GetMapping("/quinze")
	public ModelAndView fretePrincipal(String quinze) throws SQLException {
		ModelAndView mv = new ModelAndView("redirect:/carrinhoCliente"); 
		frete=15;
        return mv;
	}
	
	@GetMapping("/finalizarCompra")
	public ModelAndView finalizacao() throws SQLException {
		ModelAndView modelAndView;
		
	if (IdSession.idMain == null) {
		 modelAndView = new ModelAndView("redirect:/loginCliente/carrinho");
		
	}
	else {
		 modelAndView = new ModelAndView("redirect:/paginaCheckout");
	}
		return modelAndView;
		
	}

}