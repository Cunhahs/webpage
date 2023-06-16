package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.ProdutoAllDto;

@Repository
public class CompraDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/marketplace";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void inserir(Produto produto) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		var ps = con.prepareStatement(
				"INSERT INTO produto (codigo, nome, descricao, quantidade, avaliacao, preco, situacao, link) VALUES ('?', '?', '?', '?', '?', '?', '?','?')");
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

//		
	public List<ProdutoAllDto> findAll() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		String sql = "select * from produto;";
		Statement stm = con.createStatement();
		ResultSet ps = stm.executeQuery(sql);
		List<ProdutoAllDto> listAll = new ArrayList<ProdutoAllDto>();

		while (ps.next()) {
			ProdutoAllDto n = new ProdutoAllDto();

			n.setCodigo(ps.getString("codigo"));
			n.setNome(ps.getString("nome"));
			n.setDescricao(ps.getString("descricao"));
			n.setQuantidade(Integer.parseInt(ps.getString("quantidade")));
			n.setAvaliacao(ps.getString("avaliacao"));
			n.setPreco(Double.parseDouble(ps.getString("preco").replace("$", "").replace("R", "").replace(",", ".")));
			n.setSituacao(ps.getString("situacao"));
			n.setLinkImg("images/" + ps.getString("link"));
			System.out.println(n.getLinkImg());

			listAll.add(n);
			System.out.println("Funcionou");
		}

		System.out.println("Adicionou todos");

		return listAll;
	}

	public ProdutoAllDto getProdutoCarrinho(String produtoDesejado) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		List<ProdutoAllDto> listAll = new ArrayList<ProdutoAllDto>();
			
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			String query = "select * from produto where nome = ?";

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, produtoDesejado);
		
			ResultSet ps = preparedStatement.executeQuery();

			ProdutoAllDto produto = new ProdutoAllDto();			
			while (ps.next()) {
				produto.setNome(ps.getString("nome"));
				produto.setAvaliacao(ps.getString("avaliacao"));
				produto.setCodigo(ps.getString("codigo"));
				produto.setDescricao(ps.getString("descricao"));
				produto.setLinkImg("images/" + ps.getString("link"));
				produto.setPreco(Double.parseDouble(ps.getString("preco").replace("$", "").replace("R", "").replace(",", ".")));
				produto.setQuantidade(Integer.parseInt(ps.getString("quantidade")));
				produto.setSituacao(ps.getString("situacao"));
				
				produto.setValorTotal(produto.getPreco());

				System.out.println("ADICIONOU 1" + produto.getNome());

		}
			
		return produto;
	}
}
