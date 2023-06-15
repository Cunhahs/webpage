package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.ProdutoDAO;
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.util.ListCarrinho;

@Controller
@RequestMapping("/landingPageLogado")
public class LandingPageLogadoController {

	int i;
    @GetMapping
    public ModelAndView init(Model model) throws SQLException {
        ModelAndView mv = new ModelAndView();

        ProdutoDAO produtoRepository = new ProdutoDAO();
        List<ProdutoAllDto> produtos = produtoRepository.findAll();
        
        mv.addObject("produtos", produtos);

        return mv;
    }
    
    @GetMapping("/landingPageLogado/{nome}")
    public ModelAndView result(@PathVariable("nome") String path)  throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println("Post landing page");
    	System.out.println(path);
    	
    	if (path!=null) {
    		ListCarrinho.carrinho.add(path);		
		}
    
        	ModelAndView modelAndView = new ModelAndView("redirect:carrinhoCliente");
        	return modelAndView;	
		
    }     
}