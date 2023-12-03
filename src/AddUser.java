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
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddUser extends JFrame {
	
	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField passwordText;
	private JTextField nomeText;
	private JTextField cognomeText;
	private JTextField telefonoText;
	private ButtonGroup ruolo;
	private static String[] campi = new String[6];
	static String regex = "^[0-9]*$";

	public AddUser(String user) {
		
		super("Aggiunta Utente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 600, 500, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(12, 34, 159, 15);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(12, 80, 159, 15);
		contentPane.add(lblPassword);
		
		JLabel lblname = new JLabel("Nome :");
		lblname.setBounds(12, 120, 159, 15);
		contentPane.add(lblname);
		
		JLabel lblcognome = new JLabel("Cognome :");
		lblcognome.setBounds(12, 160, 159, 15);
		contentPane.add(lblcognome);
		
		JLabel lbltelefonoText = new JLabel("Telefono :");
		lbltelefonoText.setBounds(12, 200, 159, 15);
		contentPane.add(lbltelefonoText);
		
		JLabel lblRuolo = new JLabel("Ruolo :");
		lblRuolo.setBounds(12, 260, 159, 15);
		contentPane.add(lblRuolo);
										
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 450, 117, 25);
		contentPane.add(btnCancel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(141, 450, 117, 25);
		contentPane.add(btnSubmit);
		
		usernameText = new JTextField();
		usernameText.setBounds(98, 29, 159, 25);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(98, 75, 159, 25);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		
		nomeText = new JTextField();
		nomeText.setBounds(98, 115, 159, 25);
		contentPane.add(nomeText);
		nomeText.setColumns(10);
		
		cognomeText = new JTextField();
		cognomeText.setBounds(98, 155, 159, 25);
		contentPane.add(cognomeText);
		cognomeText.setColumns(10);
		
		telefonoText = new JTextField();
		telefonoText.setBounds(98, 195, 159, 25);
		contentPane.add(telefonoText);
		telefonoText.setColumns(10);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				PannelloPrincipale p = new PannelloPrincipale(user);
				p.setVisible(true);
			}
		});
		
		btnSubmit.addActionListener(new ActionListener(){
			private RoleRadio rr;

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
				String password=passwordText.getText();
				String nome=nomeText.getText();
				String cognome=cognomeText.getText();
				String telefono=telefonoText.getText();
				
				if(password.length()<6) {
					JOptionPane.showMessageDialog(null, "Password must be at least 6 characters");
				}
				else if(telefono.length()>0 && !telefono.matches(regex)) {
					System.out.println(telefono.matches(regex));
					JOptionPane.showMessageDialog(null, "Phone number is incorrect");
				}
				else if(username.length()==0) {
					JOptionPane.showMessageDialog(null, "You must insert a username");
				}
				else if(nome.length()==0) {
					JOptionPane.showMessageDialog(null, "You must insert a name");
				}
				else if(cognome.length()==0) {
					JOptionPane.showMessageDialog(null, "You must insert a surname");
				}
				else if(CheckUsernameExists(username,c)){
					JOptionPane.showMessageDialog(null,"Username already exist");
				}
				else {
					campi[0] = username ;
					campi[1] = password;
					campi[2] = nome;
					campi[3] = cognome;
					campi[4] = telefono;
					
					/*JFrame roleButtons = new JFrame("Role");
					roleButtons.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					roleButtons.setSize(300, 300);
					roleButtons.setLayout(new GridLayout(2,1));
					

				    roleButtons.addWindowListener(new WindowAdapter() {
				         public void windowClosing(WindowEvent windowEvent){
				            System.exit(0);
				         }        
				      });    
				      /*JLabel headerLabel = new JLabel("", JLabel.CENTER);        
				      JLabel statusLabel = new JLabel("",JLabel.CENTER);    
				      statusLabel.setSize(350,100);

				      JPanel controlPanel = new JPanel();
				      controlPanel.setLayout(new FlowLayout());

				      roleButtons.add(headerLabel);
				      roleButtons.add(controlPanel);
				      roleButtons.add(statusLabel);
				      roleButtons.setVisible(true); 
				      
				      JRadioButton adminRadio= new JRadioButton("Amministratore");
				      JRadioButton staffRadio = new JRadioButton("User",true);
				      
				      ruolo = new ButtonGroup();
				      ruolo.add(adminRadio);
				      ruolo.add(staffRadio);
						
				      controlPanel.add(adminRadio);
				      controlPanel.add(staffRadio);
				      
				      if (adminRadio.isSelected()==true) {
				    	  campi[5] = "A";
				      }
				      else if(staffRadio.isSelected()==true) {
				    	  campi[5] = "U";
				      }*/
				      
					rr = new RoleRadio(campi);
				    rr.setVisible(true);
				      
						
				      
				      
					
					try {
						PreparedStatement st=c.prepareStatement("INSERT INTO staff VALUES(?,?,?,?,?,?)");
						st.setString(1, campi[0]);
						st.setString(2, campi[1]);
						st.setString(3, campi[2]);
						st.setString(4, campi[3]);
						if(campi[4] == null) {
							st.setNull(5, Types.VARCHAR);
						}
						else {
							st.setString(5, campi[4]);
						}
						
						
					}
					catch(SQLException e){
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

