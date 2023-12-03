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

public class RoleRadio extends JFrame {
	
	private JPanel contentPane;
	private ButtonGroup ruoloGroup;
	private JRadioButton adminRadio;
	private JRadioButton staffRadio;
	private JLabel lblRuolo;
	
	public RoleRadio(String[] campi){
		
		super("Role");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 600, 500, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		
		lblRuolo = new JLabel("Which Role:");
		lblRuolo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblRuolo);
		
		adminRadio = new JRadioButton("Administrator");
		staffRadio = new JRadioButton("Normal User",true);
		
		ruoloGroup = new ButtonGroup();
		ruoloGroup.add(adminRadio);
		ruoloGroup.add(staffRadio);
		
		add(adminRadio);
		add(staffRadio);
		
		JButton submit = new JButton("OK");
		submit.setBounds(50,50,50,50);
		contentPane.add(submit);
		
		pack();
		
		submit.addActionListener(new ActionListener(){
			

			public void actionPerformed(ActionEvent s) {
				
				
				
			}
		});
		
		
		
		
		
		
	}

}
