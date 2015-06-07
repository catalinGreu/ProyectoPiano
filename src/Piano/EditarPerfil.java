package Piano;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditarPerfil extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApe;
	private JPasswordField passFieldPass;
	private String elBoton;
	private JLabel labelAviso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPerfil frame = new EditarPerfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getBotonPulsado() {
		
		return this.elBoton;
	}
	
	public String getNewName(){
		
		return textFieldNombre.getText();
	}
	
	public String getNewApe(){
		
		return textFieldApe.getText();
	}
	
	public String getNewPass(){
		
		return passFieldPass.getText();
	}
	/**
	 * Create the frame.
	 */
	public EditarPerfil() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarPerfil.class.getResource("/Piano/setting.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 327);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nuevo Nombre");
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombre.setBounds(147, 85, 106, 21);
		contentPane.add(lblNombre);
		
		JLabel lblId = new JLabel("Nuevo Apellido");
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblId.setBounds(147, 134, 106, 14);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Nueva Password");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPassword.setBounds(147, 174, 124, 21);
		contentPane.add(lblPassword);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setForeground(new Color(0, 0, 0));
		textFieldNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
		textFieldNombre.setBounds(281, 85, 115, 21);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApe = new JTextField();
		textFieldApe.setForeground(new Color(0, 0, 0));
		textFieldApe.setFont(new Font("SansSerif", Font.BOLD, 12));
		textFieldApe.setBounds(281, 131, 115, 21);
		contentPane.add(textFieldApe);
		textFieldApe.setColumns(10);
		
		passFieldPass = new JPasswordField();
		passFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

					elBoton = "ACEPTAR";
					setVisible( false );

				}
			}
		});
		passFieldPass.setForeground(new Color(0, 0, 0));
		passFieldPass.setFont(new Font("SansSerif", Font.BOLD, 12));
		passFieldPass.setBounds(281, 174, 115, 21);
		contentPane.add(passFieldPass);
		passFieldPass.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (textFieldNombre.getText().isEmpty() || textFieldApe.getText().isEmpty()  
					|| passFieldPass.getText().isEmpty() ){
					
					labelAviso.setText( "Faltan campos" );
					return;
					
				}
				
				if ( passFieldPass.getText().length() < 5 ) {
					
					labelAviso.setText( "5 caracteres minimo" );
					return;
				}
				
				elBoton = "ACEPTAR";
				setVisible( false );
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnNewButton.setBounds(182, 241, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				elBoton = "CANCELAR";
				setVisible( false );
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCancelar.setBounds(318, 241, 89, 23);
		contentPane.add(btnCancelar);
		
		labelAviso = new JLabel("");
		labelAviso.setHorizontalAlignment(SwingConstants.CENTER);
		labelAviso.setForeground(new Color(153, 0, 0));
		labelAviso.setFont(new Font("SansSerif", Font.BOLD, 12));
		labelAviso.setBounds(281, 32, 281, 21);
		contentPane.add(labelAviso);
	}
	
}
