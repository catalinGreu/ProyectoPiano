package Piano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfirmaBorrar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String contenidoBoton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmaBorrar dialog = new ConfirmaBorrar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmaBorrar() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 337, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblestaSeguroDe = new JLabel("\u00BFSeguro que desea eliminar la melodia?");
		lblestaSeguroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblestaSeguroDe.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblestaSeguroDe.setBounds(30, 47, 288, 45);
		contentPanel.add(lblestaSeguroDe);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnOk = new JButton("Si");
			btnOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					contenidoBoton = "SI";
					setVisible( false );
				}
			});
			btnOk.setFont(new Font("SansSerif", Font.BOLD, 11));
			buttonPane.add(btnOk);
			
			JButton btnNewButton = new JButton("No");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					contenidoBoton = "NO";
					setVisible( false );
				}
			});
			btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 11));
			buttonPane.add(btnNewButton);
		}
	}
	public String getBotonPulsado() {
		
		return this.contenidoBoton;
		
	}
}
