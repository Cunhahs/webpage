package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import br.com.senac.webpage.model.ProdutoAllDto;
import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/paginaAdicaoProduto")
public class PaginaAdicaoProdutoController {
    
    @GetMapping
    public ModelAndView init(Model model) throws SQLException {
        ModelAndView mv = new ModelAndView();

        ProdutoDAO produtoRepository = new ProdutoDAO();
        List<ProdutoAllDto> produtos = produtoRepository.findAll();

        mv.addObject("produtos", produtos);

        return mv;
    }

    @PostMapping
    public void result(@ModelAttribute ProdutoAllDto produto) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
    	System.out.println("salvar do produto");
    	
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	produtoDAO.inserir(produto);
    	
    	
    }
}
