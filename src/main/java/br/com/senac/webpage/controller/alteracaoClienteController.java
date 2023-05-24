package br.com.senac.webpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.model.UsuarioDto;

@Controller
@RequestMapping("/alteracao-cliente")
public class alteracaoClienteController {

    @GetMapping
    public String init(final Usuario usuario) {
    	return "alteracaoCliente";
        
    }    
//    @PostMapping
//    public void requestLogin(JSONObject json) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
//    	Gson gson = new Gson();
//    	gson.toJson(json);
//    	String senha = (String) json.get("senha");
//    	String email = (String) json.get("email");
//    	
//    	System.out.println("bati");
//    	UsuarioDAO usuarioDAO = new UsuarioDAO();
//    	Cripto cripto = new Cripto();
//    	String senhaFinal = cripto.crip(senha);
//    	boolean altorizado = usuarioDAO.validar(email, senhaFinal);
//    	if (altorizado == true)  System.out.println(senhaFinal);
//    		
//		
//    }
   
}