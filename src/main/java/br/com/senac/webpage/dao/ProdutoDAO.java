package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.Usuario;

@Repository
public class ProdutoDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/marketplace";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void inserir(Produto produto) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		var ps = con.prepareStatement("INSERT INTO produto (codigo, nome, descricao, quantidade, avaliacao, preco, situacao, link) VALUES ('?', '?', '?', '?', '?', '?', '?','?')");
		ps.setString(1, produto.getCodigo());
		ps.setString(2, produto.getNome());
		ps.setString(3, produto.getDescricao());
		ps.setString(4, produto.getQuantidade());
		ps.setString(5, produto.getAvaliacao());
		ps.setString(6, produto.getPreco());
		ps.setString(7, produto.getSituacao());
		ps.setString(8, produto.getLink());		
		ps.execute();

		con.close();
	}
}