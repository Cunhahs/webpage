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

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.UsuarioDto;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String init(final Model model) {  	
    	model.addAttribute("usuarioDto", new UsuarioDto());
    	System.out.println("init");
    	return "paginaUsuarioBackoffice";
    }
    
    @PostMapping
    public String result(@ModelAttribute UsuarioDto usuarioDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println(usuarioDto.getEmail());
    	System.out.println(usuarioDto.getSenha());
    	Cripto cripto = new Cripto();
    	String senhaCriptografada = cripto.crip(usuarioDto.getSenha());
    	System.out.println(senhaCriptografada);
    	
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	try {
			boolean valido = usuarioDAO.validar(usuarioDto.getEmail(), senhaCriptografada);
			if (valido == true) {

		    	return "paginaEscolherLista";		
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "paginaUsuarioBackoffice";
    } 
}