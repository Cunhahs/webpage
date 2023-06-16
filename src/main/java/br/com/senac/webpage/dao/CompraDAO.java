
package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Compra;

@Repository
public class CompraDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/marketplace";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void inserir(Compra compra) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		var ps = con.prepareStatement(
				"INSERT INTO compra (idUsuario, cidade, estado, logradouro, numero, complemento, cep, bairro, nome) VALUES ('?', '?', '?', '?', '?', '?', '?','?', '?')");
		
		ps.setString(1, compra.getIdUsuario());
		ps.setString(2, compra.getCidade());
		ps.setString(3, compra.getEstado());
		ps.setString(4, compra.getLogradouro());
		ps.setString(5, compra.getNumero());
		ps.setString(6, compra.getComplemento());
		ps.setString(7, compra.getCep());
		ps.setString(8, compra.getBairro());
		ps.setString(9, compra.getNome());
		ps.execute();

		con.close();
	}

//		
	public List<Compra> findAll() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		String sql = "select * from compra;";
		Statement stm = con.createStatement();
		ResultSet ps = stm.executeQuery(sql);
		List<Compra> listAll = new ArrayList<Compra>();

		while (ps.next()) {
			Compra compra = new Compra();
			
			compra.setBairro(ps.getString("bairro"));
			compra.setCep(ps.getString("cep"));
			compra.setCidade(ps.getString("cidade"));
			compra.setComplemento(ps.getString("complemento"));
			compra.setEstado(ps.getString("estado"));
			compra.setIdUsuario(ps.getString("idUsuario"));
			compra.setLogradouro(ps.getString("logradouro"));
			compra.setNome(ps.getString("nome"));
			compra.setNumero(ps.getString("numero"));

			listAll.add(compra);
			System.out.println("Funcionou");
		}

		System.out.println("Adicionou todos");

		return listAll;
	}


public List<Compra> getProdutoCarrinho(String idUsuario) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		List<Compra> listAll = new ArrayList<Compra>();
			
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			String query = "select * from compra where id = ?";

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, idUsuario);
		
			ResultSet ps = preparedStatement.executeQuery();
			
			while (ps.next()) {

				Compra compra = new Compra();
				compra.setBairro(ps.getString("bairro"));
				compra.setCep(ps.getString("cep"));
				compra.setCidade(ps.getString("cidade"));
				compra.setComplemento(ps.getString("complemento"));
				compra.setEstado(ps.getString("estado"));
				compra.setIdUsuario(ps.getString("idUsuario"));
				compra.setLogradouro(ps.getString("logradouro"));
				compra.setNome(ps.getString("nome"));
				compra.setNumero(ps.getString("numero"));
				listAll.add(compra);
				
				//produto.setValorTotal(produto.getPreco());

				//System.out.println("ADICIONOU 1" + produto.getNome());

		}		
		return listAll;
	}

}