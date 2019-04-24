package sysadmin.app.forms.system;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;

import sysadmin.app.data.UsersDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmChangePassword extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField pwdActpassword;
	private JPasswordField pwdNewpassword1;
	private JPasswordField pwdNewpassword2;
	private JLabel lblCambiarContraseaActual;
	private JLabel lblMessage;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private UsersDAO dataChgPass;
	public JLabel glstUsername;
	public String gstUserId = "1001"; 

	/**
	 * Create the frame.
	 */

	@SuppressWarnings("deprecation")
	public FrmChangePassword() {
		// Propiedades de la ventana
		setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("Cambiar Contrase\u00F1a");
		setBounds(100, 100, 350, 230);
        
        lblCambiarContraseaActual = new JLabel("Cambiar Contrase\u00F1a a:");
        lblCambiarContraseaActual.setFont(new Font("Monaco", Font.PLAIN, 11));
        lblCambiarContraseaActual.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCambiarContraseaActual.setBounds(25, 16, 173, 16);
        getContentPane().add(lblCambiarContraseaActual);
        
        JLabel lblContraseaAnterior = new JLabel("Contrase\u00F1a Anterior:");
        lblContraseaAnterior.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContraseaAnterior.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblContraseaAnterior.setBounds(15, 50, 134, 16);
        getContentPane().add(lblContraseaAnterior);
        
        JLabel lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a:");
        lblNuevaContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNuevaContrasea.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblNuevaContrasea.setBounds(15, 75, 134, 16);
        getContentPane().add(lblNuevaContrasea);
        
        JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a:");
        lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConfirmarContrasea.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblConfirmarContrasea.setBounds(15, 100, 134, 16);
        getContentPane().add(lblConfirmarContrasea);
        
        pwdActpassword = new JPasswordField();
        pwdActpassword.setFont(new Font("Monaco", Font.PLAIN, 10));
        pwdActpassword.setBounds(160, 44, 133, 28);
        getContentPane().add(pwdActpassword);
        
        pwdNewpassword1 = new JPasswordField();
        pwdNewpassword1.setFont(new Font("Monaco", Font.PLAIN, 10));
        pwdNewpassword1.setBounds(160, 69, 133, 28);
        getContentPane().add(pwdNewpassword1);
        
        pwdNewpassword2 = new JPasswordField();
        pwdNewpassword2.setFont(new Font("Monaco", Font.PLAIN, 10));
        pwdNewpassword2.setBounds(160, 94, 133, 28);
        getContentPane().add(pwdNewpassword2);
        
        lblMessage = new JLabel("");
        lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
        lblMessage.setFont(new Font("Monaco", Font.PLAIN, 9));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setBounds(25, 128, 287, 16);
        getContentPane().add(lblMessage);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAceptarActionPerformed(e);
        	}
        });
        btnAceptar.setBounds(138, 149, 92, 29);
        getContentPane().add(btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelarActionPerformed(e);
        	}
        });
        btnCancelar.setBounds(228, 149, 92, 29);
        getContentPane().add(btnCancelar);
        
        // Orden de los Tabs en el formulario
        pwdActpassword.setNextFocusableComponent(pwdNewpassword1);
		pwdNewpassword1.setNextFocusableComponent(pwdNewpassword2);
		pwdNewpassword2.setNextFocusableComponent(btnAceptar);
		btnAceptar.setNextFocusableComponent(btnCancelar);
		btnCancelar.setNextFocusableComponent(pwdActpassword);
		
		glstUsername = new JLabel("label");
		glstUsername.setFont(new Font("Monaco", Font.PLAIN, 11));
		glstUsername.setBounds(199, 16, 100, 16);
		getContentPane().add(glstUsername);
		glstUsername.setVisible(true);

	}
	
	private void btnAceptarActionPerformed(ActionEvent e){
		lblMessage.setForeground(UIManager.getColor("Button.select")); 
		String lstUsername = glstUsername.getText();
		String lstActPassword = new String (pwdActpassword.getPassword()); 
		String lstNewPassword1 = new String (pwdNewpassword1.getPassword()); 
		String lstNewPassword2 = new String (pwdNewpassword2.getPassword());
		String lstValidaPasswordAct = fnValidaActPassword(lstUsername,lstActPassword);
		boolean lstValidaPasswordNew = fnValidaNewPassword(lstNewPassword1,lstNewPassword2);
		String lstChangePasswordNew = fnChangePasswordNew(gstUserId, lstUsername,lstActPassword,lstNewPassword1);
		if (!lstValidaPasswordAct.equals("OK")){
			lblMessage.setText(lstValidaPasswordAct);
			pwdActpassword.setText("");
			pwdActpassword.requestFocusInWindow();
			return;
		}
		if (!lstValidaPasswordNew){
			pwdNewpassword1.setText("");
			pwdNewpassword2.setText("");
			pwdNewpassword1.requestFocusInWindow();
			return;
		}
		if (!lstChangePasswordNew.equals("OK")){
			lblMessage.setText(lstChangePasswordNew);
			pwdActpassword.setText("");
			pwdNewpassword1.setText("");
			pwdNewpassword2.setText("");
			pwdActpassword.requestFocusInWindow();
			return;
		}
		
		lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
		lblMessage.setText("La contrase\u00F1a se actualizo correctamente");
		fnLimpiaFormulario();
	}
	
	private String fnValidaActPassword(String p_username, String p_password){
		String lstReturn = "";
		int linValidate = 0;
		dataChgPass = new UsersDAO();
		linValidate = dataChgPass.fnValidatePasswordChgPass(p_username, p_password);
		if(linValidate == 0){
			lstReturn = "La contrase\u00F1a actual no es valida";
		}
		else {
			lstReturn = "OK";
		}
		return lstReturn;
	}
	
	private boolean fnValidaNewPassword(String p_password_new1, String p_password_new2){
		boolean lbReturn = false;
		if(!p_password_new1.equals(p_password_new2)){
			lbReturn = false;
		}
		else{
			lbReturn = true;
		}
		return lbReturn;
	}
	
	private String fnChangePasswordNew(String p_user_id, String p_username, String p_password_new, String p_password_old){
		String lstReturn = "";
		String linValidate = "";
		dataChgPass = new UsersDAO();
		linValidate = dataChgPass.fnChangePasswordChgPass(p_user_id, p_username, p_password_new, p_password_old);
		if(!linValidate.equals("1")){
			lstReturn = "No se pudo cambiar la contrase\u00F1a nueva: "+linValidate;
		}
		else {
			lstReturn = "OK";
		}
		return lstReturn;
	}
	
	private void btnCancelarActionPerformed(ActionEvent e){
		hide();
	}
	
	private void fnLimpiaFormulario(){
		pwdActpassword.setText("");
		pwdNewpassword1.setText("");
		pwdNewpassword2.setText("");
		pwdActpassword.requestFocusInWindow();
	}
}
