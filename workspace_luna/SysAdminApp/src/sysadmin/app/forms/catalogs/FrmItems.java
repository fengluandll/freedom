package sysadmin.app.forms.catalogs;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import sysadmin.app.data.ItemsDAO;
import sysadmin.app.model.ItemsBean;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Color;

public class FrmItems extends JInternalFrame {
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
	private boolean glIsNew;
	private int glItemAct;
    private ItemsDAO dtData;
    private List<ItemsBean> listData;
    private int ginCountItems = 0;
    private JLabel lblMessage;
    private JTable tblData;
    private JScrollPane scrData;
    private DefaultTableModel dtmTable;
    private JCheckBox chckbxEnabled;
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtType;
	private JTextField txtItemcode;
	private JTextField txtBarcode;
	private JTextField txtContext;
    
	public  int ginUserId;

	public int getGinUserId() {
		return ginUserId;
	}
	public void setGinUserId(int ginUserId) {
		this.ginUserId = ginUserId;
	}
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public FrmItems() {
		dtData = new ItemsDAO();
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
        setTitle("Administraci\u00F3n de Productos");
        setBounds(2, 2, 550, 315);
        

        dtcStartdate = new JDateChooser();
        dtcStartdate.setBounds(95, 153, 150, 28);
        getContentPane().add(dtcStartdate);
        
        dtcEnddate = new JDateChooser();
        dtcEnddate.setBounds(340, 153, 150, 28);
        getContentPane().add(dtcEnddate);

        btnFind = new JButton("");
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Buscar.png")));
        btnFind.setToolTipText("Buscar registro");
        btnFind.setBounds(47, 211, 42, 42);
        getContentPane().add(btnFind);
		
        btnFirst = new JButton("");
        btnFirst.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFirstActionPerformed(e);
        	}
        });
        btnFirst.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Primero.png")));
        btnFirst.setToolTipText("Primer registro");
        btnFirst.setBounds(102, 211, 42, 42);
        getContentPane().add(btnFirst);
        
        btnForward = new JButton("");
        btnForward.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnForwardActionPerformed(e);
        	}
        });
        btnForward.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Anterior.png")));
        btnForward.setToolTipText("Registro anterior");
        btnForward.setBounds(146, 211, 42, 42);
        getContentPane().add(btnForward);
        
        btnNext = new JButton("");
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNextActionPerformed(e);
        	}
        });
        btnNext.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Siguiente.png")));
        btnNext.setToolTipText("Siguiente registro");
        btnNext.setBounds(190, 211, 42, 42);
        getContentPane().add(btnNext);
        
        btnLast = new JButton("");
        btnLast.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLastActionPerformed(e);
        	}
        });
        btnLast.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Ultimo.png")));
        btnLast.setToolTipText("Ultimo registro");
        btnLast.setBounds(234, 211, 42, 42);
        getContentPane().add(btnLast);
        
        btnNew = new JButton("");
        btnNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewActionPerformed(e);
        	}
        });
        btnNew.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Nuevo.png")));
        btnNew.setToolTipText("Nuevo registro");
        btnNew.setBounds(292, 211, 42, 42);
        getContentPane().add(btnNew);
        
        btnDelete = new JButton("");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Borrar2.png")));
        btnDelete.setToolTipText("Modificar registro");
        btnDelete.setBounds(336, 211, 42, 42);
        getContentPane().add(btnDelete);
        
        btnSave = new JButton("");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnSaveActionPerformed(e);
        	}
        });
        btnSave.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Guardar.png")));
        btnSave.setToolTipText("Guardar registro");
        btnSave.setBounds(390, 211, 42, 42);
        getContentPane().add(btnSave);
        
        btnCancel = new JButton("");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelActionPerformed(e);
        	}
        });
        btnCancel.setIcon(new ImageIcon(FrmItems.class.getResource("/images/Cancelar2.png")));
        btnCancel.setToolTipText("Cancelar cambios");
        btnCancel.setBounds(434, 211, 42, 42);
        getContentPane().add(btnCancel);
        
        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
        lblFechaInicial.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaInicial.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaInicial.setLabelFor(dtcStartdate);
        lblFechaInicial.setBounds(6, 158, 85, 16);
        getContentPane().add(lblFechaInicial);
        
        JLabel lblFechaFinal = new JLabel("Fecha Final:");
        lblFechaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaFinal.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechaFinal.setLabelFor(dtcEnddate);
        lblFechaFinal.setBounds(255, 158, 80, 16);
        getContentPane().add(lblFechaFinal);
        
        JLabel lblFechasEfectivas = new JLabel("Fechas efectivas");
        lblFechasEfectivas.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblFechasEfectivas.setBounds(75, 127, 209, 16);
        getContentPane().add(lblFechasEfectivas);

        tblData = new JTable();
        
        scrData = new JScrollPane();
        scrData.setViewportView(tblData);
        scrData.setBounds(600, 212, 460, 265);
        getContentPane().add(scrData);
        
        txtName = new JTextField();
        txtName.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtName.setBounds(105, 17, 140, 28);
        getContentPane().add(txtName);
        txtName.setColumns(10);
        
        txtDescription = new JTextField();
        txtDescription.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtDescription.setBounds(360, 17, 150, 28);
        getContentPane().add(txtDescription);
        txtDescription.setColumns(10);
        
        txtType = new JTextField();
        txtType.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtType.setBounds(360, 73, 150, 28);
        getContentPane().add(txtType);
        txtType.setColumns(10);
        
        txtItemcode = new JTextField();
        txtItemcode.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtItemcode.setBounds(105, 45, 140, 28);
        getContentPane().add(txtItemcode);
        txtItemcode.setColumns(10);
        
        txtBarcode = new JTextField();
        txtBarcode.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtBarcode.setBounds(360, 45, 150, 28);
        getContentPane().add(txtBarcode);
        txtBarcode.setColumns(10);
        
        txtContext = new JTextField();
        txtContext.setFont(new Font("Monaco", Font.PLAIN, 10));
        txtContext.setBounds(105, 73, 140, 28);
        getContentPane().add(txtContext);
        txtContext.setColumns(10);
        
        chckbxEnabled = new JCheckBox("Activo");
        chckbxEnabled.setFont(new Font("Monaco", Font.PLAIN, 10));
        chckbxEnabled.setBounds(105, 102, 128, 23);
        getContentPane().add(chckbxEnabled);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblNombre.setBounds(6, 23, 100, 16);
        getContentPane().add(lblNombre);
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblDescripcion.setBounds(250, 23, 110, 16);
        getContentPane().add(lblDescripcion);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblTipo.setBounds(250, 79, 110, 16);
        getContentPane().add(lblTipo);
        
        JLabel lblCodigoArticulo = new JLabel("Codigo Articulo:");
        lblCodigoArticulo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCodigoArticulo.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblCodigoArticulo.setBounds(6, 51, 100, 16);
        getContentPane().add(lblCodigoArticulo);
        
        JLabel lblCodigoDeBarras = new JLabel("Codigo de barras:");
        lblCodigoDeBarras.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCodigoDeBarras.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblCodigoDeBarras.setBounds(250, 51, 110, 16);
        getContentPane().add(lblCodigoDeBarras);
        
        JLabel lblContexto = new JLabel("Contexto:");
        lblContexto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContexto.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblContexto.setBounds(6, 79, 100, 16);
        getContentPane().add(lblContexto);
        
        lblMessage = new JLabel("");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(new Font("Monaco", Font.PLAIN, 10));
        lblMessage.setBounds(16, 190, 494, 16);
        getContentPane().add(lblMessage);

	}
	private void frmInternalFrameOpened(){
		//fnLoadTable();
        //fnShowRow();
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

        txtName.setEnabled(true);
        txtDescription.setEnabled(true);
        txtItemcode.setEnabled(true);
        txtBarcode.setEnabled(false);
        txtContext.setEnabled(false);
        txtType.setEnabled(false);
        chckbxEnabled.setEnabled(false);

        dtcStartdate.setDate(null);
        dtcEnddate.setDate(null);
        glIsNew = false;
		glItemAct = 0;
		txtName.requestFocusInWindow();
		txtName.setBackground(new Color(211, 243, 255));
		txtDescription.setBackground(new Color(211, 243, 255));
		txtItemcode.setBackground(new Color(211, 243, 255));
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
        
        txtName.setEnabled(true);
        txtDescription.setEnabled(true);
        txtItemcode.setEnabled(true);
        txtBarcode.setEnabled(true);
        txtContext.setEnabled(true);
        txtType.setEnabled(true);
        chckbxEnabled.setEnabled(true);
        
        txtName.requestFocusInWindow();
        glIsNew = false;
        txtName.setBackground(UIManager.getColor("ToolTip.background"));
        txtItemcode.setBackground(UIManager.getColor("ToolTip.background"));
        txtDescription.setBackground(UIManager.getColor("Button.highlight"));
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

        txtName.setEnabled(true);
        txtDescription.setEnabled(true);
        txtItemcode.setEnabled(true);
        txtBarcode.setEnabled(true);
        txtContext.setEnabled(true);
        txtType.setEnabled(true);
        chckbxEnabled.setEnabled(true);

        dtcStartdate.setDate(new java.util.Date());
        dtcEnddate.setDate(new java.util.Date());
		chckbxEnabled.setSelected(true);

		glIsNew = true;
        txtName.setBackground(UIManager.getColor("ToolTip.background"));
        txtItemcode.setBackground(UIManager.getColor("ToolTip.background"));
        txtDescription.setBackground(UIManager.getColor("Button.highlight"));
        lblMessage.setText("");
    }
	private void fnCleanRow(){
		txtName.setText("");
		txtDescription.setText("");
		txtItemcode.setText("");
		txtBarcode.setText("");
		txtContext.setText("");
		txtType.setText("");

		chckbxEnabled.setSelected(false);

        dtcStartdate.setDate(null);
        dtcEnddate.setDate(null);
	}
	private void btnFirstActionPerformed(ActionEvent e){
		glItemAct = 0;
		fnShowRow();
	}
	private void btnForwardActionPerformed(ActionEvent e){
        if(glItemAct == 0) return;
        glItemAct--;
        fnShowRow();
	}
	private void btnNextActionPerformed(ActionEvent e){
        if(glItemAct == ginCountItems-1) return;
        glItemAct++;
        fnShowRow();
	}
	private void btnLastActionPerformed(ActionEvent e){
        glItemAct = ginCountItems -1;
        fnShowRow();
	}
	private void btnNewActionPerformed(ActionEvent e){
		fnInsertRow();
	}
	private void btnSaveActionPerformed(ActionEvent e){
		// Realizamos Validaciones
        String lstMessage = "";
        int linExistItem;
        linExistItem = dtData.fnItemExist(txtItemcode.getText()); 

        if(txtItemcode.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un usuario");
            txtItemcode.requestFocusInWindow();
            return;
        }
        if(txtName.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Debe de ingresar un usuario");
            txtName.requestFocusInWindow();
            return;
        }
        if(glIsNew == true && linExistItem != 0){
            JOptionPane.showMessageDialog(this,"Este codigo de articulo: '"+txtItemcode.getText()+"' ya existe, favor de ingresar otro");
            txtItemcode.setText("");
            txtItemcode.requestFocusInWindow();
            return;
        }
        if (glIsNew){
        	Date ldtStrarDate = new java.sql.Date(dtcStartdate.getDate().getTime());
        	Date ldtEndDate = new java.sql.Date(dtcEnddate.getDate().getTime());
            lstMessage = dtData.fnInsertItems(101, txtName.getText(), txtDescription.getText(), txtType.getText(), txtBarcode.getText(), 
            		                          fnSetItemEnabled(chckbxEnabled.isSelected()), ldtStrarDate, ldtEndDate, 
            		                          txtContext.getText(), txtItemcode.getText(), "", "", "", "", "", "", "", "", ginUserId, -1);
            if (lstMessage.equals("OK")){
            	lblMessage.setForeground(new Color(0, 220, 0));
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
        	lstMessage = dtData.fnUpdateItems(listData.get(glItemAct).getItem_id(), 101, txtName.getText(), txtDescription.getText(), txtType.getText(), txtBarcode.getText(), 
                    fnSetItemEnabled(chckbxEnabled.isSelected()), ldtEndDate, 
                    txtContext.getText(), txtItemcode.getText(), "", "", "", "", "", "", "", "", ginUserId, -1);
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
        String lstMessage = dtData.fnDeleteItems(String.valueOf(listData.get(glItemAct).getItem_id()), "101",String.valueOf(ginUserId));
        if(!lstMessage.equals("OK")){
    		lblMessage.setForeground(UIManager.getColor("Button.select"));
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
		fnLoadTable(txtName.getText(), txtDescription.getText(), txtItemcode.getText());
        glItemAct = 0;
        if (ginCountItems == 0){
        	return;
        }
        else{
        	fnShowRow();
    		fnUpdateRow();
        }
	}
	private void fnShowRow(){
		txtName.setText(listData.get(glItemAct).getName());
		txtDescription.setText(listData.get(glItemAct).getDescription());
		txtItemcode.setText(listData.get(glItemAct).getSegment1());
		txtBarcode.setText(listData.get(glItemAct).getBarcode());
		txtContext.setText(listData.get(glItemAct).getSegment_context());
		txtType.setText(listData.get(glItemAct).getType());
		chckbxEnabled.setSelected(fnGetItemEnabled(listData.get(glItemAct).getEnabled_flag()));
        dtcStartdate.setDate(listData.get(glItemAct).getStart_date());
        dtcEnddate.setDate(listData.get(glItemAct).getEnd_date());
    }
	private void fnLoadTable(String p_name, String p_description, String p_segment1){
		/**/
		listData = dtData.fnLoadData(p_name, p_description, p_segment1);
		ginCountItems = listData.size();
        String strHeaderTittles[] = {"Nombre", "Descripcion", "ItemCode", "BarCode", "Contexto", "Tipo"};
        String strDataReg[] = new String[6];
        dtmTable = new DefaultTableModel(null, strHeaderTittles);
        for (ItemsBean u : listData){
        	strDataReg[0] = u.getName();
            strDataReg[1] = u.getDescription();
            strDataReg[2] = u.getSegment1();
            strDataReg[3] = u.getBarcode();
            strDataReg[4] = u.getSegment_context();
            strDataReg[5] = u.getType();
            
            dtmTable.addRow(strDataReg);
        }
        tblData.setModel(dtmTable);
        /**/
    }
	
	private boolean fnGetItemEnabled(String p_enabled_flag){
		boolean lbReturn = false;
		if(p_enabled_flag.equals("Y")){
			lbReturn = true;
		}
		else {
			lbReturn = false;
		}
		return lbReturn;
	}
	
	private String fnSetItemEnabled(boolean p_enabled_flag){
		String lbReturn = "N";
		if(p_enabled_flag){
			lbReturn = "Y";
		}
		else {
			lbReturn = "N";
		}
		return lbReturn;
	} 
}
