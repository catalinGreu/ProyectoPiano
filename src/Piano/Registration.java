package Piano;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;

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

	Usuario u;
	private JButton btnAceptar;
	private JPanel panel_1;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 102));
		contentPane.setForeground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{1034, 0};
		gbl_contentPane.rowHeights = new int[]{676, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 153, 102));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 0;
				contentPane.add(panel, gbc_panel);
						GridBagLayout gbl_panel = new GridBagLayout();
						gbl_panel.columnWidths = new int[]{81, 134, 0, 17, 173, 215, 0};
						gbl_panel.rowHeights = new int[]{113, 28, 28, 28, 28, 28, 34, 45, 0};
						gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
						gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						panel.setLayout(gbl_panel);
						
								JLabel lblNewLabel = new JLabel("");
								lblNewLabel.setIcon(new ImageIcon(Registration.class.getResource("/Piano/registro.png")));
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
								gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
								gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
								gbc_lblNewLabel.gridwidth = 5;
								gbc_lblNewLabel.gridx = 1;
								gbc_lblNewLabel.gridy = 0;
								panel.add(lblNewLabel, gbc_lblNewLabel);
								
										JLabel lblNombre = new JLabel("Nombre");
										lblNombre.setForeground(new Color(0, 0, 0));
										lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
										lblNombre.setFont(new Font("SansSerif", Font.BOLD, 13));
										GridBagConstraints gbc_lblNombre = new GridBagConstraints();
										gbc_lblNombre.anchor = GridBagConstraints.EAST;
										gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
										gbc_lblNombre.gridwidth = 3;
										gbc_lblNombre.gridx = 1;
										gbc_lblNombre.gridy = 1;
										panel.add(lblNombre, gbc_lblNombre);
										
												textFieldNombre = new JTextField();
												textFieldNombre.addFocusListener(new FocusAdapter() {
													@Override
													public void focusGained(FocusEvent arg0) {
														textFieldNombre.selectAll();
														textFieldNombre.setSelectionColor( new Color ( 51, 102, 255 ) );
														textFieldNombre.setSelectedTextColor( new Color(255, 255, 255) );

													}
												});
												textFieldNombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
												GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
												gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
												gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
												gbc_textFieldNombre.gridx = 4;
												gbc_textFieldNombre.gridy = 1;
												panel.add(textFieldNombre, gbc_textFieldNombre);
												textFieldNombre.setColumns(10);
								
										textFieldID = new JTextField();
										textFieldID.addFocusListener(new FocusAdapter() {
											@Override
											public void focusGained(FocusEvent e) {
												textFieldID.selectAll();
												textFieldID.setSelectionColor( new Color ( 51, 102, 255 ) );
												textFieldID.setSelectedTextColor( new Color(255, 255, 255) );
											}
										});
												
														textFieldApellido = new JTextField();
														textFieldApellido.addFocusListener(new FocusAdapter() {
															@Override
															public void focusGained(FocusEvent e) {
																textFieldApellido.selectAll();
																textFieldApellido.setSelectionColor( new Color ( 51, 102, 255 ) );
																textFieldApellido.setSelectedTextColor( new Color(255, 255, 255) );
															}
														});
														
																JLabel lblApelido = new JLabel("Apelido");
																lblApelido.setForeground(new Color(0, 0, 0));
																lblApelido.setHorizontalAlignment(SwingConstants.LEFT);
																lblApelido.setFont(new Font("SansSerif", Font.BOLD, 13));
																GridBagConstraints gbc_lblApelido = new GridBagConstraints();
																gbc_lblApelido.gridwidth = 2;
																gbc_lblApelido.anchor = GridBagConstraints.EAST;
																gbc_lblApelido.insets = new Insets(0, 0, 5, 5);
																gbc_lblApelido.gridx = 2;
																gbc_lblApelido.gridy = 2;
																panel.add(lblApelido, gbc_lblApelido);
														textFieldApellido.setFont(new Font("SansSerif", Font.PLAIN, 12));
														GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
														gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
														gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
														gbc_textFieldApellido.gridx = 4;
														gbc_textFieldApellido.gridy = 2;
														panel.add(textFieldApellido, gbc_textFieldApellido);
														textFieldApellido.setColumns(10);
										
												JLabel lblIdUsuario = new JLabel("ID Usuario");
												lblIdUsuario.setForeground(new Color(0, 0, 0));
												lblIdUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
												GridBagConstraints gbc_lblIdUsuario = new GridBagConstraints();
												gbc_lblIdUsuario.anchor = GridBagConstraints.SOUTHEAST;
												gbc_lblIdUsuario.insets = new Insets(0, 0, 5, 5);
												gbc_lblIdUsuario.gridwidth = 3;
												gbc_lblIdUsuario.gridx = 1;
												gbc_lblIdUsuario.gridy = 3;
												panel.add(lblIdUsuario, gbc_lblIdUsuario);
										GridBagConstraints gbc_textFieldID = new GridBagConstraints();
										gbc_textFieldID.fill = GridBagConstraints.BOTH;
										gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
										gbc_textFieldID.gridx = 4;
										gbc_textFieldID.gridy = 3;
										panel.add(textFieldID, gbc_textFieldID);
										textFieldID.setColumns(10);
												
														JLabel lblPassword = new JLabel("Password");
														lblPassword.setForeground(new Color(0, 0, 0));
														lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
														lblPassword.setFont(new Font("SansSerif", Font.BOLD, 13));
														GridBagConstraints gbc_lblPassword = new GridBagConstraints();
														gbc_lblPassword.anchor = GridBagConstraints.EAST;
														gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
														gbc_lblPassword.gridwidth = 3;
														gbc_lblPassword.gridx = 1;
														gbc_lblPassword.gridy = 4;
														panel.add(lblPassword, gbc_lblPassword);
												
														passwordField = new JPasswordField();
														passwordField.addFocusListener(new FocusAdapter() {
															@Override
															public void focusGained(FocusEvent e) {
																passwordField.selectAll();
																passwordField.setSelectionColor( new Color ( 51, 102, 255 ) );
																passwordField.setSelectedTextColor( new Color(255, 255, 255) );
															}
														});
														passwordField.setColumns(14);
														GridBagConstraints gbc_passwordField = new GridBagConstraints();
														gbc_passwordField.fill = GridBagConstraints.BOTH;
														gbc_passwordField.insets = new Insets(0, 0, 5, 5);
														gbc_passwordField.gridx = 4;
														gbc_passwordField.gridy = 4;
														panel.add(passwordField, gbc_passwordField);
										
												labelOK = new JLabel("");
												labelOK.setIcon(null);
												GridBagConstraints gbc_labelOK = new GridBagConstraints();
												gbc_labelOK.anchor = GridBagConstraints.WEST;
												gbc_labelOK.fill = GridBagConstraints.VERTICAL;
												gbc_labelOK.insets = new Insets(0, 0, 5, 0);
												gbc_labelOK.gridx = 5;
												gbc_labelOK.gridy = 4;
												panel.add(labelOK, gbc_labelOK);
										
												JLabel lblConfirmPasswd = new JLabel("Confirm passwd");
												lblConfirmPasswd.setForeground(new Color(0, 0, 0));
												lblConfirmPasswd.setHorizontalAlignment(SwingConstants.LEFT);
												lblConfirmPasswd.setFont(new Font("SansSerif", Font.BOLD, 13));
												GridBagConstraints gbc_lblConfirmPasswd = new GridBagConstraints();
												gbc_lblConfirmPasswd.anchor = GridBagConstraints.EAST;
												gbc_lblConfirmPasswd.insets = new Insets(0, 0, 5, 5);
												gbc_lblConfirmPasswd.gridwidth = 3;
												gbc_lblConfirmPasswd.gridx = 1;
												gbc_lblConfirmPasswd.gridy = 5;
												panel.add(lblConfirmPasswd, gbc_lblConfirmPasswd);
										
												passwordField_1 = new JPasswordField();
												passwordField_1.addKeyListener(new KeyAdapter() {
													@Override
													public void keyPressed(KeyEvent e) {
														if (e.getKeyCode() == KeyEvent.VK_ENTER ) {

															try {
																registraUsuario();
															} catch (Exception e1) {
																System.out.println("Se ha debido de presionar enter dos veces");
															}					
														}
													}
												});
												passwordField_1.addFocusListener(new FocusAdapter() {
													@Override
													public void focusLost(FocusEvent arg0) {

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
													@Override
													public void focusGained(FocusEvent e) {
														passwordField_1.selectAll();
														passwordField_1.setSelectionColor( new Color ( 51, 102, 255 ) );
														passwordField_1.setSelectedTextColor( new Color(255, 255, 255) );

													}
												});
												passwordField_1.setColumns(14);
												GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
												gbc_passwordField_1.fill = GridBagConstraints.BOTH;
												gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
												gbc_passwordField_1.gridx = 4;
												gbc_passwordField_1.gridy = 5;
												panel.add(passwordField_1, gbc_passwordField_1);
										
												labelOK2 = new JLabel("");
												labelOK2.setIcon(null);
												GridBagConstraints gbc_labelOK2 = new GridBagConstraints();
												gbc_labelOK2.anchor = GridBagConstraints.WEST;
												gbc_labelOK2.fill = GridBagConstraints.VERTICAL;
												gbc_labelOK2.insets = new Insets(0, 0, 5, 0);
												gbc_labelOK2.gridx = 5;
												gbc_labelOK2.gridy = 5;
												panel.add(labelOK2, gbc_labelOK2);
								
										JPanel panelButtons = new JPanel();
										panelButtons.setBackground(new Color(0, 153, 102));
										panelButtons.setBorder(new EmptyBorder(0, 0, 0, 0));
										FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
										flowLayout.setHgap(15);
										flowLayout.setVgap(10);
										GridBagConstraints gbc_panelButtons = new GridBagConstraints();
										gbc_panelButtons.anchor = GridBagConstraints.NORTH;
										gbc_panelButtons.fill = GridBagConstraints.HORIZONTAL;
										gbc_panelButtons.insets = new Insets(0, 0, 0, 5);
										gbc_panelButtons.gridwidth = 2;
										gbc_panelButtons.gridx = 3;
										gbc_panelButtons.gridy = 7;
										panel.add(panelButtons, gbc_panelButtons);
										
												btnAceptar = new JButton("Aceptar");
												btnAceptar.addKeyListener(new KeyAdapter() {
													@Override
													public void keyPressed(KeyEvent e ) {

														if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

															try {

																registraUsuario();

																//						btnAceptar.setVisible( false );
															} catch (Exception ex) {

																ex.printStackTrace();
																System.out.println("Igual se ha pulsado dos veces");
																System.out.println("Se ha metido un usuario con ID ya existente.");
																lblConfirmacion.setText("ID ya existe");
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

														} catch ( Exception ex ) {
															System.out.println("Igual se ha pulsado dos veces");
															ex.printStackTrace();
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
																System.exit(0);				
															}
														});
														btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
														panelButtons.add(btnCancelar);
						
								lblConfirmacion = new JLabel("");
								lblConfirmacion.setForeground(new Color(153, 0, 0));
								lblConfirmacion.setFont(new Font("SansSerif", Font.BOLD, 14));
								GridBagConstraints gbc_lblConfirmacion = new GridBagConstraints();
								gbc_lblConfirmacion.fill = GridBagConstraints.BOTH;
								gbc_lblConfirmacion.gridx = 5;
								gbc_lblConfirmacion.gridy = 7;
								panel.add(lblConfirmacion, gbc_lblConfirmacion);
								
								panel_1 = new JPanel();
								GridBagConstraints gbc_panel_1 = new GridBagConstraints();
								gbc_panel_1.fill = GridBagConstraints.BOTH;
								gbc_panel_1.gridx = 0;
								gbc_panel_1.gridy = 1;
								contentPane.add(panel_1, gbc_panel_1);
	}

	public void registraUsuario() {

		if ( textFieldNombre.getText().isEmpty() || textFieldApellido.getText().isEmpty()
				|| textFieldID.getText().isEmpty() || passwordField.getText().isEmpty()
				|| passwordField_1.getText().isEmpty() ) {

			System.out.println("No podemos registrar nada");
			lblConfirmacion.setText("Faltan campos");
			return;

		}

		else {

			u = new Usuario();
			dao = new UsuarioHibernate( em );
			u.setNombre( textFieldNombre.getText() );
			u.setApellido( textFieldApellido.getText() );

			if ( passwordOK() ) {

				u.setPassword( passwordField.getText() );
			}
			else if( passwordField.getText().length() < 5 || passwordField_1.getText().length() < 5 ) {
				lblConfirmacion.setText("5 caracteres minimo");

				return;
			}
			else {
				lblConfirmacion.setText("Contraseñas no coinicden");
				return;
			}

			u.setIDUser( textFieldID.getText() );

			EntityTransaction tx = this.em.getTransaction();
			if ( !tx.isActive() ) {
				tx.begin();
			}
			
			//esto no va

			if ( dao.findByPrimaryKey(u) == null ) {

				this.dao.insert( u );

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


			}
			else if ( u.getIDUser().equals( dao.findByPrimaryKey(u).getIDUser() ) ) {

				lblConfirmacion.setText("El ID ya existe");
				return;
			}

			tx.commit();

		}

	}

	private boolean passwordOK() {

		String field1 = passwordField.getText();
		String field2 = passwordField_1.getText();

		//me deja meter espacios
		if ( field1.isEmpty() || field2.isEmpty() ) {

			return false;
		}

		if ( field1.length() < 5 || field2.length() < 5 ) {

			return false;
		}
		if ( field1.equals( field2 ) ) {

			return true;
		}
		return false;
	}


}
