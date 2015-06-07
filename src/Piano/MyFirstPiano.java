package Piano;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import java.awt.Toolkit;

import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MyFirstPiano extends JFrame {

	protected static final int MILISEGUNDOSDEMUESTREO = 10;
	private ArrayList<Pulsacion> listaTeclas = new ArrayList<Pulsacion>();
	private ArrayList<Pulsacion> listaParaGuardar = new ArrayList<Pulsacion>();

	private Timer timer;
	private static double contador = 0;
	private JLayeredPane contentPane;
	private JButton btnDo;
	private JButton btnRe;
	private JButton btnMi;
	Clip clip;
	private JButton btnNewButton_1;
	private JButton btnSolb;
	private JButton btnSib;
	private JButton btnSi;
	private JButton btnDo4;
	private JButton Re4;
	private JButton btnMi4;
	private JButton btnReb4;
	private JButton btnFa4;
	private JButton btnSol4;
	private JButton btnLa4;
	private JButton btnSi4;
	private JButton btnDo5;
	private JButton btnSolb4;
	private JButton btnLab4;
	private JButton btnSib4;
	private JButton rec_btn;
	private JLabel lblParar;
	private JLabel lblNewLabel;
	private JPanel panelReproductor;
	private JLabel lblGrabando;

	private EntityManager em;
	private UsuarioHibernate dao;
	private MelodiaHibernate melodiaDAO;
	private PulsacionHibernate pulsDAO;
	private String idUsuarioConectado;

	private Usuario userConnected;

	private JLabel lblIduser;
	private JLabel lblUser;
	private JLabel lblImgConect;
	private JButton btnGuardar;
	private JButton btnMisMelodias;
	private JButton play_btn;
	private JButton stop_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFirstPiano frame = new MyFirstPiano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

	public void setUserConected( Usuario u, boolean connected ){

		this.userConnected = u;
		this.idUsuarioConectado = u.getIDUser();
		lblIduser.setText( u.getIDUser() );
		if ( connected ) {
			lblImgConect.setEnabled( true );
			rec_btn.setEnabled( true );
			rec_btn.setToolTipText("Pulsa para grabar");
		}
		else {
			this.userConnected = null;
			lblImgConect.setEnabled( false );
			rec_btn.setEnabled( false );
			btnGuardar.setEnabled( false );
			play_btn.setEnabled( false );
			stop_btn.setEnabled( false );


			rec_btn.setToolTipText("Registrate para poder grabar melodias");
			btnMisMelodias.setEnabled( false );
			btnMisMelodias.setToolTipText("No tiene melodias");
		}

	}
	/**
	 * Create the frame.
	 */
	public MyFirstPiano() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {

				if ( e.getKeyCode() == 87 ) {
					playSound("3.RE3.wav");
				}
			}
		});
		setImageIcon();
		setResizable(false);
		setBackground(new Color(51, 153, 102));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1197, 458);
		contentPane = new JLayeredPane();
		contentPane.setBackground(new Color(0, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRe = new JButton("");
		btnRe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == 87 ) {
					playSound("3.RE3.wav");
				}
			}
		});
		btnRe.setBackground(Color.WHITE);

		btnRe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("3.RE3.wav");

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//	clip.close();
			}
		});

		btnDo = new JButton("");
		btnDo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {
				if ( e.getKeyCode() == 81  ) {

					playSound("1.DO3.wav");

				}
			}
		});
		btnDo.setBackground(Color.WHITE);
		btnDo.setBounds(74, 33, 51, 284);
		contentPane.add(btnDo);
		btnDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				playSound( "1.DO3.wav" );

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//clip.close();

			}
		});
		btnRe.setBounds(120, 33, 51, 284);
		contentPane.add(btnRe);

		btnMi = new JButton("");
		btnMi.setBackground(Color.WHITE);
		btnMi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "5.MI3.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnMi.setBounds(167, 33, 53, 284);
		contentPane.add(btnMi);

		JButton btnReb3 = new JButton("");
		btnReb3.addKeyListener(new KeyAdapter() {
			//			@Override
			//			public void keyPressed(KeyEvent e) {
			//				if ( e.getKeyCode() == 87 ) {
			//					playSound("2.Reb3.wav");
			//				}
			//			}
		});
		contentPane.setLayer(btnReb3, 2);
		btnReb3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "2.REb3.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnReb3.setBackground(Color.BLACK);
		btnReb3.setBounds(102, 33, 37, 189);
		contentPane.add(btnReb3);

		JButton btnMib = new JButton("");
		contentPane.setLayer(btnMib, 2);
		btnMib.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {

				playSound(  "4.MIb3.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnMib.setBackground(Color.BLACK);
		btnMib.setBounds(157, 33, 37, 189);
		contentPane.add(btnMib);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "6.FA3.wav"  );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnNewButton_1.setBounds(216, 33, 51, 284);
		contentPane.add(btnNewButton_1);

		btnSolb = new JButton("");
		contentPane.setLayer(btnSolb, 2);
		btnSolb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				playSound( "7.SOLb3.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnSolb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSolb.setBackground(Color.BLACK);
		btnSolb.setBounds(249, 33, 37, 189);
		contentPane.add(btnSolb);

		JButton btnSol = new JButton("");
		btnSol.setBackground(Color.WHITE);
		btnSol.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "8.SOL3.wav" );

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();

			}
		});
		btnSol.setBounds(265, 33, 53, 284);
		contentPane.add(btnSol);

		JButton btnLab = new JButton("");
		contentPane.setLayer(btnLab, 2);
		btnLab.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "9.LAb3.wav"  );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLab.setBackground(Color.BLACK);
		btnLab.setBounds(296, 33, 37, 189);
		contentPane.add(btnLab);

		JButton btnLa = new JButton("");
		btnLa.setBackground(Color.WHITE);
		btnLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound(  "10.LA3.wav"  );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnLa.setBounds(315, 33, 53, 284);
		contentPane.add(btnLa);

		btnSib = new JButton("");
		contentPane.setLayer(btnSib, 2);
		btnSib.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "11.SIb3.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnSib.setBackground(Color.BLACK);
		btnSib.setBounds(343, 33, 37, 189);
		contentPane.add(btnSib);

		btnSi = new JButton("");
		btnSi.setBackground(Color.WHITE);
		btnSi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound(  "12.SI3.wav"  );

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnSi.setBounds(362, 33, 53, 284);
		contentPane.add(btnSi);

		btnDo4 = new JButton("");
		btnDo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDo4.setBackground(Color.WHITE);
		btnDo4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound( "13.DO4.wav" );
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		btnDo4.setBounds(412, 33, 51, 284);
		contentPane.add(btnDo4);

		Re4 = new JButton("");
		Re4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("15.RE4.wav");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});
		Re4.setBackground(Color.WHITE);
		Re4.setBounds(462, 33, 53, 284);
		contentPane.add(Re4);

		btnMi4 = new JButton("");
		btnMi4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("17.Mi4.wav");
			}
		});
		btnMi4.setBackground(Color.WHITE);
		btnMi4.setBounds(514, 33, 51, 284);
		contentPane.add(btnMi4);

		btnReb4 = new JButton("");
		btnReb4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("14.REb4.wav");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//				clip.close();
			}
		});

		btnReb4.setBackground(Color.BLACK);
		contentPane.setLayer(btnReb4, 2);
		btnReb4.setBounds(443, 33, 37, 189);
		contentPane.add(btnReb4);

		JButton btnMib4 = new JButton("");
		btnMib4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				playSound("16.Mib4.wav");

			}
		});
		btnMib4.setBackground(Color.BLACK);
		contentPane.setLayer(btnMib4, 2);
		btnMib4.setBounds(500, 33, 37, 189);
		contentPane.add(btnMib4);

		btnFa4 = new JButton("");
		btnFa4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("18.Fa4.wav");
			}
		});
		btnFa4.setBackground(Color.WHITE);
		btnFa4.setBounds(564, 33, 51, 284);
		contentPane.add(btnFa4);

		btnSol4 = new JButton("");
		btnSol4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("20.Sol4.wav");
			}
		});
		btnSol4.setBackground(Color.WHITE);
		btnSol4.setBounds(614, 33, 51, 284);
		contentPane.add(btnSol4);

		btnLa4 = new JButton("");
		btnLa4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("22.La4.wav");
			}
		});
		btnLa4.setBackground(Color.WHITE);
		btnLa4.setBounds(664, 33, 51, 284);
		contentPane.add(btnLa4);

		btnSi4 = new JButton("");
		btnSi4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("24.Si4.wav");
			}
		});
		btnSi4.setBackground(Color.WHITE);
		btnSi4.setBounds(714, 33, 51, 284);
		contentPane.add(btnSi4);

		btnDo5 = new JButton("");
		btnDo5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("25.Do5.wav");
			}
		});
		btnDo5.setBackground(Color.WHITE);
		btnDo5.setBounds(763, 33, 51, 284);
		contentPane.add(btnDo5);

		btnSolb4 = new JButton("");
		btnSolb4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("19.Solb4.wav");
			}
		});
		btnSolb4.setBackground(Color.BLACK);
		contentPane.setLayer(btnSolb4, 2);
		btnSolb4.setBounds(591, 33, 37, 189);
		contentPane.add(btnSolb4);

		btnLab4 = new JButton("");
		btnLab4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("21.Lab4.wav");
			}
		});
		btnLab4.setBackground(Color.BLACK);
		contentPane.setLayer(btnLab4, 2);
		btnLab4.setBounds(645, 33, 37, 189);
		contentPane.add(btnLab4);

		btnSib4 = new JButton("");
		btnSib4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("23.Sib4.wav");
			}
		});
		btnSib4.setBackground(Color.BLACK);
		contentPane.setLayer(btnSib4, 2);
		btnSib4.setBounds(695, 33, 37, 189);
		contentPane.add(btnSib4);

		ActionListener pulsarGrabar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ( !( timer.isRunning() ) ) {

					timer.start();
				}				

			}
		};

		ActionListener tickea = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tick();				
			}
		};

		timer = new Timer( MILISEGUNDOSDEMUESTREO, tickea );

		JButton btnRe5 = new JButton("");
		btnRe5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//				teclaPulsada("27.Re5.wav");

				playSound("27.Re5.wav");
			}
		});
		btnRe5.setBackground(Color.WHITE);
		btnRe5.setBounds(813, 33, 51, 284);
		contentPane.add(btnRe5);

		JButton btnMi5 = new JButton("");
		btnMi5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("29.Mi5.wav");
			}
		});
		btnMi5.setBackground(Color.WHITE);
		btnMi5.setBounds(863, 33, 51, 284);
		contentPane.add(btnMi5);

		JButton btnReb5 = new JButton("");
		btnReb5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("26.Reb5.wav");
			}
		});
		contentPane.setLayer(btnReb5, 2);
		btnReb5.setBackground(Color.BLACK);
		btnReb5.setBounds(796, 33, 37, 189);
		contentPane.add(btnReb5);

		JButton btnMib5 = new JButton("");
		btnMib5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				playSound("28.Mib5.wav");
			}
		});
		btnMib5.setBackground(Color.BLACK);
		contentPane.setLayer(btnMib5, 2);
		btnMib5.setBounds(851, 33, 37, 189);
		contentPane.add(btnMib5);

		JLabel lblGrabar = new JLabel("Rec");
		lblGrabar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblGrabar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrabar.setBounds(976, 277, 46, 14);
		contentPane.add(lblGrabar);

		lblIduser = new JLabel("");
		lblIduser.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblIduser.setBounds(95, 342, 304, 25);
		contentPane.add(lblIduser);

		lblParar = new JLabel("Stop");
		lblParar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblParar.setHorizontalAlignment(SwingConstants.CENTER);
		lblParar.setBounds(1040, 277, 51, 14);
		contentPane.add(lblParar);

		lblNewLabel = new JLabel("Play");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(1101, 277, 37, 14);
		contentPane.add(lblNewLabel);

		panelReproductor = new JPanel();
		panelReproductor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(102, 153, 204)));
		panelReproductor.setBounds(958, 204, 197, 46);
		contentPane.add(panelReproductor);
		panelReproductor.setLayout(null);

		rec_btn = new JButton("");
		rec_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if ( !rec_btn.isEnabled() ) {

					lblGrabando.setText("");

				}			
				else{
					lblGrabando.setText("Grabando...");
					btnGuardar.setEnabled( false );
					listaTeclas.removeAll(listaTeclas);
				}
			}
		});
		rec_btn.setBounds(26, 11, 25, 23);
		panelReproductor.add(rec_btn);
		rec_btn.setActionCommand("start");

		rec_btn.addActionListener( pulsarGrabar );


		rec_btn.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/rec.png")));

		stop_btn = new JButton("");
		stop_btn.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/stop2.png")));
		stop_btn.setBounds(93, 11, 25, 23);
		stop_btn.setToolTipText("Parar de grabar");
		panelReproductor.add(stop_btn);
		stop_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if ( !stop_btn.isEnabled() ) {

					lblGrabando.setText("");
				}
				else {			
					lblGrabando.setText("Stop.");
					stop_btn.setToolTipText("Pulsa para parar");
					timer.stop();
					contador = 0;					
				}

			}
		});
		stop_btn.setActionCommand("stop");

		play_btn = new JButton("");
		play_btn.setToolTipText("Pulsa para reproducir");
		play_btn.setBounds(148, 11, 25, 23);
		panelReproductor.add(play_btn);
		play_btn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if  ( !play_btn.isEnabled() ) {					

					lblGrabando.setText("");

				}
				else{
					play_btn.setToolTipText("Pulsa para reproducir");
					lblGrabando.setText("Reproduciendo....");

					listaParaGuardar = (ArrayList)listaTeclas.clone();

					if ( listaParaGuardar.isEmpty() ) {
						lblGrabando.setText("Antes debes grabar");
						return;
					}

					Reproductor r = new Reproductor();
					r.setListaPulsaciones(listaParaGuardar);
					r.tocaMelodia( null );

					if ( !btnGuardar.isEnabled() ) {

						if ( !( userConnected == null ) ) {

							btnGuardar.setEnabled( true );
						}
					}	
				}
			}

		});

		play_btn.setForeground(Color.BLACK);
		play_btn.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/play.png")));

		lblGrabando = new JLabel("");
		lblGrabando.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblGrabando.setBounds(958, 168, 197, 25);
		contentPane.add(lblGrabando);

		lblUser = new JLabel("User:");
		lblUser.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblUser.setBounds(39, 342, 46, 25);
		contentPane.add(lblUser);

		lblImgConect = new JLabel("");
		lblImgConect.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgConect.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/conected.png")));
		lblImgConect.setBounds(10, 342, 31, 25);
		contentPane.add(lblImgConect);

		btnGuardar = new JButton("");
		btnGuardar.setBackground(new Color(51, 153, 102));
		btnGuardar.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/save_small.png")));
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {

				if ( btnGuardar.isEnabled() ) {

					btnGuardar.setToolTipText("Guardar melodia");
					GuardarMelodia g = new GuardarMelodia(this);
					g.setModalityType( Dialog.ModalityType.APPLICATION_MODAL );
					g.setAlwaysOnTop( true );
					g.setVisible( true );
					g.setResizable( false );

					if ( g.getTxtFieldContent().isEmpty() ) {
						System.out.println("no guardo nada porque esta vacío");
						return;
					}

					if ( g.getBotonPulsado() == null) {
						return;
					}
					if (g.getBotonPulsado().equals("GUARDAR")) {

						guardaMelodia( g.getTxtFieldContent() );
					}
					else {
						return;
					}

				}


			}
		});
		btnGuardar.setEnabled(false);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnGuardar.setBounds(1109, 133, 29, 32);
		contentPane.add(btnGuardar);

		JButton btnExit = new JButton("");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(MyFirstPiano.class.getResource("/Piano/exit.png")));
		btnExit.setForeground(new Color(0, 0, 0));
		btnExit.setBackground(new Color(51, 153, 102));
		btnExit.setBounds(1101, 346, 37, 32);
		contentPane.add(btnExit);
		btnExit.setToolTipText("Salir");

		btnMisMelodias = new JButton("Mis melodias");
		btnMisMelodias.setToolTipText("Mi lista de melodias");
		btnMisMelodias.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {

				if ( btnMisMelodias.isEnabled() ) {

					MelodiasUsuario lista = new MelodiasUsuario(this);	
					lista.setVisible( true );
					lista.setResizable( false );
					lista.setUsuario( userConnected );	
					try {
						lista.setEntityManager(em);
					} catch (Exception e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		btnMisMelodias.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnMisMelodias.setBounds(1031, 33, 124, 25);
		contentPane.add(btnMisMelodias);
	}

	public void guardaMelodia( String nombre ){

		Usuario u = userConnected;
		Melodia m = new Melodia( nombre, u );
		u.addMelodia( m );

		System.out.println("Size de la listaParaGuardar cuando llega:" + listaParaGuardar.size());
		System.out.println("Size de la listaTeclas cuando llega:" + listaTeclas.size());
		for (Pulsacion p : listaTeclas) {

			m.addPulsacion( p );
		}

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist( m );
		for (Pulsacion p : listaTeclas) {

			em.persist( p );					
		}

		tx.commit();	

		listaTeclas.removeAll(listaTeclas);

		lblGrabando.setText("Melodia guardada");
		btnGuardar.setEnabled( false );
		//guardaMelodia donde haya que guardar	

	}

	public static void tick(){		
		contador += 1;	
		System.out.println( contador );

	}


	private void playSound( String nombreNota ) {

		if ( timer.isRunning() ) {

			Pulsacion p = new Pulsacion( nombreNota, contador );
			listaTeclas.add( p );

		}

		URL url = getClass().getResource(nombreNota);

		try{

			clip = AudioSystem.getClip();				 

			clip.open( AudioSystem.getAudioInputStream( url ));

			clip.start();

			//Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
