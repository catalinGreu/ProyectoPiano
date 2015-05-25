package Piano;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.border.MatteBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.border.TitledBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel labelOK2;
	private JLabel labelOK;

	private EntityManager em;
	private UsuarioHibernate dao;
	private JTextField textFieldID;
	private JLabel lblConfirmacion;

	private JButton btnAceptar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration(null);

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
	private void setImageIcon() {
		URL iconImageURL=this.getClass().getResource("/Piano/logo.png");
		Image icon=null;
		try {
			icon = ImageIO.read(iconImageURL);
		} catch (IOException e2) {
			System.out.println("IOException!Icon image does not exist!");
		}
		setIconImage(icon);
	}

	public void setEntityManager( EntityManager em ) throws Exception {
		this.em = em;
		dao = new UsuarioHibernate( em );
	}

	public Registration( MouseAdapter mouseAdapter ) {

		setResizable(false);
		setBackground(new Color(153, 153, 153));
		setImageIcon();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 493);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 102));
		panel.setBounds(0, 0, 647, 464);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(108, 124, 61, 18);
		lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(256, 120, 157, 28);
		textFieldNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApelido = new JLabel("Apelido");
		lblApelido.setForeground(new Color(0, 0, 0));
		lblApelido.setHorizontalAlignment(SwingConstants.LEFT);
		lblApelido.setBounds(108, 181, 78, 18);
		lblApelido.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblApelido);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(256, 177, 157, 28);
		textFieldApellido.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(108, 287, 92, 18);
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {


			}
		});
		passwordField.setBounds(256, 284, 157, 28);
		passwordField.setColumns(14);
		panel.add(passwordField);

		JLabel lblConfirmPasswd = new JLabel("Confirm passwd");
		lblConfirmPasswd.setForeground(new Color(0, 0, 0));
		lblConfirmPasswd.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPasswd.setBounds(108, 338, 126, 18);
		lblConfirmPasswd.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblConfirmPasswd);

		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER ) {

					try {
						registraUsuario();
						btnAceptar.setVisible( false );
					} catch (Exception e1) {
						System.out.println("Se ha debido de presionar enter dos veces");
						//						e1.printStackTrace();
					}					
				}
			}
		});
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {				

				String s = passwordField_1.getText();
				String s1 = passwordField.getText();

				if ( passwordOK() ) {

					labelOK2.setIcon( new ImageIcon(Registration.class.getResource( "/Piano/ok2.png")) );
					labelOK.setIcon(  new ImageIcon(Registration.class.getResource( "/Piano/ok2.png")) );

					labelOK.setVisible(true);
					labelOK2.setVisible(true);
				}
				else {
					labelOK2.setIcon(  new ImageIcon(Registration.class.getResource( "/Piano/cross.png")) );
					labelOK.setIcon( new ImageIcon(Registration.class.getResource( "/Piano/cross.png")) );	

					labelOK.setVisible(true);
					labelOK2.setVisible(true);

				}
			}
		});
		passwordField_1.setBounds(256, 335, 157, 28);
		passwordField_1.setColumns(14);
		panel.add(passwordField_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Registration.class.getResource("/Piano/registro.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(81, 0, 492, 113);
		panel.add(lblNewLabel);

		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setVgap(10);
		panelButtons.setBounds(229, 397, 200, 45);
		panel.add(panelButtons);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e ) {

				if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

					try {
						registraUsuario();
//						btnAceptar.setVisible( false );
					} catch (Exception ex) {
						System.out.println("Igual se ha pulsado dos veces");
						//e.printStackTrace();
					}


				}
			}
		});
		btnAceptar.setForeground(new Color(0, 0, 0));
		btnAceptar.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					registraUsuario();
//					btnAceptar.setVisible( false );
				} catch (Exception ex) {
					System.out.println("Igual se ha pulsado dos veces");
					//e.printStackTrace();
				}
			}
		});
		btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelButtons.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setVisible( false );				
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelButtons.add(btnCancelar);

		labelOK = new JLabel("");
		labelOK.setIcon(null);
		labelOK.setBounds(432, 284, 39, 28);
		panel.add(labelOK);

		labelOK2 = new JLabel("");
		labelOK2.setIcon(null);
		labelOK2.setBounds(432, 336, 37, 27);
		panel.add(labelOK2);

		JLabel lblIdUsuario = new JLabel("ID Usuario");
		lblIdUsuario.setForeground(new Color(0, 0, 0));
		lblIdUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblIdUsuario.setBounds(108, 242, 78, 18);
		panel.add(lblIdUsuario);

		textFieldID = new JTextField();
		textFieldID.setBounds(256, 234, 157, 28);
		panel.add(textFieldID);
		textFieldID.setColumns(10);

		lblConfirmacion = new JLabel("");
		lblConfirmacion.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblConfirmacion.setBounds(471, 344, 176, 34);
		panel.add(lblConfirmacion);
	}

	public void registraUsuario() throws Exception {

		Usuario u = new Usuario();
		dao = new UsuarioHibernate( em );
		u.setNombre( textFieldNombre.getText() );
		u.setApellido( textFieldApellido.getText() );

		if ( passwordOK() ) {

			u.setPassword( passwordField.getText() );
		}
		else {
			lblConfirmacion.setText("Minimoi 5 caracteres");
		}

		u.setIDUser( textFieldID.getText() );

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();		
		this.dao.insert( u );		
		tx.commit();

		lblConfirmacion.setText("OK");		

		MyFirstPiano piano = new MyFirstPiano();
		piano.setVisible( true );
		piano.setResizable(false);
				
		piano.setUserConected( u, true );
		try {
			piano.setEntityManager(this.em);
			setVisible( false );
		} catch (Exception ex) {

			ex.printStackTrace();
		}		

		//		this.frame.setVisible(false);	


	}

	private boolean passwordOK() {

		String field1 = passwordField.getText();
		String field2 = passwordField_1.getText();

		//me deja meter espacios
		if ( field1.isEmpty() || field2.isEmpty() ) {

			return false;
		}

		if (field1.length() < 5 || field2.length() < 5) {

			return false;
		}
		if ( field1.equals(field2) ) {

			return true;
		}
		return false;
	}


}
