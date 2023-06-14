package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

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

@Controller
@RequestMapping("/paginaDeProdutos")
public class PaginaDeProdutoController {

    /*@GetMapping
    public String init(final Model model) {
    	model.addAttribute("produto", new Produto());
    	return "paginaDeProdutos";
        
    }*/

    @GetMapping
    public ModelAndView init(Model model) throws SQLException {
        ModelAndView mv = new ModelAndView();

        ProdutoDAO produtoRepository = new ProdutoDAO();
        List<ProdutoAllDto> produtos = produtoRepository.findAll();

        mv.addObject("produtos", produtos);

        return mv;
    }
    
    @PostMapping
    public void result(@ModelAttribute Produto produto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
    	System.out.println("salvar do produto");
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	produtoDAO.inserir(produto);
    	
    	
    }
    
}