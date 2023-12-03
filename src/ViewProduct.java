import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewProduct extends JFrame{
	private JPanel contentPane;

	
	public ViewProduct(String id, Cart cart, String user) {
	
		super("Product Details");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 720);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel titleD= new JLabel();
		titleD.setBounds(38, 45, 343, 34);
		contentPane.add(titleD);
		setVisible(true);
		
		JLabel bandD = new JLabel();
		bandD.setBounds(38, 95, 343, 34);
		contentPane.add(bandD);
		bandD.setVisible(true);
		
		JLabel musicians = new JLabel();
		musicians.setBounds(38, 145, 295, 34);
		contentPane.add(musicians);
		musicians.setVisible(true);
		
		JLabel genre = new JLabel();
		genre.setBounds(38, 195, 343, 34);
		contentPane.add(genre);
		genre.setVisible(true);
		
		JLabel titleSongs = new JLabel();
		titleSongs.setBounds(38, 245, 343, 34);
		contentPane.add(titleSongs);
		titleSongs.setVisible(true);
		
		JLabel description = new JLabel();
		description.setBounds(38, 295, 500, 34);
		contentPane.add(description);
		description.setVisible(true);
		
		JLabel date = new JLabel();
		date.setBounds(38, 345, 343, 34);
		contentPane.add(date);
		date.setVisible(true);
		
		JLabel priceD = new JLabel();
		priceD.setBounds(38, 395, 343, 34);
		contentPane.add(priceD);
		priceD.setVisible(true);
		
		JLabel giacenza = new JLabel();
		giacenza.setBounds(38,450,343,34);
		contentPane.add(giacenza);
		giacenza.setVisible(true);
		
		/*JLabel photo = new JLabel();
		photo.setBounds(38, 181, 70, 15);
		contentPane.add(photo);
		photo.setVisible(true);*/
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(39, 674, 117, 25);
		contentPane.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});

		Connection c = null;
		// connection to database
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cdshop","postgres", "postgres");
			String query = "select * from \"CD\" where cd_id ="+id;
			Statement st = c.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				titleD.setText("Title :   "+rs.getString(1));
				bandD.setText("Band :   "+rs.getString(4));
				musicians.setText("Musicians :   "+rs.getString(11));
				genre.setText("Genre :   " +rs.getString(6));
				titleSongs.setText("Songs :   "+rs.getString(9));
				description.setText("Description :   " +rs.getString(5));
				date.setText("Date since available :   " +rs.getString(3));
				if(user != "admin" && user != "staff") {
					if(rs.getString(12) != "0") {
						giacenza.setText("Il disco è Disponibile");
					}
					else {
						giacenza.setText("Il disco Non è Disponibile");
					}
				}
				else {
					giacenza.setText("Magazzino : "+rs.getString(12));
				}
				
				double p = (rs.getDouble(2)*100)/100;
				String pric = String.valueOf(p);
				priceD.setText("Price :   " +pric);
				
				if(rs.getString(10) != null) {
					BufferedImage img = ImageIO.read(new File(rs.getString(10)));
					ImageIcon imgicon = new ImageIcon(img);
					JLabel photo = new JLabel(imgicon);
					contentPane.add(photo);
					photo.setBounds(38,550,100,100);
					photo.setVisible(true);
				}
			}
						
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}

		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(193, 674, 117, 25);
		contentPane.add(btnAddToCart);

		btnAddToCart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				if (cart.inCart(id) == false){
					cart.addToCart(id);
					JOptionPane.showMessageDialog(null, "The CD has been succesfully added to cart!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "The CD is alredy in the cart!");
					dispose();
				}
			}
		});
	}
}
