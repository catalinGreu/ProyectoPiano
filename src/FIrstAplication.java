import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;


public class FIrstAplication {

	private JFrame frmMiAplicacion;
	private JTextField textField;
	private JPasswordField passwordField;
	private static String USUARIO = "catalin";
	private static String PASS = "12345";

	/**
	 * Launch the application.
	 */
	public static FIrstAplication window;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window = new FIrstAplication();
					window.frmMiAplicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FIrstAplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiAplicacion = new JFrame();
		frmMiAplicacion.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		frmMiAplicacion.getContentPane().setForeground(new Color(30, 144, 255));
		frmMiAplicacion.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmMiAplicacion.setForeground(Color.BLUE);
		frmMiAplicacion.setTitle("Programa Chulo");
		frmMiAplicacion.setBackground(Color.BLUE);
		frmMiAplicacion.setBounds(100, 100, 480, 319);
		frmMiAplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMiAplicacion.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(178, 85, 146, 25);
		frmMiAplicacion.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(79, 85, 55, 17);
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frmMiAplicacion.getContentPane().add(lblNombre);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(79, 152, 77, 17);
		lblContrasea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frmMiAplicacion.getContentPane().add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(178, 152, 146, 25);
		frmMiAplicacion.getContentPane().add(passwordField);

		JLabel label = new JLabel("");
		label.setBounds(79, 24, 288, 25);
		frmMiAplicacion.getContentPane().add(label);
		Color c = new Color(255, 0, 0);
		label.setForeground( c );

		JButton btnEnviar = new JButton("Aceptar");
		btnEnviar.setBounds(79, 222, 110, 32);
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usuEntrada = textField.getText();
				@SuppressWarnings("deprecation")
				String passEntrada = passwordField.getText();

				if (  !(usuEntrada.equals( USUARIO ) ) || !(passEntrada.equals( PASS ) ) ){

					label.setText("ID o password incorrectos");					
				}
				
				if (  usuEntrada.equals("") )  {

					label.setText("Debe introducir usuario.");					
				}
				if (  passEntrada.equals("") )  {

					label.setText("Debe introducir contraseña.");					
				}
				if ( usuEntrada.equals(USUARIO) && passEntrada.equals(PASS)) {

					label.setText("");	
					window.frmMiAplicacion.setVisible(false);
					Ventana2.main(null);
					
				}
			}
		});
		frmMiAplicacion.getContentPane().add(btnEnviar);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(242, 222, 110, 32);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		frmMiAplicacion.getContentPane().add(btnNewButton);


	}
}
