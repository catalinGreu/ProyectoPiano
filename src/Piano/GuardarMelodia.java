package Piano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.transaction.Transactional.TxType;

public class GuardarMelodia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private MelodiaHibernate mh;
	private EntityManager em;
	private Usuario u;
	private String nombreCancion;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblWarning;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuardarMelodia dialog = new GuardarMelodia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuardarMelodia(MouseAdapter mouseAdapter) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuardarMelodia.class.getResource("/Piano/save-icon.png")));
		setBounds(100, 100, 346, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre Cancion");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setBounds(43, 44, 123, 27);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if ( textField.getText().length() == 0 ) {
						
						lblWarning.setText("Debes introducir un nombre");						
					}
					else{	
						
						setVisible( false );
					}						
				}				
			}
		});

		textField.setForeground(new Color(0, 0, 0));
		textField.setFont(new Font("SansSerif", Font.BOLD, 11));
		textField.setBounds(176, 44, 126, 27);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setForeground(new Color(204, 0, 0));
		lblWarning.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblWarning.setBounds(68, 82, 208, 30);
		contentPanel.add(lblWarning);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
			{
				okButton = new JButton("Guardar");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						
						if ( textField.getText().length() == 0 ) {
							
							lblWarning.setText("Debes introducir un nombre");
							
						}
						else {
							setVisible( false );
						}
						
						
					}
				});

				okButton.setForeground(new Color(0, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getTxtFieldContent(){	
		
		return textField.getText();

	}
}
