import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Ventana2 {

	private JFrame frame;
	private JTextField textFieldAncho;
	private JTextField textFieldAlto;
	private JTextField textFieldArea;
	private JLabel lblAncho;
	private JLabel lblAlto;
	private JSlider sliderAncho;
	private JSlider sliderAlto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana2 window = new Ventana2();
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
	public Ventana2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 554, 329);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textFieldAncho = new JTextField();
		textFieldAncho.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String s = textFieldAncho.getText();

				if( !esEntero(s) ){
					
					int oldValue = sliderAncho.getValue();
					sliderAncho.setValue(0);
					sliderAncho.setValue(oldValue);

				}
				else{
					sliderAncho.setValue(Integer.valueOf(s));
				}
			}
		});
		textFieldAncho.setBounds(287, 41, 101, 26);
		frame.getContentPane().add(textFieldAncho);
		textFieldAncho.setColumns(10);

		textFieldAlto = new JTextField();
		textFieldAlto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String s = textFieldAlto.getText();

				if( !esEntero(s) ){
					
					int oldValue = sliderAlto.getValue();
					sliderAlto.setValue(0);
					sliderAlto.setValue(oldValue);

				}
				else{
					sliderAlto.setValue(Integer.valueOf(s));
				}
			}
		});
		textFieldAlto.setBounds(287, 107, 101, 26);
		frame.getContentPane().add(textFieldAlto);
		textFieldAlto.setColumns(10);

		textFieldArea = new JTextField();


		textFieldArea.setBounds(74, 198, 101, 26);
		frame.getContentPane().add(textFieldArea);
		textFieldArea.setColumns(10);

		JLabel lblrea = new JLabel("\u00C1rea");
		lblrea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblrea.setBounds(32, 202, 46, 14);
		frame.getContentPane().add(lblrea);


		sliderAlto = new JSlider();
		sliderAncho = new JSlider();
		sliderAncho.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int alto = sliderAlto.getValue();
				int ancho = sliderAncho.getValue();
				textFieldAncho.setText(String.valueOf( ancho ));
				textFieldArea.setText(String.valueOf(ancho*alto));
			}
		});

		sliderAncho.setBounds(32, 41, 213, 26);
		frame.getContentPane().add(sliderAncho);


		sliderAlto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int alto = sliderAlto.getValue();
				int ancho = sliderAncho.getValue();
				textFieldAlto.setText(String.valueOf(alto));
				textFieldArea.setText(String.valueOf(ancho*alto));
			}
		});

		sliderAlto.setBounds(32, 107, 213, 26);
		frame.getContentPane().add(sliderAlto);

		lblAncho = new JLabel("Ancho");
		lblAncho.setBounds(398, 47, 46, 14);
		frame.getContentPane().add(lblAncho);

		lblAlto = new JLabel("Alto");
		lblAlto.setBounds(398, 113, 46, 14);
		frame.getContentPane().add(lblAlto);

		sliderAlto.setValue(25);
		sliderAncho.setValue(25);

		JList list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, null, Color.PINK, null));
		list.setBackground(Color.WHITE);
		list.setBounds(479, 258, -184, -104);
		frame.getContentPane().add(list);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(287, 198, 101, 26);
		frame.getContentPane().add(btnCancelar);
	}

	private boolean esEntero(String s) {
		try{
			Integer.parseInt(s);
			return true;
		}
		catch( NumberFormatException e ){
			return false;
		}
	}
}
