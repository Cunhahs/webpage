package br.com.senac.webpage.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.webpage.dao.UsuarioDAO;
import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.util.Cripto;

@Controller
@RequestMapping("/paginaCadastroCliente")
public class PaginaCadastroClienteController {

    @GetMapping
    public String init(final Model model) {
    	 model.addAttribute("usuario", new Usuario()); 
    	return "paginaCadastroCliente";
    }
    
    public String enviar(final Model model) {  	
    	return "loginCliente";
    }
    
    @PostMapping
    public ModelAndView result(@ModelAttribute Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
    	Cripto cripto = new Cripto();
    	String senhaCriptografada = cripto.crip(usuario.getSenha());
    	usuario.setSenha(senhaCriptografada);
    	usuario.setGrupo("Cliente");
    	usuario.setStatus("ativo");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
    	String dataFormatada = sdf.format(hora);	
    	String idcripto = cripto.crip(dataFormatada);
    	usuario.setId(idcripto);
    		
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	usuarioDAO.inserir(usuario);
			
       	ModelAndView modelAndView = new ModelAndView("redirect:loginCliente");
        	return modelAndView;	
    }
}