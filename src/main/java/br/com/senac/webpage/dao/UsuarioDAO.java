package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Usuario;

@Repository
public class UsuarioDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/marketplace";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void inserir(Usuario usuario) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		

		var ps = con.prepareStatement("INSERT INTO Usuario (id, nome, cpf, email, senha, grupo) VALUES (?, ?, ?, ?, ?, ?)");
		ps.setString(1, usuario.getId());
		ps.setString(2, usuario.getNome());
		ps.setString(3, usuario.getCpf());
		ps.setString(4, usuario.getEmail());
		ps.setString(5, usuario.getSenha());
		ps.setString(6, usuario.getGrupo());
		ps.execute();

		con.close();
	}

	public boolean validar(String email, String senha) throws SQLException {
		System.out.println("cheguei");
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("entrei no banco");
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		System.out.println("entrei no banco");
		String query = "select * from Usuario where email = ? and senha = ?";
		System.out.println("entrei no banco");
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senha);
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

}
