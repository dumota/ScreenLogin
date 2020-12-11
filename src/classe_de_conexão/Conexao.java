package classe_de_conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String caminho = "jdbc:mysql://localhost:3306/db_senhas?useTimezone=true&serverTimezone=UTC";
	private static String usuario = "root";
	private static String senha = "kaiabesuruaraiama";
	
	
	
	
	
	public static Connection cn() throws SQLException{
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(caminho,usuario,senha);
			
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}

	}



	

}
