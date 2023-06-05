package br.com.senac.webpage.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import br.com.senac.webpage.model.Produto;
import br.com.senac.webpage.model.ProdutoDto;

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
	
//	
	public void atualizaQuantidadeTenisUm(ProdutoDto produtoDto) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		int i = produtoDAO.primeiroTenisValorAntigo();
		i = i - produtoDto.getTenis1();
		PreparedStatement preparedStatement;
		int resultSet;
		String query = "update produto SET quantidade = ? WHERE nome = 'TÊNIS NIKE AIR FORCE 1 SHADOW FEMININO'";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, Integer.toString(i));
		resultSet = preparedStatement.executeUpdate();
		
		}
	public void atualizaQuantidadeTenisDois(ProdutoDto produtoDto) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		int i = produtoDAO.segundoTenisValorAntigo();
		i = i - produtoDto.getTenis1();
		PreparedStatement preparedStatement;
		int resultSet;
		String query = "update produto SET quantidade = ? WHERE nome = 'TÊNIS NIKE AIR FORCE 1 FONTANKA FEMININOO'";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, Integer.toString(i));
		resultSet = preparedStatement.executeUpdate();
		
		}
	

	
	public void atualizaQuantidadeTenisTres(ProdutoDto produtoDto) throws SQLException {
			var con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			int i = produtoDAO.terceiroTenisValorAntigo();
			i = i - produtoDto.getTenis1();
			PreparedStatement preparedStatement;
			int resultSet;
			String query = "update produto SET quantidade = ? WHERE nome = 'TÊNIS NIKE AIR FORCE 1 07 SHROUD'";

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, Integer.toString(i));
			resultSet = preparedStatement.executeUpdate();
			
			}
	
	public void atualizaQuantidadeTenisQuatro(ProdutoDto produtoDto) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		int i = produtoDAO.quartoTenisValorAntigo();
		i = i - produtoDto.getTenis1();
		PreparedStatement preparedStatement;
		int resultSet;
		String query = "update produto SET quantidade = ? WHERE nome = 'TÊNIS NIKE AIR FORCE 1 07 SPACE JAM MASCULINO'";

		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, Integer.toString(i));
		resultSet = preparedStatement.executeUpdate();
		
		}	
	
	public int primeiroTenisValorAntigo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select quantidade from Produto WHERE nome = 'TÊNIS NIKE AIR FORCE 1 SHADOW FEMININO'";
		int i = 0;
		preparedStatement = con.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			 i = Integer.parseInt(resultSet.getString(1));
	}
		System.out.println(i + "retorno");
	
		return i;
		
		}
	public int segundoTenisValorAntigo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select quantidade from Produto WHERE nome = 'TÊNIS NIKE AIR FORCE 1 FONTANKA FEMININOO'";
		int i = 0;
		preparedStatement = con.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			 i = Integer.parseInt(resultSet.getString(1));
	}
		System.out.println(i + "retorno");
	
		return i;
		
		}
	
	public int terceiroTenisValorAntigo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select quantidade from Produto WHERE nome = 'TÊNIS NIKE AIR FORCE 1 07 SHROUD'";
		int i = 0;
		preparedStatement = con.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			 i = Integer.parseInt(resultSet.getString(1));
	}
		System.out.println(i + "retorno");
	
		return i;
		
		}
	
	public int quartoTenisValorAntigo() throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASSWORD);

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String query = "select quantidade from Produto WHERE nome = 'TÊNIS NIKE AIR FORCE 1 07 SPACE JAM MASCULINO'";
		int i = 0;
		preparedStatement = con.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()){
			 i = Integer.parseInt(resultSet.getString(1));
	}
		System.out.println(i + "retorno");
	
		return i;
		
		}
}
