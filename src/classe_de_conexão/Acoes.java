package classe_de_conexão;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Acoes {
	
	private int id;
	private String usuario;
	private String senha;
	
	public Acoes(int id) {
		
		this.id = id;
		
	}
	
	
	public Acoes(String usuario, String senha) {
		this.usuario = usuario;
		this.senha   = senha;
	}
	
	public Acoes(int id , String usuario, String senha) {
		this.id      = id;
		this.usuario = usuario;
		this.senha   = senha;
	}
	//método salvar
	public void salvar() {
		try {
			Connection con = Conexao.cn();
			String sql = "insert into dados_senhas (usuario,senha) value (?,?) ";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, senha);
			ps.execute();
			ps.close();
			con.close();
			JOptionPane.showMessageDialog(null, "usuario cadastrado com sucesso");

	

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}//fim
	public void Update() {
		try {
			Connection con = Conexao.cn();
			String sql = "update dados_senhas set usuario=?,senha=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, senha);
			ps.setInt(3, id);

			ps.execute();
			ps.close();
			con.close();

			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
			
			
		

		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

}
