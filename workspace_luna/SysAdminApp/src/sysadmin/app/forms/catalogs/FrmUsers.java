package sysadmin.app.forms.catalogs;


import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

import sysadmin.app.custom.JTextFieldLimit;
import sysadmin.app.data.UsersDAO;
import sysadmin.app.model.ComboBoxBean;
import sysadmin.app.model.UsersBean;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmUsers extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername, txtFirstname, txtLastname;
	private JPasswordField pwdPassword1, pwdPassword2;
	private JTextField txtPhoto;
    private JTable tblData;
    private JScrollPane scrData;
    private JButton btnSave, btnCancel, btnFind, btnDelete, btnFirst, btnForward, btnLast, btnNew, btnNext, btnUpdate, btnPhoto;
    private JDateChooser dtcStartdate, dtcEnddate;
    @SuppressWarnings("rawtypes")
	private JComboBox cmbProfile;
    private JLabel lblMessage;
    private boolean glIsNew = false;
    private int glUserAct = 0;
    private DefaultTableModel dtmTable;
    private UsersDAO dtData;
    private List<UsersBean> listData;
    private List<ComboBoxBean> listCmb;
    private int ginCountUsers;

	/**
	 * Create the frame.
	 */
    
	@SuppressWarnings({"rawtypes" })
	public FrmUsers() {
		dtData = new UsersDAO();
		// Propiedades de la ventana
		setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(null);
        
        cmbProfile = new JComboBox();
        cmbProfile.setFont(new Font("Monaco", Font.PLAIN, 10));
        cmbProfile.setBounds(327, 16, 150, 27);
        getContentPane().add(cmbProfile);

		fnLoadCmb();
        
        txtUsername = new JTextField();
        txtUsername.setBackground(UIManager.getColor("ToolTip.background"));
        txtUsername.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtUsername.setColumns(10);
        txtUsername.setBounds(97, 14, 150, 28);
        txtUsername.setDocument(new JTextFieldLimit(10));
        getContentPane().add(txtUsername);
        
        txtFirstname = new JTextField();
        txtFirstname.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtFirstnameFocusLost(e);
        	}
        });
        txtFirstname.setBackground(UIManager.getColor("ToolTip.background"));
        txtFirstname.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtFirstname.setColumns(10);
        txtFirstname.setBounds(97, 44, 150, 28);
        txtFirstname.setDocument(new JTextFieldLimit(50));
        getContentPane().add(txtFirstname);
        
        txtLastname = new JTextField();
        txtLastname.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtLastnameFocusLost(e);
        	}
        });
        txtLastname.setBackground(UIManager.getColor("ToolTip.background"));
        txtLastname.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtLastname.setColumns(10);
        txtLastname.setBounds(327, 44, 150, 28);
        txtLastname.setDocument(new JTextFieldLimit(100));
        getContentPane().add(txtLastname);
        
        pwdPassword1 = new JPasswordField();
        pwdPassword1.setBackground(UIManager.getColor("ToolTip.background"));
        pwdPassword1.setFont(new Font("Monaco", Font.PLAIN, 10));
        pwdPassword1.setBounds(97, 74, 150, 28);
        pwdPassword1.setDocument(new JTextFieldLimit(20));
        getContentPane().add(pwdPassword1);
        
        pwdPassword2 = new JPasswordField();
        pwdPassword2.setBackground(UIManager.getColor("ToolTip.background"));
        pwdPassword2.setFont(new Font("Monaco", Font.PLAIN, 10));
        pwdPassword2.setBounds(327, 74, 150, 28);
        pwdPassword2.setDocument(new JTextFieldLimit(20));
        getContentPane().add(pwdPassword2);
        
        txtPhoto = new JTextField();
        txtPhoto.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtPhoto.setColumns(10);
        txtPhoto.setBounds(97, 134, 150, 28);
        txtPhoto.setDocument(new JTextFieldLimit(340));
        //txtPhoto
        getContentPane().add(txtPhoto);
        
        btnPhoto = new JButton("...");
        btnPhoto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnPhotoActionPerformed(e);
        	}
        });
        btnPhoto.setBounds(245, 134, 52, 29);
        getContentPane().add(btnPhoto);
        
        dtcStartdate = new JDateChooser();
        dtcStartdate.setBackground(UIManager.getColor("ToolTip.background"));
        dtcStartdate.setDateFormatString("dd-MMM-yyyy");
        dtcStartdate.setBounds(97, 104, 145, 28);
        getContentPane().add(dtcStartdate);
        
        dtcEnddate = new JDateChooser();
        dtcEnddate.setBackground(UIManager.getColor("ToolTip.background"));
        dtcEnddate.setDateFormatString("dd-MMM-yyyy");
        dtcEnddate.setBounds(327, 104, 145, 28);
        getContentPane().add(dtcEnddate);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUsuario.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblUsuario.setLabelFor(txtUsername);
        lblUsuario.setBounds(6, 20, 90, 16);
        getContentPane().add(lblUsuario);
        
        JLabel lblNombres = new JLabel("Nombre(s):");
        lblNombres.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombres.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblNombres.setLabelFor(txtFirstname);
        lblNombres.setBounds(6, 50, 90, 16);
        getContentPane().add(lblNombres);
        
        JLabel lblApellidos = new JLabel("Apellido(s):");
        lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellidos.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblApellidos.setLabelFor(txtLastname);
        lblApellidos.setBounds(245, 50, 80, 16);
        getContentPane().add(lblApellidos);
        
        JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
        lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContrasea.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblContrasea.setLabelFor(pwdPassword1);
        lblContrasea.setBounds(6, 80, 90, 16);
        getContentPane().add(lblContrasea);
        
        JLabel lblConfirmar = new JLabel("Confirmar:");
        lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConfirmar.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblConfirmar.setLabelFor(pwdPassword2);
        lblConfirmar.setBounds(245, 80, 80, 16);
        getContentPane().add(lblConfirmar);
        
        JLabel lblFotografia = new JLabel("Fotografia:");
        lblFotografia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFotografia.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFotografia.setLabelFor(txtPhoto);
        lblFotografia.setBounds(6, 140, 90, 16);
        getContentPane().add(lblFotografia);
        
        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
        lblFechaInicial.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaInicial.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaInicial.setLabelFor(dtcStartdate);
        lblFechaInicial.setBounds(6, 110, 90, 16);
        getContentPane().add(lblFechaInicial);
        
        JLabel lblFechaFinal = new JLabel("Fecha Final:");
        lblFechaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaFinal.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaFinal.setLabelFor(dtcEnddate);
        lblFechaFinal.setBounds(245, 110, 80, 16);
        getContentPane().add(lblFechaFinal);
        
        JLabel lblPerfil = new JLabel("Perfil:");
        lblPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPerfil.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblPerfil.setBounds(245, 20, 80, 16);
        getContentPane().add(lblPerfil);
        
        btnFirst = new JButton("");
        btnFirst.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFirstActionPerformed(e);
        	}
        });
        btnFirst.setToolTipText("Primer registro");
        btnFirst.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Primero.png")));
        btnFirst.setBounds(16, 164, 42, 42);
        getContentPane().add(btnFirst);
        
        btnForward = new JButton("");
        btnForward.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnForwardActionPerformed(e);
        	}
        });
        btnForward.setToolTipText("Registro anterior");
        btnForward.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Anterior.png")));
        btnForward.setBounds(60, 164, 42, 42);
        getContentPane().add(btnForward);
        
        btnNext = new JButton("");
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNextActionPerformed(e);
        	}
        });
        btnNext.setToolTipText("Siguiente registro");
        btnNext.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Siguiente.png")));
        btnNext.setBounds(104, 164, 42, 42);
        getContentPane().add(btnNext);
        
        btnLast = new JButton("");
        btnLast.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLastActionPerformed(e);
        	}
        });
        btnLast.setToolTipText("Ultimo registro");
        btnLast.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Ultimo.png")));
        btnLast.setBounds(148, 164, 42, 42);
        getContentPane().add(btnLast);
        
        btnNew = new JButton("");
        btnNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewActionPerformed(e);
        	}
        });
        btnNew.setToolTipText("Nuevo registro");
        btnNew.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Nuevo.png")));
        btnNew.setBounds(200, 164, 42, 42);
        getContentPane().add(btnNew);
        
        btnUpdate = new JButton("");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnUpdateActionPerformed(e);
        	}
        });
        btnUpdate.setToolTipText("Modificar registro");
        btnUpdate.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Modificar.png")));
        btnUpdate.setBounds(244, 164, 42, 42);
        getContentPane().add(btnUpdate);
        
        btnSave = new JButton("");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnSaveActionPerformed(e);
        	}
        });
        btnSave.setToolTipText("Guardar registro");
        btnSave.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Guardar.png")));
        btnSave.setBounds(392, 164, 42, 42);
        getContentPane().add(btnSave);
        
        btnCancel = new JButton("");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelActionPerformed(e);
        	}
        });
        btnCancel.setToolTipText("Cancelar cambios");
        btnCancel.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Cancelar2.png")));
        btnCancel.setBounds(436, 164, 42, 42);
        getContentPane().add(btnCancel);
        
        btnDelete = new JButton("");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Borrar2.png")));
        btnDelete.setToolTipText("Borrar registro");
        btnDelete.setBounds(296, 164, 42, 42);
        getContentPane().add(btnDelete);
        
        btnFind = new JButton("");
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setIcon(new ImageIcon(FrmUsers.class.getResource("/images/Buscar.png")));
        btnFind.setToolTipText("Buscar registro");
        btnFind.setBounds(340, 164, 42, 42);

        tblData = new JTable();
        tblData.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		tblDataMouseClicked(e);
        	}
        });
        scrData = new JScrollPane();
        scrData.setViewportView(tblData);
        scrData.setBounds(17, 212, 460, 268);
        getContentPane().add(scrData);
        
        getContentPane().add(btnFind);
        
        lblMessage = new JLabel("");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblMessage.setBounds(16, 482, 461, 16);
        getContentPane().add(lblMessage);
        setTitle("Administraci\u00F3n de Usuarios");
		setBounds(2, 2, 520, 550);
		
		addInternalFrameListener(new InternalFrameListener() {
			
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				frmInternalFrameOpened();
			}
			
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void frmInternalFrameOpened(){
		fnLoadTable();
        fnShowRow();
        fnReadOnlyRow();
	}
	private void fnReadOnlyRow(){
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnFind.setEnabled(true);
        btnDelete.setEnabled(true);
        btnFirst.setEnabled(true);
        btnForward.setEnabled(true);
        btnLast.setEnabled(true);
        btnNew.setEnabled(true);
        btnNext.setEnabled(true);
        btnUpdate.setEnabled(true);
        txtUsername.setEnabled(false);
        dtcStartdate.setEnabled(false);
        dtcEnddate.setEnabled(false);
        pwdPassword1.setEnabled(false);
        pwdPassword2.setEnabled(false);
        txtFirstname.setEnabled(false);
        txtLastname.setEnabled(false);
        txtPhoto.setEnabled(false);
        cmbProfile.setEnabled(false);
        btnPhoto.setEnabled(false);
        dtcStartdate.setDate(null);
        dtcEnddate.setDate(null);
        glIsNew = false;
		cmbProfile.setSelectedIndex(-1);
		glUserAct = 0;
    }
	private void fnUpdateRow(){
		btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnFind.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnForward.setEnabled(false);
        btnLast.setEnabled(false);
        btnNew.setEnabled(false);
        btnNext.setEnabled(false);
        btnUpdate.setEnabled(false);
        txtUsername.setEnabled(true);
        dtcStartdate.setEnabled(true);
        dtcEnddate.setEnabled(true);
        pwdPassword1.setEnabled(true);
        pwdPassword2.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhoto.setEnabled(true);
        cmbProfile.setEnabled(true);
        txtUsername.requestFocusInWindow();
        glIsNew = false;
	}
	private void fnInsertRow(){
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnFind.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnForward.setEnabled(false);
        btnLast.setEnabled(false);
        btnNew.setEnabled(false);
        btnNext.setEnabled(false);
        btnUpdate.setEnabled(false);
        txtUsername.setEnabled(true);
        dtcStartdate.setEnabled(true);
        dtcEnddate.setEnabled(true);
        pwdPassword1.setEnabled(true);
        pwdPassword2.setEnabled(true);
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtPhoto.setEnabled(true);
        cmbProfile.setEnabled(true);
        txtUsername.setText("");
        dtcStartdate.setDate(new java.util.Date());
        dtcEnddate.setDate(new java.util.Date());
        txtFirstname.setText("");
        txtLastname.setText("");
        txtPhoto.setText("");
        pwdPassword1.setText("");
        pwdPassword2.setText("");
		cmbProfile.setSelectedIndex(-1);
		glIsNew = true;
    }
	private void txtFirstnameFocusLost(FocusEvent e){
		txtFirstname.setText(txtFirstname.getText().toUpperCase());
	}
	private void txtLastnameFocusLost(FocusEvent e){
		txtLastname.setText(txtLastname.getText().toUpperCase());
	}
	private void fnLoadTable(){
		listData = dtData.fnLoadData();
		ginCountUsers = listData.size();
        String strHeaderTittles[] = {"No Usuario", "Usuario", "Perfil", "Nombre", "Apellidos"};
        String strDataReg[] = new String[5];
        dtmTable = new DefaultTableModel(null, strHeaderTittles);
        /**/
        for (UsersBean u : listData){
            //strDataReg[0] = String.valueOf(u.getUser_id());
        	strDataReg[0] = ""+u.getUser_id();
            strDataReg[1] = u.getUsername();
            strDataReg[2] = getProfileDesc(u.getSegmnent1());
            strDataReg[3] = u.getFirst_name();
            strDataReg[4] = u.getLast_name();
            dtmTable.addRow(strDataReg);
        }
        /**/
        tblData.setModel(dtmTable);
    }
	private void fnShowRow(){
        txtUsername.setText(listData.get(glUserAct).getUsername());
        dtcStartdate.setDate(listData.get(glUserAct).getStart_date());
        dtcEnddate.setDate(listData.get(glUserAct).getEnd_date());
        txtFirstname.setText(listData.get(glUserAct).getFirst_name());
        txtLastname.setText(listData.get(glUserAct).getLast_name());
        txtPhoto.setText(listData.get(glUserAct).getPhoto());
        cmbProfile.setSelectedItem(getProfileDesc(listData.get(glUserAct).getSegmnent1()));
    }
	private void btnFirstActionPerformed(ActionEvent e){
		//TODO
		glUserAct = 0;
		fnShowRow();
	}
	private void btnForwardActionPerformed(ActionEvent e){
		//TODO
        if(glUserAct == 0) return;
        glUserAct--;
        fnShowRow();
	}
	private void btnNextActionPerformed(ActionEvent e){
		//TODO
        if(glUserAct == ginCountUsers-1) return;
        glUserAct++;
        fnShowRow();
	}
	private void btnLastActionPerformed(ActionEvent e){
		//TODO
        glUserAct = ginCountUsers -1;
        fnShowRow();
	}
	private void btnNewActionPerformed(ActionEvent e){
		//TODO
		fnInsertRow();
	}
	private void btnUpdateActionPerformed(ActionEvent e){
		//TODO
		fnUpdateRow();
	}
	private void btnSaveActionPerformed(ActionEvent e){
		//TODO
		// Realizamos Validaciones
        String lstPassword1 = String.valueOf(pwdPassword1.getPassword());
        String lstPassword2 = String.valueOf(pwdPassword2.getPassword());
        String lstMessage = "";
        int linExistUser;
        linExistUser = dtData.fnUserExist(txtUsername.getText()); 

        if(txtUsername.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un usuario");
            txtUsername.requestFocusInWindow();
            return;
        }
        if(cmbProfile.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this,"Debe de seleccionar un perfil de usuario");
            cmbProfile.requestFocusInWindow();
            return;
        }
        if(lstPassword1.equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un contraseña de usuario");
            pwdPassword1.requestFocusInWindow();
            return;
        }
        if(lstPassword2.equals("")){
            JOptionPane.showMessageDialog(this,"Debe de confirmar la contraseña de usuario");
            pwdPassword2.requestFocusInWindow();
            return;
        }
        if(txtFirstname.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un nombre de usuario");
            txtFirstname.requestFocusInWindow();
            return;
        }
        if(txtLastname.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un apellido de usuario");
            txtLastname.requestFocusInWindow();
            return;
        }
        if(dtcStartdate.getDate().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar una fecha inicial");
            dtcStartdate.requestFocusInWindow();
            return;
        }
        if(glIsNew == true && linExistUser != 0){
            JOptionPane.showMessageDialog(this,"Este usuario: '"+txtUsername.getText()+"' ya existe, favor de ingresar otro");
            txtUsername.setText("");
            txtUsername.requestFocusInWindow();
            return;
        }
        if (!lstPassword1.equals(lstPassword2)){
            JOptionPane.showMessageDialog(this,"La contraseña y la confirmacion no coinciden, favor de ingresarlas de nuevo");
            pwdPassword1.setText("");
            pwdPassword2.setText("");
            pwdPassword1.requestFocusInWindow();
            return;
        }
        if (glIsNew){
        	Date ldtStrarDate = new java.sql.Date(dtcStartdate.getDate().getTime());
        	Date ldtEndDate = new java.sql.Date(dtcEnddate.getDate().getTime());
            lstMessage = dtData.fnInsertUsers(txtUsername.getText(), txtFirstname.getText(), txtLastname.getText(), 
            		lstPassword1, getProfileId(cmbProfile.getSelectedItem().toString()), txtPhoto.getText(), ldtStrarDate, ldtEndDate, 
            		getProfileName(cmbProfile.getSelectedItem().toString()), "", "", "", "", "", "", "", "");
            if (lstMessage.equals("OK")){
            	lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
            	lblMessage.setText("El usuario se agrego correctamente");
            }
            else{
        		lblMessage.setForeground(UIManager.getColor("Button.select"));
            	lblMessage.setText("No se pudo agregar el usuario: "+lstMessage);
            	return;
            }
        }
        else {
        	Date ldtEndDate = new java.sql.Date(dtcEnddate.getDate().getTime());
        	lstMessage = dtData.fnUpdateUsers(String.valueOf(listData.get(glUserAct).getUser_id()), txtFirstname.getText(), txtLastname.getText(), 
        			getProfileId(cmbProfile.getSelectedItem().toString()), txtPhoto.getText(), ldtEndDate, getProfileName(cmbProfile.getSelectedItem().toString()), 
        			"", "", "", "", "", "", "", "");
        	if (lstMessage.equals("OK")){
            	lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
            	lblMessage.setText("El usuario se actualizo correctamente");
            }
            else{
        		lblMessage.setForeground(UIManager.getColor("Button.select"));
            	lblMessage.setText("No se pudo actualizar el usuario: "+lstMessage);
            	return;
            }
        }
        // LLenamos la tabla de nuevo
        fnLoadTable();
        fnReadOnlyRow();
        pwdPassword1.setText("");
        pwdPassword2.setText("");
        glUserAct = 0;
        fnShowRow();
	}
	private void btnCancelActionPerformed(ActionEvent e){
		//TODO
        //fnShowRow();
        fnReadOnlyRow();
	}
	private void btnDeleteActionPerformed(ActionEvent e){
		//TODO 
		int linRespuesta = JOptionPane.showConfirmDialog(this, "esta seguro de querer borrar el registro".toUpperCase());
        if (linRespuesta !=0) return;
        String lstMessage = dtData.fnDeleteUsers(String.valueOf(listData.get(glUserAct).getUser_id()));
        //JOptionPane.showMessageDialog(this, lstMessage);
        if(!lstMessage.equals("OK")){
    		lblMessage.setForeground(UIManager.getColor("Button.select"));
        	lblMessage.setText("Error al borrar al usuario: "+lstMessage);
        	return;
        }
        else{
        	lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
        	lblMessage.setText("El usuario fue borrado exitosamente!");
        }
        glUserAct = 0;
        fnLoadTable();
        fnShowRow();
	}
	private void btnFindActionPerformed(ActionEvent e){
		//TODO
		String lstUsername = JOptionPane.showInputDialog(this,"Usuario");
        if (lstUsername.equals(""))return;
        int linPosition = fnUserExist(lstUsername);

        if (linPosition==-1){
            JOptionPane.showMessageDialog(this, "El usuario no existe");
        }
        glUserAct = linPosition;
        fnShowRow();
	}
	private void btnPhotoActionPerformed(ActionEvent e){
		//TODO
	}
	private void tblDataMouseClicked(MouseEvent e){
		glUserAct = tblData.getSelectedRow();
        fnShowRow();
	}
	private int fnUserExist(String p_username){
        for (int i=0; i<ginCountUsers;i++)
        {
            if (listData.get(i).getUsername().equals(p_username)) return i;
        }
        return -1;
	}
	@SuppressWarnings("unchecked")
	private void fnLoadCmb(){
		listCmb = dtData.fnLoadDataCmb();
        /**/
        for (ComboBoxBean u : listCmb){
        	cmbProfile.addItem(u.getCmb_description());
        }
	}
	private String getProfileDesc(String p_name){
		String lsrReturn = "";
		for (ComboBoxBean u : listCmb){
        	if(u.getCmb_name().equals(p_name)){
        		lsrReturn = u.getCmb_description();
        		return lsrReturn; 
        	}
        }
		return lsrReturn;
	}
	
    private String getProfileName(String p_name){
		String lsrReturn = "";
		for (ComboBoxBean u : listCmb){
        	if(u.getCmb_description().equals(p_name)){
        		lsrReturn = u.getCmb_name();
        		return lsrReturn; 
        	}
        }
		return lsrReturn;
    }
    private String getProfileId(String p_name){
		String lstReturn = "-1";
		for (ComboBoxBean u : listCmb){
        	if(u.getCmb_description().equals(p_name)){
        		lstReturn = String.valueOf(u.getCmb_id());
        		return lstReturn; 
        	}
        }
		return lstReturn;
    }
}
