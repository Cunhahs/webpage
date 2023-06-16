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
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.ProdutoDAO;
import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;
import br.com.senac.webpage.util.ListCarrinho;

@Controller
@RequestMapping("/paginaResumoCompra")
public class PaginaResumoCompraController {

public static ArrayList<ProdutoAllDto> produtos = CarrinhoClienteController.produtos;	
double soma = 0;

	
@GetMapping    
public ModelAndView init(final Model model) throws SQLException { 
	 soma = 0;
	 double frete = CarrinhoClienteController.frete;
	 for (ProdutoAllDto produtoAllDto : produtos) {
		System.out.println(produtoAllDto.getQuantidade());
	}

	double somatemp = 0;
	 ModelAndView mv = new ModelAndView("paginaResumoCompra"); 
	System.out.println("pagina resumo compra get");
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
 
}