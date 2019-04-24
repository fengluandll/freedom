package sysadmin.app.forms.system;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAboutBox extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public FrmAboutBox() {
		setTitle("Acerca de SAE");
		setFont(new Font("Monaco", Font.PLAIN, 10));
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setResizable(false);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOkActionPerformed(e);
			}
		});
		btnOk.setFont(new Font("Monaco", Font.PLAIN, 10));
		btnOk.setBounds(418, 269, 75, 29);
		contentPane.add(btnOk);
		
		JLabel lblSieSistema = new JLabel("SAE - Sistema Administrativo Empresarial");
		lblSieSistema.setForeground(UIManager.getColor("EditorPane.inactiveForeground"));
		lblSieSistema.setFont(new Font("Monaco", Font.PLAIN, 14));
		lblSieSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblSieSistema.setBounds(153, 35, 340, 16);
		contentPane.add(lblSieSistema);
		
		JLabel lblVendorSiseMxico = new JLabel("Vendor: SISE M\u00E9xico");
		lblVendorSiseMxico.setForeground(UIManager.getColor("PasswordField.inactiveForeground"));
		lblVendorSiseMxico.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendorSiseMxico.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblVendorSiseMxico.setBounds(15, 224, 126, 16);
		contentPane.add(lblVendorSiseMxico);
		
		JLabel lblVersion = new JLabel("Version: 1.0");
		lblVersion.setForeground(UIManager.getColor("PasswordField.inactiveForeground"));
		lblVersion.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersion.setBounds(25, 252, 126, 16);
		contentPane.add(lblVersion);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setForeground(UIManager.getColor("PasswordField.inactiveForeground"));
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblAutor.setBounds(6, 177, 69, 16);
		contentPane.add(lblAutor);
		
		JLabel lblNelsoOmarFernndez = new JLabel("Nelso Omar Fern\u00E1ndez Morales");
		lblNelsoOmarFernndez.setForeground(UIManager.getColor("PasswordField.inactiveForeground"));
		lblNelsoOmarFernndez.setHorizontalAlignment(SwingConstants.CENTER);
		lblNelsoOmarFernndez.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblNelsoOmarFernndez.setBounds(5, 199, 241, 16);
		contentPane.add(lblNelsoOmarFernndez);
		
		JLabel lblAboutbox = new JLabel("");
		lblAboutbox.setIcon(new ImageIcon(FrmAboutBox.class.getResource("/images/AboutBox1.png")));
		lblAboutbox.setBounds(5, 5, 500, 300);
		contentPane.add(lblAboutbox);
	}
	private void btnOkActionPerformed(ActionEvent e){
		setVisible(false);
	}
}
