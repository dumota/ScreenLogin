package classe_de_conexão;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Tela_cadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfid;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfBusca;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
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
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 622);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("A\u00E7\u00F4es");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfUsuario.getText().equals("")|| tfSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "usuario ou senha invalidos!");
				}else {
					Acoes ac = new Acoes(tfUsuario.getText(), tfSenha.getText());
					ac.salvar();
					
					limpar();
					
					
					
					
				}
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Update");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfUsuario.getText().equals("")|| tfSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "usuario ou senha invalidos!");
				}else {
					Acoes ac = new Acoes(Integer.parseInt(tfid.getText()), tfUsuario.getText(), tfSenha.getText());
					ac.Update();
					limpar();
				}
				
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(19, 167, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(19, 219, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(19, 274, 46, 14);
		contentPane.add(lblNewLabel_2);

		tfid = new JTextField();
		tfid.setEditable(false);
		tfid.setBounds(75, 164, 86, 20);
		contentPane.add(tfid);
		tfid.setColumns(10);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(75, 216, 139, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);

		tfSenha = new JTextField();
		tfSenha.setBounds(75, 271, 139, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pesquisar por id",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(245, 219, 189, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		tfBusca = new JTextField();
		tfBusca.setBounds(105, 22, 57, 20);
		panel_1.add(tfBusca);
		tfBusca.setColumns(10);

		JButton btnNewButton_1 = new JButton("Abrir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "informe o ID");
				} else {

					try {
						Connection con = Conexao.cn();
						String sql = "select * from dados_senhas where id like ?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, "%" + tfBusca.getText());
						ResultSet rs = ps.executeQuery();

						while (rs.next()) {
							tfid.setText(rs.getString("id"));
							tfUsuario.setText(rs.getString("usuario"));
							tfSenha.setText(rs.getString("senha"));
						}
						rs.close();
						con.close();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btnNewButton_1.setBounds(10, 21, 85, 23);
		panel_1.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 11, 401, 126);
		contentPane.add(scrollPane);

		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Id", "Usu\u00E1rio", "Senha" }));
		scrollPane.setViewportView(tbDados);

		JButton btnNewButton_2 = new JButton("Listar Dados");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.cn();
					String sql = "select * from dados_senhas";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					DefaultTableModel model = (DefaultTableModel) tbDados.getModel();
					model.setNumRows(0);

					while (rs.next()) {
						model.addRow(
								new Object[] { rs.getString("id"), rs.getString("usuario"), rs.getString("senha") });
					}
					rs.close();
					con.close();

				} catch (SQLException s) {
					s.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(298, 148, 122, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(10, 322, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor informar um Id");
				} else {

					try {
						Connection con = Conexao.cn();
						String sql = "update dados_senhas set usuario=?,senha=? where id=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, tfUsuario.getText());
						ps.setString(2, tfSenha.getText());
						ps.setString(3, tfid.getText());

						ps.execute();
						ps.close();
						con.close();

						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
						
						tfid.setText("");
						tfUsuario.setText("");
						tfSenha.setText("");
					

					} catch (SQLException s) {
						s.printStackTrace();
					}
				}
			}
		});
		btnNewButton_3.setBounds(312, 298, 122, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Deletar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "informe o Id ");
				} else {

					try {
						Connection con = Conexao.cn();
						String sql = "delete from dados_senhas where id=?";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, tfid.getText());

						ps.execute();
						ps.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

						tfUsuario.setText("");
						tfSenha.setText("");
						tfid.setText("");

					} catch (SQLException e2) {
						// TODO: handle exception
					}
				}

			}
		});
		btnNewButton_4.setBounds(312, 332, 122, 23);
		contentPane.add(btnNewButton_4);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfUsuario.getText().equals("") || tfSenha.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Usuario ou senha invalidos, tente novamente!");
				} else {

					try {
						Connection con = Conexao.cn();
						String sql = "insert into dados_senhas (usuario,senha) value (?,?) ";

						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, tfUsuario.getText());
						ps.setString(2, tfSenha.getText());
						ps.execute();
						ps.close();
						con.close();
						JOptionPane.showMessageDialog(null, "usuario cadastrado com sucesso");

						tfUsuario.setText("");
						tfSenha.setText("");

					} catch (SQLException e) {
						e.printStackTrace();

					}
				}

			}
		});
	}
	public void limpar() {
		tfid.setText("");
		tfUsuario.setText("");
		tfSenha.setText("");
	}
}
