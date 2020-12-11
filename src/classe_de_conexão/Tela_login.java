package classe_de_conexão;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_login extends JFrame {

	private JPanel contentPane;
	private JTextField tfusuario;
	private JPasswordField tfsenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_login frame = new Tela_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_login() {
		setResizable(false);
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Usuario = new JLabel("Usuario");
		Usuario.setForeground(Color.BLUE);
		Usuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		Usuario.setBounds(34, 62, 82, 37);
		contentPane.add(Usuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.BLUE);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSenha.setBounds(34, 164, 138, 37);
		contentPane.add(lblSenha);
		
		tfusuario = new JTextField();
		tfusuario.setBounds(138, 69, 206, 27);
		contentPane.add(tfusuario);
		tfusuario.setColumns(10);
		
		tfsenha = new JPasswordField();
		tfsenha.setBounds(138, 164, 206, 27);
		contentPane.add(tfsenha);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.cn();
					
					String sql = "select * from dados_senhas where usuario=? and senha=?";
					
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, tfusuario.getText());
					ps.setString(2, new String (tfsenha.getPassword()));
					
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						Tela_cadastro exibir = new Tela_cadastro();
						exibir.setVisible(true);
						
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Esse usuario/senha não existe");
					}
					rs.close();
					con.close();
							
							
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(255, 230, 89, 23);
		contentPane.add(btnNewButton);
	}
}
