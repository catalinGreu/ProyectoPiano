package Piano;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.MouseAdapter;

import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

public class MelodiasUsuario extends JFrame {

	private JPanel contentPane;

	private Usuario userConectado;
	private UsuarioHibernate dao;
	private EntityManager em;
	private List<Melodia> listaMelodias = new ArrayList<Melodia>();
	private JLabel lblNombreuser;
	private JList list;

//	private class MelodiaRenderer extends DefaultListCellRenderer {
//
//		@Override
//		public Component getListCellRendererComponent( JList<?> list, Object value, int index, 
//				boolean isSelected, boolean cellHasFocus){
//			Component ret = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//			
//			JLabel label = (JLabel) ret;
//			Melodia m = (Melodia) value;
//			label.setText("ID: " + m.getId_melodia() + " " + m.getNombreMelodia() );
//			
//			return ret;
//			
//		}
//
//	}	


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

	public void setEntityManager( EntityManager em ) throws Exception {
		this.em = em;
		dao = new UsuarioHibernate( em );
	}

	/**
	 * Create the frame.
	 */
	public MelodiasUsuario() {
		setBackground(new Color(51, 153, 102));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 460);

		JPanel panel = new JPanel();
		panel.setBounds(62, 58, 401, 289);
		contentPane.add(panel);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<Melodia>();
		panel.add( new ScrollPane());
		
		

		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(503, 58, 89, 23);
		contentPane.add(btnPlay);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(503, 141, 89, 23);
		contentPane.add(btnBorrar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(503, 226, 89, 23);
		contentPane.add(btnEditar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MelodiasUsuario.class.getResource("/Piano/conected.png")));
		lblNewLabel.setBounds(38, 11, 32, 23);
		contentPane.add(lblNewLabel);

		lblNombreuser = new JLabel("");
		lblNombreuser.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNombreuser.setBounds(71, 11, 157, 23);
		contentPane.add(lblNombreuser);
	}

	public void llenaListaMelodias(){

		this.listaMelodias = userConectado.getMelodias();

		DefaultListModel<Melodia> model = new DefaultListModel<Melodia>();

		for (Melodia m : listaMelodias) {

			model.addElement(m);		
		}
	}



	public void setUsuario( Usuario u) {

		this.userConectado = u;
		lblNombreuser.setText(userConectado.getIDUser() );

	}




}
