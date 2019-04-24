package sysadmin.app.forms.catalogs;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sysadmin.app.custom.JTextFieldLimit;
import sysadmin.app.data.CustomerDAO;
import sysadmin.app.model.CustomerBean;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class FrmCustomers extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JTextField txtRfc;
	private JTextField txtBusinessname;
	private JTextField txtAdressline1;
	private JTextField txtAdressline2;
	private JTextField txtAdressline3;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtCountry;
	private JTextField txtPostalcode;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JDateChooser dtcStartdate;
	private JDateChooser dtcEnddate;
	private JButton btnFirst;
	private JButton btnForward;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnFind;
	private JLabel lblFechasEfectivas;
	private boolean glIsNew;
	private int glCustomerAct;
    private CustomerDAO dtData;
    private List<CustomerBean> listData;
    private int ginCountCustomers = 0;
    private JLabel lblMessage;
    private JTable tblData;
    private JScrollPane scrData;
    private DefaultTableModel dtmTable;
    public  int ginUserId;

	public int getGinUserId() {
		return ginUserId;
	}
	public void setGinUserId(int ginUserId) {
		this.ginUserId = ginUserId;
	}
	/**
	 * Create the frame.
	 */
	public FrmCustomers() {
		dtData = new CustomerDAO();
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				frmInternalFrameOpened();
			}
		});
		// Propiedades de la ventana
		setClosable(true);
        setIconifiable(true);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("Administraci\u00F3n de Clientes");
		setBounds(2, 2, 550, 430);
        
        txtFirstname = new JTextField();
        txtFirstname.setBackground(UIManager.getColor("Button.highlight"));
        txtFirstname.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtFirstname.setText(txtFirstname.getText().toUpperCase());
        	}
        });
        txtFirstname.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtFirstname.setBounds(75, 17, 134, 28);
        getContentPane().add(txtFirstname);
        txtFirstname.setColumns(10);
        txtFirstname.setDocument(new JTextFieldLimit(100));
        
        txtLastname = new JTextField();
        txtLastname.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtLastname.setText(txtLastname.getText().toUpperCase());
        	}
        });
        txtLastname.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtLastname.setBounds(295, 17, 215, 28);
        getContentPane().add(txtLastname);
        txtLastname.setColumns(10);
        txtLastname.setDocument(new JTextFieldLimit(240));
        
        txtRfc = new JTextField();
        txtRfc.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtRfc.setText(txtRfc.getText().toUpperCase());
        	}
        });
        txtRfc.setBackground(UIManager.getColor("ToolTip.background"));
        txtRfc.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtRfc.setBounds(75, 45, 134, 28);
        getContentPane().add(txtRfc);
        txtRfc.setColumns(10);
        txtRfc.setDocument(new JTextFieldLimit(15));
        
        txtBusinessname = new JTextField();
        txtBusinessname.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtBusinessname.setText(txtBusinessname.getText().toUpperCase());
        	}
        });
        txtBusinessname.setBackground(UIManager.getColor("ToolTip.background"));
        txtBusinessname.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtBusinessname.setBounds(295, 45, 215, 28);
        getContentPane().add(txtBusinessname);
        txtBusinessname.setColumns(10);
        txtBusinessname.setDocument(new JTextFieldLimit(240));
        
        txtAdressline1 = new JTextField();
        txtAdressline1.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtAdressline1.setText(txtAdressline1.getText().toUpperCase());
        	}
        });
        txtAdressline1.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtAdressline1.setBounds(75, 95, 134, 28);
        getContentPane().add(txtAdressline1);
        txtAdressline1.setColumns(10);
        txtAdressline1.setDocument(new JTextFieldLimit(240));
        
        txtAdressline2 = new JTextField();
        txtAdressline2.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtAdressline2.setText(txtAdressline2.getText().toUpperCase());
        	}
        });
        txtAdressline2.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtAdressline2.setBounds(295, 95, 215, 28);
        getContentPane().add(txtAdressline2);
        txtAdressline2.setColumns(10);
        txtAdressline2.setDocument(new JTextFieldLimit(240));
        
        txtAdressline3 = new JTextField();
        txtAdressline3.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtAdressline3.setText(txtAdressline3.getText().toUpperCase());
        	}
        });
        txtAdressline3.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtAdressline3.setBounds(75, 123, 134, 28);
        getContentPane().add(txtAdressline3);
        txtAdressline3.setColumns(10);
        txtAdressline3.setDocument(new JTextFieldLimit(45));
        
        txtCity = new JTextField();
        txtCity.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtCity.setText(txtCity.getText().toUpperCase());
        	}
        });
        txtCity.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtCity.setBounds(295, 123, 134, 28);
        getContentPane().add(txtCity);
        txtCity.setColumns(10);
        txtCity.setDocument(new JTextFieldLimit(45));
        
        txtState = new JTextField();
        txtState.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtState.setText(txtState.getText().toUpperCase());
        	}
        });
        txtState.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtState.setBounds(75, 150, 134, 28);
        getContentPane().add(txtState);
        txtState.setColumns(10);
        txtState.setDocument(new JTextFieldLimit(45));
        
        txtCountry = new JTextField();
        txtCountry.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtCountry.setText(txtCountry.getText().toUpperCase());
        	}
        });
        txtCountry.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtCountry.setBounds(295, 150, 134, 28);
        getContentPane().add(txtCountry);
        txtCountry.setColumns(10);
        txtCountry.setDocument(new JTextFieldLimit(45));
        
        //txtPostalcode = new JTextField();
        txtPostalcode = new JFormattedTextField();
        txtPostalcode.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		txtPostalcodeKeyTyped(e);
        	}
        });
        txtPostalcode.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtPostalcode.setText(txtPostalcode.getText().toUpperCase());
        		lblMessage.setText("");
        	}
        });
        txtPostalcode.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtPostalcode.setBounds(75, 178, 134, 28);
        getContentPane().add(txtPostalcode);
        txtPostalcode.setColumns(10);
        txtPostalcode.setDocument(new JTextFieldLimit(5));
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtEmail.setBounds(295, 212, 215, 28);
        getContentPane().add(txtEmail);
        txtEmail.setColumns(10);
        txtEmail.setDocument(new JTextFieldLimit(240));
        
        txtPhone = new JTextField();
        txtPhone.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		txtPhoneKeyTyped(e);
        	}
        });
        txtPhone.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		txtPhone.setText(txtPhone.getText().toUpperCase());
        		lblMessage.setText("");
        	}
        });
        txtPhone.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtPhone.setBounds(75, 212, 134, 28);
        getContentPane().add(txtPhone);
        txtPhone.setColumns(10);
        txtPhone.setDocument(new JTextFieldLimit(10));
        
        dtcStartdate = new JDateChooser();
        dtcStartdate.setBounds(95, 278, 150, 28);
        getContentPane().add(dtcStartdate);
        
        dtcEnddate = new JDateChooser();
        dtcEnddate.setBounds(340, 278, 150, 28);
        getContentPane().add(dtcEnddate);
               
        btnFind = new JButton("");
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Buscar.png")));
        btnFind.setToolTipText("Buscar registro");
        btnFind.setBounds(47, 336, 42, 42);
        getContentPane().add(btnFind);
		
        btnFirst = new JButton("");
        btnFirst.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFirstActionPerformed(e);
        	}
        });
        btnFirst.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Primero.png")));
        btnFirst.setToolTipText("Primer registro");
        btnFirst.setBounds(102, 336, 42, 42);
        getContentPane().add(btnFirst);
        
        btnForward = new JButton("");
        btnForward.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnForwardActionPerformed(e);
        	}
        });
        btnForward.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Anterior.png")));
        btnForward.setToolTipText("Registro anterior");
        btnForward.setBounds(146, 336, 42, 42);
        getContentPane().add(btnForward);
        
        btnNext = new JButton("");
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNextActionPerformed(e);
        	}
        });
        btnNext.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Siguiente.png")));
        btnNext.setToolTipText("Siguiente registro");
        btnNext.setBounds(190, 336, 42, 42);
        getContentPane().add(btnNext);
        
        btnLast = new JButton("");
        btnLast.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLastActionPerformed(e);
        	}
        });
        btnLast.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Ultimo.png")));
        btnLast.setToolTipText("Ultimo registro");
        btnLast.setBounds(234, 336, 42, 42);
        getContentPane().add(btnLast);
        
        btnNew = new JButton("");
        btnNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewActionPerformed(e);
        	}
        });
        btnNew.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Nuevo.png")));
        btnNew.setToolTipText("Nuevo registro");
        btnNew.setBounds(292, 336, 42, 42);
        getContentPane().add(btnNew);
        
        btnDelete = new JButton("");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Borrar2.png")));
        btnDelete.setToolTipText("Modificar registro");
        btnDelete.setBounds(336, 336, 42, 42);
        getContentPane().add(btnDelete);
        
        btnSave = new JButton("");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnSaveActionPerformed(e);
        	}
        });
        btnSave.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Guardar.png")));
        btnSave.setToolTipText("Guardar registro");
        btnSave.setBounds(390, 336, 42, 42);
        getContentPane().add(btnSave);
        
        btnCancel = new JButton("");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelActionPerformed(e);
        	}
        });
        btnCancel.setIcon(new ImageIcon(FrmCustomers.class.getResource("/images/Cancelar2.png")));
        btnCancel.setToolTipText("Cancelar cambios");
        btnCancel.setBounds(434, 336, 42, 42);
        getContentPane().add(btnCancel);

        JLabel lblNombres = new JLabel("Nombre(s):");
        lblNombres.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombres.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblNombres.setLabelFor(txtFirstname);
        lblNombres.setBounds(6, 23, 70, 16);
        getContentPane().add(lblNombres);
        
        JLabel lblApellidoss = new JLabel("Apellidos(s):");
        lblApellidoss.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellidoss.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblApellidoss.setLabelFor(txtLastname);
        lblApellidoss.setBounds(210, 23, 80, 16);
        getContentPane().add(lblApellidoss);
        
        JLabel lblRfc = new JLabel("RFC:");
        lblRfc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRfc.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblRfc.setLabelFor(txtRfc);
        lblRfc.setBounds(6, 51, 70, 16);
        getContentPane().add(lblRfc);
        
        JLabel lblRazonSocial = new JLabel("Razon Social:");
        lblRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRazonSocial.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblRazonSocial.setLabelFor(txtBusinessname);
        lblRazonSocial.setBounds(210, 51, 80, 16);
        getContentPane().add(lblRazonSocial);
        
        JLabel lblDireccin = new JLabel("Direcci\u00F3n de Facturaci\u00F3n");
        lblDireccin.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblDireccin.setBounds(75, 77, 209, 16);
        getContentPane().add(lblDireccin);
        
        JLabel lblCalle = new JLabel("Calle:");
        lblCalle.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCalle.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblCalle.setLabelFor(txtAdressline1);
        lblCalle.setBounds(6, 101, 70, 16);
        getContentPane().add(lblCalle);
        
        JLabel lblColonia = new JLabel("Colonia:");
        lblColonia.setHorizontalAlignment(SwingConstants.RIGHT);
        lblColonia.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblColonia.setLabelFor(txtAdressline2);
        lblColonia.setBounds(210, 101, 80, 16);
        getContentPane().add(lblColonia);
        
        JLabel lblDelegacin = new JLabel("Delegaci\u00F3n:");
        lblDelegacin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDelegacin.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblDelegacin.setLabelFor(txtAdressline3);
        lblDelegacin.setBounds(6, 129, 70, 16);
        getContentPane().add(lblDelegacin);
        
        JLabel lblCiudad = new JLabel("Ciudad:");
        lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCiudad.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblCiudad.setLabelFor(txtCity);
        lblCiudad.setBounds(210, 129, 80, 16);
        getContentPane().add(lblCiudad);
        
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEstado.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblEstado.setLabelFor(txtState);
        lblEstado.setBounds(6, 156, 70, 16);
        getContentPane().add(lblEstado);
        
        JLabel lblPais = new JLabel("Pais:");
        lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPais.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblPais.setLabelFor(txtCountry);
        lblPais.setBounds(214, 156, 80, 16);
        getContentPane().add(lblPais);
        
        JLabel lblCp = new JLabel("C.P.:");
        lblCp.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCp.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblCp.setLabelFor(txtPostalcode);
        lblCp.setBounds(6, 184, 70, 16);
        getContentPane().add(lblCp);
        
        JLabel lblEmail = new JLabel("eMail:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblEmail.setLabelFor(txtEmail);
        lblEmail.setBounds(210, 218, 80, 16);
        getContentPane().add(lblEmail);
        
        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefono.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblTelefono.setLabelFor(txtPhone);
        lblTelefono.setBounds(6, 218, 70, 16);
        getContentPane().add(lblTelefono);
        
        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
        lblFechaInicial.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaInicial.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaInicial.setLabelFor(dtcStartdate);
        lblFechaInicial.setBounds(6, 283, 85, 16);
        getContentPane().add(lblFechaInicial);
        
        JLabel lblFechaFinal = new JLabel("Fecha Final:");
        lblFechaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaFinal.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaFinal.setLabelFor(dtcEnddate);
        lblFechaFinal.setBounds(255, 283, 80, 16);
        getContentPane().add(lblFechaFinal);
        
        lblFechasEfectivas = new JLabel("Fechas efectivas");
        lblFechasEfectivas.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechasEfectivas.setBounds(75, 252, 209, 16);
        getContentPane().add(lblFechasEfectivas);
        
        lblMessage = new JLabel("");
        lblMessage.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setBounds(16, 315, 494, 16);
        getContentPane().add(lblMessage);
        
        tblData = new JTable();
        tblData.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//tblDataMouseClicked(e);
        	}
        });
        scrData = new JScrollPane();
        scrData.setViewportView(tblData);
        scrData.setBounds(600, 212, 460, 265);
        getContentPane().add(scrData);
	}
	private void frmInternalFrameOpened(){
        fnReadOnlyRow();
	}
	private void fnReadOnlyRow(){
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnFind.setEnabled(true);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnForward.setEnabled(false);
        btnLast.setEnabled(false);
        btnNew.setEnabled(true);
        btnNext.setEnabled(false);

        dtcStartdate.setEnabled(false);
        dtcEnddate.setEnabled(false);

        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtAdressline1.setEnabled(false);
        txtAdressline2.setEnabled(false);
        txtAdressline3.setEnabled(false);
        txtBusinessname.setEnabled(true);
        txtCity.setEnabled(false);
        txtCountry.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtPostalcode.setEnabled(false);
        txtRfc.setEnabled(true);
        txtState.setEnabled(false);

        dtcStartdate.setDate(null);
        dtcEnddate.setDate(null);
        glIsNew = false;
		glCustomerAct = 0;
		txtFirstname.requestFocusInWindow();
        txtFirstname.setBackground(new Color(211, 243, 255));
        txtLastname.setBackground(new Color(211, 243, 255));
        txtRfc.setBackground(new Color(211, 243, 255));
        txtBusinessname.setBackground(new Color(211, 243, 255));
    }
	private void fnUpdateRow(){
		btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnFind.setEnabled(false);
        btnDelete.setEnabled(true);
        btnFirst.setEnabled(true);
        btnForward.setEnabled(true);
        btnLast.setEnabled(true);
        btnNew.setEnabled(false);
        btnNext.setEnabled(true);

        dtcStartdate.setEnabled(true);
        dtcEnddate.setEnabled(true);
        
        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtAdressline1.setEnabled(true);
        txtAdressline2.setEnabled(true);
        txtAdressline3.setEnabled(true);
        txtBusinessname.setEnabled(true);
        txtCity.setEnabled(true);
        txtCountry.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtPostalcode.setEnabled(true);
        txtRfc.setEnabled(true);
        txtState.setEnabled(true);
        
        txtFirstname.requestFocusInWindow();
        glIsNew = false;
        txtRfc.setBackground(UIManager.getColor("ToolTip.background"));
        txtBusinessname.setBackground(UIManager.getColor("ToolTip.background"));
        txtFirstname.setBackground(UIManager.getColor("Button.highlight"));
        txtLastname.setBackground(UIManager.getColor("Button.highlight"));
        lblMessage.setText("");
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
        
        dtcStartdate.setEnabled(true);
        dtcEnddate.setEnabled(true);

        txtFirstname.setEnabled(true);
        txtLastname.setEnabled(true);
        txtAdressline1.setEnabled(true);
        txtAdressline2.setEnabled(true);
        txtAdressline3.setEnabled(true);
        txtBusinessname.setEnabled(true);
        txtCity.setEnabled(true);
        txtCountry.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtPostalcode.setEnabled(true);
        txtRfc.setEnabled(true);
        txtState.setEnabled(true);

        dtcStartdate.setDate(new java.util.Date());
        dtcEnddate.setDate(new java.util.Date());

        txtFirstname.setText("");
        txtLastname.setText("");

		glIsNew = true;
        txtRfc.setBackground(UIManager.getColor("ToolTip.background"));
        txtBusinessname.setBackground(UIManager.getColor("ToolTip.background"));
        txtFirstname.setBackground(UIManager.getColor("Button.highlight"));
        txtLastname.setBackground(UIManager.getColor("Button.highlight"));
        lblMessage.setText("");
    }
	private void fnCleanRow(){
		txtFirstname.setText("");
        txtLastname.setText("");
        txtAdressline1.setText("");
        txtAdressline2.setText("");
        txtAdressline3.setText("");
        txtBusinessname.setText("");
        txtCity.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPostalcode.setText("");
        txtRfc.setText("");
        txtState.setText("");

        dtcStartdate.setDate(null);
        dtcEnddate.setDate(null);
	}
	private void btnFirstActionPerformed(ActionEvent e){
		glCustomerAct = 0;
		fnShowRow();
	}
	private void btnForwardActionPerformed(ActionEvent e){
        if(glCustomerAct == 0) return;
        glCustomerAct--;
        fnShowRow();
	}
	private void btnNextActionPerformed(ActionEvent e){
        if(glCustomerAct == ginCountCustomers-1) return;
        glCustomerAct++;
        fnShowRow();
	}
	private void btnLastActionPerformed(ActionEvent e){
        glCustomerAct = ginCountCustomers -1;
        fnShowRow();
	}
	private void btnNewActionPerformed(ActionEvent e){
		fnInsertRow();
	}
	private void btnSaveActionPerformed(ActionEvent e){
		// Realizamos Validaciones
        String lstMessage = "";
        int linExistCustomer;
        linExistCustomer = dtData.fnCustomerExist(txtRfc.getText()); 

        if(txtRfc.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un usuario");
            txtRfc.requestFocusInWindow();
            return;
        }
        if(txtBusinessname.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un usuario");
            txtBusinessname.requestFocusInWindow();
            return;
        }
        if(glIsNew == true && linExistCustomer != 0){
            JOptionPane.showMessageDialog(this,"Este cliente: '"+txtRfc.getText()+"' ya existe, favor de ingresar otro");
            txtRfc.setText("");
            txtBusinessname.setText("");
            txtRfc.requestFocusInWindow();
            return;
        }
        if (glIsNew){
        	Date ldtStrarDate = new java.sql.Date(dtcStartdate.getDate().getTime());
        	Date ldtEndDate = new java.sql.Date(dtcEnddate.getDate().getTime());
            lstMessage = dtData.fnInsertCustomer(txtFirstname.getText(), txtLastname.getText(), txtBusinessname.getText(), txtRfc.getText(), 
        			txtAdressline1.getText(), txtAdressline2.getText(), txtAdressline3.getText(), txtCity.getText(), 
        			txtState.getText(), txtCountry.getText(), txtPostalcode.getText(), "", "", "", "", "", "",
        			"SysAdminApp", "Modificado el: ", String.valueOf(new java.util.Date()), txtEmail.getText(), "", 
        			txtPhone.getText(), ldtStrarDate, ldtEndDate, null, ginUserId, -1);
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
        	lstMessage = dtData.fnUpdateCustomer(listData.get(glCustomerAct).getCustomer_id(), 
        			txtFirstname.getText(), txtLastname.getText(), txtBusinessname.getText(), txtRfc.getText(), 
        			txtAdressline1.getText(), txtAdressline2.getText(), txtAdressline3.getText(), txtCity.getText(), 
        			txtState.getText(), txtCountry.getText(), txtPostalcode.getText(), "", "", "", "", "", "",
        			"SysAdminApp", "Modificado el: ", String.valueOf(new java.util.Date()), txtEmail.getText(), "", txtPhone.getText(), ldtEndDate, null, ginUserId);
        	if (lstMessage.equals("OK")){
            	lblMessage.setForeground(new Color(0, 220, 0));
            	lblMessage.setText("El usuario se actualizo correctamente");
            }
            else{
        		lblMessage.setForeground(UIManager.getColor("Button.select"));
            	lblMessage.setText("No se pudo actualizar el usuario: "+lstMessage);
            	return;
            }
        }
        // LLenamos la tabla de nuevo
        fnReadOnlyRow();
        fnCleanRow();
	}
	private void btnCancelActionPerformed(ActionEvent e){
        fnReadOnlyRow();
        fnCleanRow();
	}
	private void btnDeleteActionPerformed(ActionEvent e){
		int linRespuesta = JOptionPane.showConfirmDialog(this, "esta seguro de querer borrar el registro".toUpperCase());
        if (linRespuesta !=0) return;
        String lstMessage = dtData.fnDeleteCustomer(String.valueOf(listData.get(glCustomerAct).getCustomer_id()),String.valueOf(ginUserId));
        if(!lstMessage.equals("OK")){
    		lblMessage.setForeground(new Color(0, 220, 0));
        	lblMessage.setText("Error al borrar al usuario: "+lstMessage);
        	return;
        }
        else{
        	lblMessage.setForeground(UIManager.getColor("EditorPane.selectionBackground"));
        	lblMessage.setText("El usuario fue borrado exitosamente!");
        }
        fnReadOnlyRow();
        fnCleanRow();
	}
	private void btnFindActionPerformed(ActionEvent e){
		fnLoadTable(txtFirstname.getText(), txtLastname.getText(), txtRfc.getText(), txtBusinessname.getText());
        glCustomerAct = 0;
        if (ginCountCustomers == 0){
        	return;
        }
        else{
        	fnShowRow();
    		fnUpdateRow();
        }
	}
	private void fnShowRow(){
		txtFirstname.setText(listData.get(glCustomerAct).getFirst_name());
		txtLastname.setText(listData.get(glCustomerAct).getLast_name());
		txtRfc.setText(listData.get(glCustomerAct).getRfc());
		txtBusinessname.setText(listData.get(glCustomerAct).getBusiness_name());
		txtAdressline1.setText(listData.get(glCustomerAct).getAddress_line1());
		txtAdressline2.setText(listData.get(glCustomerAct).getAddress_line2());
		txtAdressline3.setText(listData.get(glCustomerAct).getAddress_line3());
		txtCity.setText(listData.get(glCustomerAct).getCity());
		txtState.setText(listData.get(glCustomerAct).getState());
		txtCountry.setText(listData.get(glCustomerAct).getCountry());
		txtPostalcode.setText(listData.get(glCustomerAct).getPostal_code());
		txtPhone.setText(listData.get(glCustomerAct).getPhone());
		txtEmail.setText(listData.get(glCustomerAct).getEmail());
        dtcStartdate.setDate(listData.get(glCustomerAct).getStart_date());
        dtcEnddate.setDate(listData.get(glCustomerAct).getEnd_date());
    }
	private void fnLoadTable(String p_first_name, String p_last_name, String p_rfc, String p_business_name){
		/**/
		listData = dtData.fnLoadData(p_first_name, p_last_name, p_rfc, p_business_name);
		ginCountCustomers = listData.size();
        String strHeaderTittles[] = {"Nombre", "Apellido", "RFC", "Razon Social", "Calle", "Colonia", "Delegacion", 
        		                     "Ciudad", "Estado", "Pais","CP","Tel","Email"};
        String strDataReg[] = new String[15];
        dtmTable = new DefaultTableModel(null, strHeaderTittles);
        for (CustomerBean u : listData){
            //strDataReg[0] = String.valueOf(u.getUser_id());
        	strDataReg[0] = u.getFirst_name();
            strDataReg[1] = u.getLast_name();
            strDataReg[2] = u.getRfc();
            strDataReg[3] = u.getBusiness_name();
            strDataReg[4] = u.getAddress_line1();
            strDataReg[5] = u.getAddress_line2();
            strDataReg[6] = u.getAddress_line3();
            strDataReg[4] = u.getCity();
            strDataReg[4] = u.getState();
            strDataReg[4] = u.getCountry();
            strDataReg[4] = u.getPostal_code();
            strDataReg[4] = u.getPhone();
            strDataReg[4] = u.getEmail();
            
            dtmTable.addRow(strDataReg);
        }
        tblData.setModel(dtmTable);
        /**/
    }
	private void txtPostalcodeKeyTyped(java.awt.event.KeyEvent e) {
		int k=(int)e.getKeyChar();
		if (k >= 97 && k <= 122 || k>=65 && k<=90){
			e.setKeyChar((char)KeyEvent.VK_CLEAR);
			lblMessage.setForeground(UIManager.getColor("Button.select"));
	    	lblMessage.setText("ERROR: No se pueden ingresar letras en este campo! ");
		}
		if(k==241 || k==209){
			e.setKeyChar((char)KeyEvent.VK_CLEAR);
			lblMessage.setForeground(UIManager.getColor("Button.select"));
	    	lblMessage.setText("ERROR: No se pueden ingresar letras en este campo! ");
		}
		if(k==10){
			txtPostalcode.transferFocus();
		}
	}
	private void txtPhoneKeyTyped(KeyEvent e){
		int k=(int)e.getKeyChar();
		if (k >= 97 && k <= 122 || k>=65 && k<=90){
			e.setKeyChar((char)KeyEvent.VK_CLEAR);
			lblMessage.setForeground(UIManager.getColor("Button.select"));
	    	lblMessage.setText("ERROR: No se pueden ingresar letras en este campo! ");
		}
		if(k==241 || k==209){
			e.setKeyChar((char)KeyEvent.VK_CLEAR);
			lblMessage.setForeground(UIManager.getColor("Button.select"));
	    	lblMessage.setText("ERROR: No se pueden ingresar letras en este campo! ");
		}
		if(k==10){
			txtPhone.transferFocus();
		}
	}
}
