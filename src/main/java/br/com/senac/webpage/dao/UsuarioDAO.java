package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Usuario;
import br.com.senac.webpage.model.UsuarioAllDto;
import br.com.senac.webpage.model.UsuarioDto;

@Repository
public class UsuarioDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/marketplace";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void inserir(Usuario usuario) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		var ps = con.prepareStatement("INSERT INTO usuario (id, nome, email, senha, cpf, sexo, nascimento, cidade, estado, logradouro, numero, complemento, cep, bairro, grupo, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, usuario.getId());
		ps.setString(2, usuario.getNome());
		ps.setString(3, usuario.getEmail());
		ps.setString(4, usuario.getSenha());
		ps.setString(5, usuario.getCpf());
		ps.setString(6, usuario.getSexo());
		ps.setString(7, usuario.getNascimento());
		ps.setString(8, usuario.getCidade());
		ps.setString(9, usuario.getEstado());
		ps.setString(10, usuario.getLogradouro());
		ps.setString(11, usuario.getNumero());
		ps.setString(12, usuario.getComplemento());
		ps.setString(13, usuario.getCep());
		ps.setString(14, usuario.getBairro());
		ps.setString(15, usuario.getGrupo());
		ps.setString(16, usuario.getStatus());
		
		ps.execute();

		con.close();
	}

	public boolean validarCliente(String email, String senha, String grupo) throws SQLException {
		System.out.println("cheguei");
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("entrei no banco");
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		System.out.println("entrei no banco");
		String query = "select * from Usuario where email = ? and senha = ? and grupo = ?";
		System.out.println("entrei no banco");
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senha);
		preparedStatement.setString(3, grupo);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}

	}
	public boolean validarAdmin(String email, String senha, String grupo) throws SQLException {
		System.out.println("cheguei");
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("entrei no banco");
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		System.out.println("entrei no banco");
		String query = "select * from Usuario where email = ? and senha = ? and grupo = ?";
		System.out.println("entrei no banco");
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senha);
		preparedStatement.setString(3, grupo);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}

	}

	
	public void apagar(String email) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		var ps = con.prepareStatement("DELETE FROM Usuario WHERE email=?");
		ps.setString(1, email);
		ps.execute();

		con.close();
	}

	public boolean verify(String email) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select * from Usuario where email = ?";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getNome(String email) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select nome from Usuario where email = ? ";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString(1);
		} else {
			return null;
		}
	}
	
    public void atualizar(Usuario usuario, String emailAntigo) throws SQLException{
        var con = DriverManager.getConnection(URL, USER, PASSWORD);

    	//var ps = con.prepareStatement("UPDATE Usuario set (id, nome, email, senha, celular) VALUES (?, ?, ?, ?, ?) where email = ?");

    	var ps = con.prepareStatement("update Usuario u set u.nome = ?, u.email = ?, u.senha = ?, u.grupo = ?  where u.email = ?");

		ps.setString(1, usuario.getNome());
		ps.setString(2, usuario.getEmail());
		ps.setString(3, usuario.getSenha());
		ps.setString(4, usuario.getGrupo());

		ps.setString(5, emailAntigo);
		
		ps.execute();

		System.out.println(con.getTransactionIsolation());

		con.close();
    }

	public List<UsuarioAllDto> findAll() throws SQLException {
		 var con = DriverManager.getConnection(URL, USER, PASSWORD);
		 
		 String sql = "select * from usuario;";
		 Statement stm = con.createStatement();
		ResultSet ps = stm.executeQuery(sql);
		 List<UsuarioAllDto> listAll = new ArrayList<UsuarioAllDto>();
		
		while (ps.next()) {

		UsuarioAllDto n = new UsuarioAllDto();
					
		n.setNome(ps.getString("nome"));
		n.setEmail(ps.getString("email"));
		n.setCpf(ps.getString("cpf"));
		n.setStatus(ps.getString("status"));
		n.setGrupo(ps.getString("grupo"));

		listAll.add(n);
		System.out.println("ADICIONOU 1");
					
		}
		System.out.println("Adicionou Todos!");
				
		 
		return listAll ;
	}

	public String getId(String email, String senhaCriptografada) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select id from Usuario where email = ? and senha = ? ";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senhaCriptografada);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString(1);
		} else {
			return null;
		}
	}

}
