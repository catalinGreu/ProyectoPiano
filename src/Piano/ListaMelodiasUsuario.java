package Piano;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class ListaMelodiasUsuario {

	private JFrame frame;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnReproducir;
	private JList listaMelodias = new JList<Melodia>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaMelodiasUsuario window = new ListaMelodiasUsuario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaMelodiasUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 153, 102));
		frame.setBounds(100, 100, 653, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panelPpal = new JPanel();
		panelPpal.setBounds(73, 77, 406, 278);
		frame.getContentPane().add(panelPpal);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnBorrar.setBounds(515, 77, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnEditar.setBounds(515, 135, 89, 23);
		frame.getContentPane().add(btnEditar);
		
		btnReproducir = new JButton("Reproducir");
		btnReproducir.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnReproducir.setBounds(515, 199, 89, 23);
		frame.getContentPane().add(btnReproducir);
		
		 DefaultListModel model = new DefaultListModel();
		 panelPpal.add(listaMelodias);
		 listaMelodias.setModel(model);
//		 ArrayList<Melodia> melodias = new ArrayList<Melodia>();
		 
		 for ( int i = 0; i < 20; i+=1 ) {
			 model.addElement("hola");
			 
//			 Melodia m = (Melodia) model.getElementAt( i );
			
		}
		 
		 JScrollPane scrollPane = new JScrollPane(listaMelodias);
			scrollPane.setBounds(73, 353, 403, -274);
			frame.getContentPane().add(scrollPane);
		
	}
}
