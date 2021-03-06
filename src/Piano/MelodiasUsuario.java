package Piano;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollBar;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import Piano.Reproductor.HeAcabadoListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MelodiasUsuario extends JFrame {

	private JPanel contentPane;
	private EntityManager em;
	private MelodiaHibernate dao;
	private PulsacionHibernate pdao;

	private UsuarioHibernate newuser;
	private Usuario userConectado;
	private JLabel lblConnected;
	private JLabel lblNombreUser;
	private JButton btnReproducir;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JList<Melodia> listPanel;
	private List<Melodia> listaRetorno;
	private JScrollBar scrollBar;
	private List<Pulsacion> pulsacionesReproducir;
	private JLabel labelWarn;
	Reproductor r = new Reproductor();

	EntityTransaction tx;
	private class ListaMelodiasRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			Component ret = super.getListCellRendererComponent(list, value,
					index, isSelected, cellHasFocus);

			return ret;

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MelodiasUsuario frame = new MelodiasUsuario(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void borraMelodia(){

		Melodia m = listPanel.getSelectedValue();

		if ( m == null ) {
			labelWarn.setText("Seleccione una melodia");
			return;
		}

		labelWarn.setText("");
		ConfirmaBorrar  conf = new ConfirmaBorrar();
		conf.setModalityType( Dialog.ModalityType.APPLICATION_MODAL );
		conf.setVisible( true );
		conf.setResizable( false );

		List<Pulsacion> pulsacionesDeMelodiaQBorro;

		pulsacionesDeMelodiaQBorro = dao.pulsacionesDeMelodia( m );

		if ( conf.getBotonPulsado() == null ) {
			System.out.println("Me salgo porque no se ha pulsado nada");
			return;
		}
		if ( conf.getBotonPulsado().equals("SI") ) {

			tx = em.getTransaction();
			tx.begin();

			for ( Pulsacion p : pulsacionesDeMelodiaQBorro ) {

				pdao.delete( p );					
			}

			dao.delete(m);
			tx.commit();
			DefaultListModel<Melodia> model = (DefaultListModel<Melodia>) listPanel.getModel();
			model.removeElement( m );
			//refresco la lista
			//kaska, no borro melodia de tabla pulsaciones

		}
		else {
			System.out.println("No se borra nada");
			return;
		}


	}



	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public MelodiasUsuario(MouseAdapter mouseAdapter) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MelodiasUsuario.class.getResource("/Piano/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(255, 255, 255));
		panelCentro.setBounds(62, 100, 470, 264);
		contentPane.add(panelCentro);
		panelCentro.setLayout(null);


		scrollBar = new JScrollBar();
		scrollBar.setEnabled(false);
		scrollBar.setBounds(453, 0, 17, 264);
		panelCentro.add(scrollBar);

		listPanel = new JList();
		listPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					System.out.println("se ha pulsado suprimir");

					borraMelodia();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					reproduce();
				}
			}
		});
		listPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		listPanel.setFont(new Font("SansSerif", Font.BOLD, 13));
		listPanel.setBounds(10, 11, 433, 242);
		panelCentro.add(listPanel);
		listPanel.setCellRenderer(new ListaMelodiasRenderer() );

		btnReproducir = new JButton("Reproducir");
		btnReproducir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

					reproduce();
				}
			}
		});
		btnReproducir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {

				reproduce();
			}

		});

		btnReproducir.setForeground(new Color(0, 0, 0));
		btnReproducir.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnReproducir.setBounds(552, 100, 134, 30);
		contentPane.add(btnReproducir);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				borraMelodia();

			}

		});
		btnBorrar.setForeground(new Color(0, 0, 0));
		btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnBorrar.setBounds(552, 178, 134, 30);
		contentPane.add(btnBorrar);

		btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Melodia m = listPanel.getSelectedValue();

				if ( m == null ) {
					labelWarn.setText("Seleccione una melodia");
					return;
				}

				labelWarn.setText("");
				GuardarMelodia gm = new GuardarMelodia(this);
				gm.setModalityType( Dialog.ModalityType.APPLICATION_MODAL );
				gm.setVisible( true );
				gm.setResizable( false );

				if ( gm.getTxtFieldContent().isEmpty() ) {
					System.out.println("No editamos nada");
					return;
				}

				if ( gm.getBotonPulsado() == null ) {
					System.out.println("Tampoco hago nada");
					return;
				}
				if ( gm.getBotonPulsado().equals("GUARDAR") ) {

					if ( m != null ) {

						m.setNombreMelodia( gm.getTxtFieldContent() );
						tx = em.getTransaction();
						tx.begin();
						dao.update(m);
						tx.commit();					
					}

				}

				if (gm.getBotonPulsado().equals("CANCELAR")) {
					return;
				}

				rellenaListaMelodias();
				//vuelvo a refrescar lista
			}
		});

		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnEditar.setBounds(552, 254, 134, 30);
		contentPane.add(btnEditar);

		lblConnected = new JLabel("");
		lblConnected.setBounds(62, 55, 24, 24);
		contentPane.add(lblConnected);
		lblConnected.setIcon(new ImageIcon(MelodiasUsuario.class.getResource("/Piano/conected.png")));

		lblNombreUser = new JLabel("");
		lblNombreUser.setBounds(96, 55, 281, 24);
		contentPane.add(lblNombreUser);
		lblNombreUser.setForeground(new Color(0, 0, 0));
		lblNombreUser.setFont(new Font("SansSerif", Font.BOLD, 12));

		labelWarn = new JLabel("");
		labelWarn.setForeground(new Color(204, 0, 0));
		labelWarn.setFont(new Font("SansSerif", Font.BOLD, 12));
		labelWarn.setHorizontalAlignment(SwingConstants.CENTER);
		labelWarn.setBounds(431, 55, 221, 24);
		contentPane.add(labelWarn);

		JButton btnEdit = new JButton("Editar perfil");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tx = em.getTransaction();
				EditarPerfil edit = new EditarPerfil();
				edit.setModalityType( Dialog.ModalityType.APPLICATION_MODAL );
				edit.setVisible( true );
				edit.setResizable( false );

				if ( edit.getBotonPulsado() == null ) {
					return;
				}
				if (  edit.getBotonPulsado().equals("CANCELAR") ) {
					return;
				}

				if (  edit.getBotonPulsado().equals("ACEPTAR") ) {

					labelWarn.setText("Usuario modificado con exito");
					newuser = new UsuarioHibernate(em);

					userConectado = newuser.findByPrimaryKey(userConectado);

					System.out.println("Nombre: " + userConectado.getNombre() + " " + "ID: " + userConectado.getIDUser());
					userConectado.setNombre( edit.getNewName() );
					userConectado.setApellido( edit.getNewApe() );
					userConectado.setPassword( edit.getNewPass() );

					System.out.println("Nombre: " + userConectado.getNombre() + " " + "ID: " + userConectado.getIDUser());


					if ( !tx.isActive() ) {
						tx.begin();
					}

					em.persist(userConectado);
					tx.commit();				


				}
			}
		});
		btnEdit.setForeground( new Color( 0, 0, 0 ));
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEdit.setHorizontalAlignment(SwingConstants.CENTER);

		btnEdit.setBounds(552, 358, 134, 30);
		contentPane.add(btnEdit);


	}

	public void setEntityManager( EntityManager em ) throws Exception {
		this.em = em;
		dao = new MelodiaHibernate ( em );
		pdao = new PulsacionHibernate( em );
		rellenaListaMelodias();
	}

	public void rellenaListaMelodias(){

		listaRetorno = dao.buscaMelodiasPorUsuario( userConectado );

		DefaultListModel<Melodia> model = new DefaultListModel<Melodia>();
		listPanel.setModel(model);

		if ( listaRetorno.isEmpty()) {

			return;

		}
		for ( Melodia m : listaRetorno ) {

			model.addElement(m);		
		}
		listPanel.setModel( model );

		if ( listaRetorno.size() > 10 ) {
			scrollBar.setEnabled( true );
		}
	}

	public void setUsuario( Usuario u ) {

		this.userConectado = u;
		lblNombreUser.setText(userConectado.getIDUser() );

	}

	public void reproduce() {

		System.out.println( btnReproducir.getText() );

		if ( btnReproducir.getText().equals("Parar")) {

			r.setPararMelodia( true );
		}
		else {
			r.setPararMelodia( false );
		}

		Melodia m = listPanel.getSelectedValue();

		if ( m == null ) {
			labelWarn.setText("Debes seleccionar una melodia");
		}

		else{

			labelWarn.setText("");
			btnReproducir.setText("Parar");

			
			r.setListaPulsaciones( pdao.getPulsacionesDeMelodia( m ) );

			r.tocaMelodia( new HeAcabadoListener() {

				@Override
				public void heAcabado() {

					btnReproducir.setText( "Reproducir" ); 
					return;
				}
			});
		}


	}

}
