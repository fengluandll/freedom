package sysadmin.app.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import sysadmin.app.data.LoginDAO;
import sysadmin.app.model.LoginBean;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Toolkit;

public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JLabel lblMessage;
	private int ginValidation = 0;
	private String gstUserId, gstFirstName, gstLastName,  gstProfileName, gstStartDate, gstEndDate;
	private String gstFullname;
	
	public String getGstFullname() {
		return gstFullname;
	}

	public void setGstFullname(String gstFullname) {
		this.gstFullname = gstFullname;
	}

	public String getGstUserId() {
		return gstUserId;
	}

	public void setGstUserId(String gstUserId) {
		this.gstUserId = gstUserId;
	}

	public String getGstFirstName() {
		return gstFirstName;
	}

	public void setGstFirstName(String gstFirstName) {
		this.gstFirstName = gstFirstName;
	}

	public String getGstLastName() {
		return gstLastName;
	}

	public void setGstLastName(String gstLastName) {
		this.gstLastName = gstLastName;
	}

	public String getGstProfileName() {
		return gstProfileName;
	}

	public void setGstProfileName(String gstProfileName) {
		this.gstProfileName = gstProfileName;
	}

	public String getGstStartDate() {
		return gstStartDate;
	}

	public void setGstStartDate(String gstStartDate) {
		this.gstStartDate = gstStartDate;
	}

	public String getGstEndDate() {
		return gstEndDate;
	}

	public void setGstEndDate(String gstEndDate) {
		this.gstEndDate = gstEndDate;
	}
	private LoginDAO dataLogin;
	private List<LoginBean> listData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin wnd = new FrmLogin();
					wnd.setLocationRelativeTo(null);
					wnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/images/SISE.png")));
		setFont(new Font("Monaco", Font.PLAIN, 10));
		setResizable(false);
		setTitle("SysAdmin - Sistema de Administraci\u00F3n Empresarial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setForeground(UIManager.getColor("Button.light"));
		lblLogo.setBackground(UIManager.getColor("Button.light"));
		lblLogo.setPreferredSize(new Dimension(200, 120));
		lblLogo.setBounds(16, 60, 200, 120);
		contentPane.add(lblLogo);
		
		JLabel lblBienvenidos = new JLabel("Bienvenidos Sistema Administrativo");
		lblBienvenidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidos.setFont(new Font("Monaco", Font.PLAIN, 16));
		lblBienvenidos.setBounds(6, 24, 468, 16);
		contentPane.add(lblBienvenidos);
		
		txtUsername = new JTextField();
		txtUsername.setScrollOffset(1);
		txtUsername.setFont(new Font("Monaco", Font.PLAIN, 10));
		txtUsername.setBounds(292, 80, 140, 28);
		txtUsername.setText("nelsomar");
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Monaco", Font.PLAIN, 10));
		pwdPassword.setBounds(292, 113, 140, 28);
		pwdPassword.setText("Nelsomar23");
		contentPane.add(pwdPassword);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setLabelFor(txtUsername);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblUsuario.setBounds(210, 87, 70, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setLabelFor(pwdPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Monaco", Font.PLAIN, 10));
		lblPassword.setBounds(210, 120, 70, 16);
		contentPane.add(lblPassword);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptarActionPerformed(e);
			}
		});
		btnAceptar.setFont(new Font("Monaco", Font.PLAIN, 12));
		btnAceptar.setBounds(273, 181, 100, 30);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionPerformed(e);
			}
		});
		btnCancelar.setFont(new Font("Monaco", Font.PLAIN, 12));
		btnCancelar.setBounds(374, 181, 100, 30);
		contentPane.add(btnCancelar);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Monaco", Font.PLAIN, 9));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(UIManager.getColor("CheckBox.select"));
		lblMessage.setBounds(220, 153, 249, 16);
		contentPane.add(lblMessage);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
		
		/* Declaracion del icono * /
		//Icon imgImagen = new ImageIcon(getClass().getResource("../../../images/SISE.png"));
        //Icon imgAceptar = new ImageIcon(getClass().getResource("../../../images/Aceptar2.png"));
        //Icon imgCancel = new ImageIcon(getClass().getResource("../../../images/Cancelar.png"));
        /**/
        
        // Asignar los iconos a sus respectivos objetos
        lblLogo.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/SISE.png")));
        btnAceptar.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/menuicon/Aceptar2.png")));
        btnCancelar.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/menuicon/Cancelar.png")));
        contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsername, pwdPassword, btnAceptar, btnCancelar}));
		txtUsername.setNextFocusableComponent(pwdPassword);
		pwdPassword.setNextFocusableComponent(btnAceptar);
		btnAceptar.setNextFocusableComponent(btnCancelar);
		btnCancelar.setNextFocusableComponent(txtUsername);
		txtUsername.requestFocusInWindow();
	}
	private void btnAceptarActionPerformed(ActionEvent e){
			String lstPassword = new String (pwdPassword.getPassword()); 
			String lstUsername = new String (txtUsername.getText());
			dataLogin = new LoginDAO();
			int linValidate = dataLogin.fnLoginValidate(lstUsername, lstPassword);
	        if (linValidate == 0 ){
			//if (!lstUsername.equals("nelsomar") && !lstPassword.equals("Nelsomar23")){
	            //JOptionPane.showMessageDialog(null, "Usuario o Contrase\u00F1a no validos!".toUpperCase());
	        	lblMessage.setText("Usuario o Contrase\u00F1a no validos");
	            txtUsername.setText("");
	            pwdPassword.setText("");
	            txtUsername.requestFocusInWindow();
	            ginValidation++;
	            fnValidaIntentos(ginValidation);
	            return;
	        }
	        /**/
	        LoginDAO dtData = new LoginDAO();
	        listData = dtData.fnGetInfoUser(lstUsername, lstPassword);
	        for (LoginBean u : listData){
	        	setGstUserId(String.valueOf(u.getUser_id()));
	            setGstFirstName(u.getFirst_name());
	            setGstLastName(u.getLast_name());
	            setGstProfileName(u.getProfile_name());
	            setGstFullname(u.getFull_name());
	            setGstStartDate(String.valueOf(u.getStart_date()));
	            setGstEndDate(String.valueOf(u.getEnd_date()));
	        }
	        /**/
	        FrmMain wndMain = new FrmMain();
	        this.setVisible(false);
	        //wndMain.setDataUsers(dataUsers);
	        wndMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        wndMain.setLocationRelativeTo(null);
	        wndMain.lblUsername.setText(lstUsername);
	        wndMain.lblUserid.setText(getGstUserId());
	        wndMain.lblProfile.setText(getGstProfileName());
	        wndMain.lblStartdate.setText(getGstStartDate());
	        wndMain.lblEnddate.setText(getGstEndDate());
	        wndMain.lblFullname.setText(getGstFirstName()+ " "+getGstLastName());
	        wndMain.setVisible(true);
    }
    private void btnCancelarActionPerformed(ActionEvent e){
        // salimos de la aplicacion
        System.exit(0);
    }
    private void fnValidaIntentos(int p_intentos){
        if(p_intentos == 3){
        	System.exit(0);
        }
    }
}
