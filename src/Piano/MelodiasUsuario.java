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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MelodiasUsuario extends JFrame {

	private JPanel contentPane;
	private EntityManager em;
	private MelodiaHibernate dao;
	private PulsacionHibernate pdao;
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
					MelodiasUsuario frame = new MelodiasUsuario();
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
	@SuppressWarnings("unchecked")
	public MelodiasUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MelodiasUsuario.class.getResource("/Piano/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 452);
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
		listPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		listPanel.setFont(new Font("SansSerif", Font.BOLD, 13));
		listPanel.setBounds(10, 11, 433, 242);
		panelCentro.add(listPanel);
		listPanel.setCellRenderer(new ListaMelodiasRenderer() );

		btnReproducir = new JButton("Reproducir");
		btnReproducir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {

				//
				//if ( btnReproducir.getModel().isPressed() ) {
				//		System.out.println("no reproduzco nada y me espero");
				//}

				//si pulso 1000 veces reproduce 1000 veces seguidas.
				Melodia m = listPanel.getSelectedValue();

				if ( m == null ) {
					labelWarn.setText("Debes seleccionar una melodia");
				}

				else{
					System.out.println(m.getId_melodia());
					System.out.println(m.getNombreMelodia());

					Reproductor r = new Reproductor();
					r.setListaPulsaciones(pdao.getPulsacionesDeMelodia( m ));
					r.tocaMelodia();

				}
			}
		});
		btnReproducir.setForeground(new Color(0, 0, 0));
		btnReproducir.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnReproducir.setBounds(552, 100, 100, 30);
		contentPane.add(btnReproducir);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Melodia m = listPanel.getSelectedValue();
				List<Pulsacion> pulsacionesDeMelodiaQBorro;

				pulsacionesDeMelodiaQBorro = dao.pulsacionesDeMelodia( m );
				
				if ( m == null ) {
					labelWarn.setText("No hay melodías para borrar");

				}				

				else{
					EntityTransaction tx = em.getTransaction();
					tx.begin();

					for (Pulsacion p : pulsacionesDeMelodiaQBorro) {

						pdao.delete(p);					
					}

					dao.delete(m);
					tx.commit();
					DefaultListModel<Melodia> model = (DefaultListModel<Melodia>) listPanel.getModel();
					model.removeElement( m );
					//refresco la lista
					//kaska, no borro melodia de tabla pulsaciones
				}
			}
		});
		btnBorrar.setForeground(new Color(0, 0, 0));
		btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnBorrar.setBounds(552, 178, 100, 30);
		contentPane.add(btnBorrar);

		btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Melodia m = listPanel.getSelectedValue();
				GuardarMelodia gm = new GuardarMelodia(this);
				gm.setModalityType( Dialog.ModalityType.APPLICATION_MODAL );
				gm.setVisible( true );
				gm.setResizable( false );				
				
				m.setNombreMelodia( gm.getTxtFieldContent() );
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				dao.update(m);
				tx.commit();
				
				rellenaListaMelodias();
				//vuelvo a refrescar lista
			}
		});
		
		btnEditar.setForeground(new Color(0, 0, 0));
		btnEditar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnEditar.setBounds(552, 254, 100, 30);
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
		labelWarn.setBounds(466, 55, 186, 24);
		contentPane.add(labelWarn);


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

		if ( listaRetorno.size() > 3 ) {
			scrollBar.setEnabled( true );
		}
	}

	public void setUsuario( Usuario u ) {

		this.userConectado = u;
		lblNombreUser.setText(userConectado.getIDUser() );

	}


}
