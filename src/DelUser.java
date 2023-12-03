import java.awt.BorderLayout;

import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;


public class DelUser extends JFrame {
	
	private JPanel contentPane;
	private JTextField usernameText;
	private static String username;
	static String regex = "^[a-zA-Z]+$";
	
	public DelUser(String user) {
		
		super("Deleting a User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 170);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(12, 34, 159, 15);
		contentPane.add(lblUsername);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 125, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(141, 125, 117, 25);
		contentPane.add(btnSubmit);
		
		usernameText = new JTextField();
		usernameText.setBounds(98, 29, 159, 25);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent s) {
				// TODO Auto-generated method stub
				//JButton b=(JButton) e.getSource()
				//chiudere prototipo e poplogin e aprire nuovamente prototipo
				//dispose();
				
				Connection c = null;

				
				// connection to database
				try {
					Class.forName("org.postgresql.Driver");
					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cdshop","postgres", "postgres");
				} catch (Exception e) {
					System.err.println( e.getClass().getName()+": "+ e.getMessage() );
					System.exit(0);
				}
				
				String username=usernameText.getText();
				
				DelUser.this.username = username;
				boolean check=false;
				
				if (username.isEmpty()){
					JOptionPane.showMessageDialog(null,"You must insert an username!");
					DelUser au = new DelUser(user);
					au.setVisible(true);
					return;
				}else if(!CheckUsernameExists(username,c)){
					JOptionPane.showMessageDialog(null, "This username does not exists");
					PannelloPrincipale pp = new PannelloPrincipale(user);
					pp.setVisible(true);
				}
				else if(CheckUsernameExists(username,c)) {
					try {
						PreparedStatement st = c.prepareStatement("DELETE FROM \"staff\" WHERE username = ? ");
						st.setString(1,username);
						int rows = st.executeUpdate();
						if(rows > 0) {
							JOptionPane.showMessageDialog(null, "The user was successfully deleted!");
							dispose();
							//PannelloPrincipale pp = new PannelloPrincipale(user);
							//pp.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "A problem occur.. please try again");
							DelUser au = new DelUser(user);
							au.setVisible(true);
						}
						
					}
					catch (SQLException e) {
						System.out.println("SQL Exception: " + e.toString());
					}
				}

				
			}
		
			});
	}
	
			
			public boolean CheckUsernameExists(String username, Connection c) {

				boolean usernameExists = false;
				
				try {
					PreparedStatement st = c.prepareStatement("select username from \"staff\"");
					ResultSet r1 = st.executeQuery();
					while(r1.next()){
						String db_username = r1.getObject(1).toString();
						if(db_username.equals(username)){
							usernameExists = true;
							break;
						}
					}
				}
				catch (SQLException e) {
					System.out.println("SQL Exception: " + e.toString());
				}
				return usernameExists;
			}
}
