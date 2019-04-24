package sysadmin.app.forms;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.xml.ws.Action;

import sysadmin.app.forms.catalogs.FrmCustomers;
import sysadmin.app.forms.catalogs.FrmItems;
import sysadmin.app.forms.catalogs.FrmSuppliers;
//import sysadmin.app.custom.DesktopBackgroud;
import sysadmin.app.forms.catalogs.FrmUsers;
import sysadmin.app.forms.system.FrmAboutBox;
import sysadmin.app.forms.system.FrmChangePassword;

import javax.swing.SwingConstants;
import java.awt.Dimension;


public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel lblUsername, lblUserid, lblProfile,lblStartdate, lblEnddate, lblFullname; 
	private JDesktopPane dpnMain;

	/**
	 * Create the frame.
	 */
	
	public FrmMain() {
		setFont(new Font("Monaco", Font.PLAIN, 10));
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/pumba/Documents/workspace/SysAdminApp/src/images/sisemexicofondo.png"));
		setTitle("SAE - Sistema Administrador Empresarial".toUpperCase());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Monaco", Font.PLAIN, 10));
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Monaco", Font.PLAIN, 10));
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setFont(new Font("Monaco", Font.PLAIN, 10));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmSalirActionPerformed(e);
			}
		});
		
		JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCambiarContraseaActionPerformed(e);
			}
		});
		mntmCambiarContrasea.setFont(new Font("Monaco", Font.PLAIN, 10));
		mnArchivo.add(mntmCambiarContrasea);
		
		JMenuItem mntmCerrarSessin = new JMenuItem("Cambiar sessi\u00F3n");
		mntmCerrarSessin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCerrarSessinActionPerformed(e);
			}
		});
		mntmCerrarSessin.setFont(new Font("Monaco", Font.PLAIN, 10));
		mnArchivo.add(mntmCerrarSessin);
		
		JSeparator separator_2 = new JSeparator();
		mnArchivo.add(separator_2);
		mnArchivo.add(mntmSalir);
		
		JMenu mnCatalogos = new JMenu("Catalogos");
		mnCatalogos.setFont(new Font("Monaco", Font.PLAIN, 10));
		menuBar.add(mnCatalogos);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setFont(new Font("Monaco", Font.PLAIN, 10));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmClientesActionPerformed(e);
			}
		});
		mnCatalogos.add(mntmClientes);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.setFont(new Font("Monaco", Font.PLAIN, 10));
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmProveedoresActionPerformed(e);
			}
		});
		mnCatalogos.add(mntmProveedores);
		
		JSeparator separator_1 = new JSeparator();
		mnCatalogos.add(separator_1);
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mntmProductos.setFont(new Font("Monaco", Font.PLAIN, 10));
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmProductosActionPerformed(e);
			}
		});
		mnCatalogos.add(mntmProductos);
		
		JSeparator separator = new JSeparator();
		mnCatalogos.add(separator);
		
		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.setFont(new Font("Monaco", Font.PLAIN, 10));
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmUsuariosActionPerformed(e);
			}
		});
		mnCatalogos.add(mntmUsuarios);
		
		JMenu mnTransacciones = new JMenu("Transacciones");
		mnTransacciones.setFont(new Font("Monaco", Font.PLAIN, 10));
		menuBar.add(mnTransacciones);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setFont(new Font("Monaco", Font.PLAIN, 10));
		menuBar.add(mnReportes);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setFont(new Font("Monaco", Font.PLAIN, 10));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmAcercaDeActionPerformed(e);
			}
		});
		mntmAcercaDe.setFont(new Font("Monaco", Font.PLAIN, 10));
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar tbHeader = new JToolBar();
		tbHeader.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbHeader.setFloatable(false);
		contentPane.add(tbHeader, BorderLayout.NORTH);
		
		JButton btnCustomers = new JButton("");
		btnCustomers.setToolTipText("Catalogo de clientes");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCustomersActionPerformed(e);
			}
		});
		btnCustomers.setFont(new Font("Monaco", Font.PLAIN, 10));
		btnCustomers.setIcon(new ImageIcon(FrmMain.class.getResource("/images/menuicon/Clientes.png")));
		tbHeader.add(btnCustomers);
		
		JButton btnUsuarios = new JButton("");
		btnUsuarios.setToolTipText("Catalogo de usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUsuariosActionPerformed(e);
			}
		});
		
		JButton btnProveedores = new JButton("");
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProveedoresActionPerformed(e);
			}
		});
		btnProveedores.setToolTipText("Catalogo de proveedores");
		btnProveedores.setIcon(new ImageIcon(FrmMain.class.getResource("/images/menuicon/Proveedor.png")));
		tbHeader.add(btnProveedores);
		
		JButton btnProductos = new JButton("");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProductosActionPerformed(e);
			}
		});
		btnProductos.setToolTipText("Catalogo de productos");
		btnProductos.setIcon(new ImageIcon(FrmMain.class.getResource("/images/menuicon/Productos.png")));
		tbHeader.add(btnProductos);
		btnUsuarios.setIcon(new ImageIcon(FrmMain.class.getResource("/images/menuicon/usuarios.png")));
		tbHeader.add(btnUsuarios);
		
		JToolBar tbFooter = new JToolBar();
		tbFooter.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.setFloatable(false);
		contentPane.add(tbFooter, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Bienvenido: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tbFooter.add(lblNewLabel);
		
		lblFullname = new JLabel("FullName");
		lblFullname.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		tbFooter.add(lblFullname);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setPreferredSize(new Dimension(0, 1));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		tbFooter.add(separator_3);
		separator_3.setPreferredSize(new Dimension(0, 1));
		
		JLabel lblUsuario = new JLabel("Usuario Id:");
		lblUsuario.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblUsuario);
		
		lblUserid = new JLabel("UserId");
		lblUserid.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblUserid);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		tbFooter.add(separator_6);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblNewLabel_1);
		
		lblUsername = new JLabel();
		lblUsername.setText("Usuario Id:");
		tbFooter.add(lblUsername);
		lblUsername.setFont(new Font("Monaco", Font.PLAIN, 10));
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		tbFooter.add(separator_4);
		
		JLabel lblPerfilDeUsuario = new JLabel("Perfil de usuario: ");
		lblPerfilDeUsuario.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblPerfilDeUsuario);
		
		lblProfile = new JLabel("Profile");
		lblProfile.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblProfile);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		tbFooter.add(separator_5);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblFecha);
		
		lblStartdate = new JLabel("Startdate");
		lblStartdate.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblStartdate);
		
		JLabel lblA = new JLabel("  a:  ");
		lblA.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblA);
		
		lblEnddate = new JLabel("Enddate");
		lblEnddate.setFont(new Font("Monaco", Font.PLAIN, 10));
		tbFooter.add(lblEnddate);
		
		dpnMain = new JDesktopPane();
		//dpnMain = new DesktopBackgroud();
		dpnMain.setBackground(new Color(128, 128, 128));
		//((DesktopBackgroud) dpnMain).setImagen("/Users/pumba/Documents/workspace/SysAdminApp/src/images/sisemexicofondo.png");
		contentPane.add(dpnMain, BorderLayout.CENTER);
	}
	private void mntmSalirActionPerformed(ActionEvent e){
		System.exit(0);
	}
	
	private void mntmClientesActionPerformed(ActionEvent e){
		try {
			FrmCustomers wnd = new FrmCustomers();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void mntmProveedoresActionPerformed(ActionEvent e){
		try {
			FrmSuppliers wnd = new FrmSuppliers();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void mntmProductosActionPerformed(ActionEvent e){
		try {
			FrmItems wnd = new FrmItems();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void mntmUsuariosActionPerformed(ActionEvent e){
		try {
			FrmUsers wnd = new FrmUsers();
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void mntmAcercaDeActionPerformed(ActionEvent e){
		try {
			FrmAboutBox wnd =  new FrmAboutBox();
			wnd.setLocationRelativeTo(null);
			wnd.setAlwaysOnTop(true);
			wnd.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Action public void mntmCerrarSessinActionPerformed(ActionEvent e){
		try {
			dispose();
			FrmLogin wnd = new FrmLogin();
			wnd.setLocationRelativeTo(null);
			wnd.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void mntmCambiarContraseaActionPerformed(ActionEvent e){
		try {
			FrmChangePassword wnd = new FrmChangePassword();
			wnd.glstUsername.setText(lblUsername.getText()); 
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void btnUsuariosActionPerformed(ActionEvent e){
		try {
			FrmUsers wnd = new FrmUsers();
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void btnCustomersActionPerformed(ActionEvent e){
		try {
			FrmCustomers wnd = new FrmCustomers();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void btnProductosActionPerformed(ActionEvent e){
		try {
			FrmItems wnd = new FrmItems();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void btnProveedoresActionPerformed(ActionEvent e){
		try {
			FrmSuppliers wnd = new FrmSuppliers();
			wnd.setGinUserId(Integer.valueOf(lblUserid.getText()));
			dpnMain.add(wnd);
			wnd.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
