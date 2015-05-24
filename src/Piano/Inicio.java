package Piano;
//holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.Font;

import javax.swing.JPasswordField;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Inicio extends JFrame {

	Image image;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	protected static final Dimension PREFERRED = new Dimension( 647, 464 );
	
	private UsuarioHibernate dao;
	private EntityManager em;
	private Label labelWarning;

	private static Inicio frameInicio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle");
		EntityManager em = emf.createEntityManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameInicio = new Inicio();
					frameInicio.setVisible(true);
					frameInicio.setEntityManager(em);
					//					frame.pack();
					frameInicio.setResizable(true);

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

	public Inicio() {
		setBackground();
		setForeground(Color.WHITE);
		setResizable(false);
		setImageIcon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 512);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(0, 153, 102));
		contentPane.add(panelCentro);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{268, 31, 34, 171, 0};
		gbl_panelCentro.rowHeights = new int[]{41, 28, 49, 28, 0, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panelCentro.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

		textFieldUser = new JTextField();
		textFieldUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldUser.selectAll();
			}
		});
		textFieldUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

					compruebaUserYPasswd();

				}
			}
		});
		textFieldUser.setFont(new Font("SansSerif", Font.PLAIN, 13));
		GridBagConstraints gbc_textFieldUser = new GridBagConstraints();
		gbc_textFieldUser.fill = GridBagConstraints.VERTICAL;
		gbc_textFieldUser.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUser.gridx = 3;
		gbc_textFieldUser.gridy = 1;
		panelCentro.add(textFieldUser, gbc_textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		panelCentro.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				passwordField.selectAll();
			}
		});
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

					compruebaUserYPasswd();

				}
			}
		});
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 13));
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.VERTICAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 3;
		panelCentro.add(passwordField, gbc_passwordField);

		Panel panelBotones = new Panel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setHgap(20);
		panelBotones.setBackground(new Color(0, 153, 102));
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		JButton btnAceptar = new JButton("Aceptar");

		btnAceptar.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				compruebaUserYPasswd();

			}


		});
		btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnAceptar.setForeground(new Color(0, 0, 0));
		panelBotones.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnCancelar.setForeground(new Color(0, 0, 0));
		panelBotones.add(btnCancelar);

		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(new Color(0, 153, 102));
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setIcon(new ImageIcon(Inicio.class.getResource("/Piano/clavesol.png")));
		panelNorth.add(lblNewLabel_2);

		JLabel lblbImg = new JLabel("");
		lblbImg.setIcon(new ImageIcon(Inicio.class.getResource("/Piano/mp_logo.png")));
		panelNorth.add(lblbImg);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Inicio.class.getResource("/Piano/notas.png")));
		panelNorth.add(lblNewLabel_3);

		JPanel panelWest = new JPanel();
		panelWest.setBackground(new Color(0, 153, 102));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 46, 14);
		panelWest.add(lblNewLabel_1);

		JPanel panelEste = new JPanel();
		panelEste.setBackground(new Color(0, 153, 102));
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setHgap(10);
		panel.setBackground(new Color(51, 153, 153));
		panelEste.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Registro");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed (MouseEvent arg0) {

				Registration r = new Registration();
				r.setVisible(true);
				//				r.setPreferredSize( PREFERRED );
				//				r.pack();

				try {
					r.setEntityManager( em );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Juego Rapido");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				MyFirstPiano p = new MyFirstPiano();
				//				p.pack();
				p.setVisible(true);
				Usuario nuevo = new Usuario();
				nuevo.setIDUser("");
				p.setUserConected( nuevo, false );

			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnNewButton_1);

		labelWarning = new Label("");
		labelWarning.setForeground(new Color(204, 0, 51));
		panelEste.add(labelWarning, BorderLayout.CENTER);
		labelWarning.setFont(new Font("SansSerif", Font.BOLD, 15));
	}

	public void compruebaUserYPasswd(){
		
		if (textFieldUser.getText().equals("") || (passwordField.getText().equals(""))) {
			labelWarning.setText("Introduzca usuario y contraseña" );
		}

		else{
			Usuario u = new Usuario();
			u.setIDUser( textFieldUser.getText() );
			u.setPassword( passwordField.getText() );

			dao = new UsuarioHibernate( em );
			Usuario userFound = null;
			try {
				userFound = dao.findByPrimaryKey( u );
			} catch (Exception e1) {

				labelWarning.setText("Usuario no existe");

				//				e1.printStackTrace();
			}

			if ( userFound.equals( u ) ) {

				MyFirstPiano p = new MyFirstPiano();
				p.setVisible(true);
				p.setResizable(false);
				p.setUserConected( u , true );
				try {
					p.setEntityManager(em);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				frameInicio.setVisible( false );
			}
			else {
				labelWarning.setText("No existe el usuario");
				
			}
		}
	}

	public void setBackground(){
		URL bgImage=this.getClass().getResource("fondoinicio.jpg");
		BufferedImage bg=null;
		try {
			bg = ImageIO.read(bgImage);
		} catch (IOException e) {
			System.out.println("Background image does not exist!");
		}
		//		setContentPane(	new ImagePanel(bg));
	}

	//	private boolean userExists( Usuario u ) {
	//		
	//		Query q = getSession.cre
	//		return false;
	//	}

}
//
//class ImagePanel extends JComponent {
//	private Image image;
//	public ImagePanel(Image image) {
//		this.image = image;
//	}
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(image, 0, 0, this);
//	}
//}


