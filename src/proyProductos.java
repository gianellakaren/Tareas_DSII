import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class proyProductos extends JFrame implements ActionListener{
	private JLabel lbltl, lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;
	private JTextField txtCod, txtNom, txtDet, txtSto, txtPre;
	private JButton btnnuevo,btnagregar,btneditar,btnborrar;
	private JRadioButton rbca, rbr, rbm, rbp, rbcu;
	private ButtonGroup bg;
	private JTable tprod;
	private JScrollPane sp;
	
	private Conexion cn = new Conexion();
	
	public proyProductos() {
		super();
		ConfigVentana();
		IniciarControl();
		MostrarDatos();
	}
	
	private void ConfigVentana() {
		this.setTitle("Tarea");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void IniciarControl() {
		lbltl = new JLabel();
		lbltl.setText("ALMACEN DE PRODUCTOS DE SPA");
		lbltl.setFont(new Font("Tahoma", Font.BOLD,20));
		lbltl.setForeground(Color.PINK);
		lbltl.setOpaque(true);
		lbltl.setBounds(80,10,400,25);
		
		lbl1 = new JLabel();
		lbl1.setText("Ingrese Codigo: ");
		lbl1.setBounds(20,40,150,25);
		
		txtCod = new JTextField();
		txtCod.setBounds(150,40,60,25);
		
		lbl2 = new JLabel();
		lbl2.setText("Ingrese Nombre: ");
		lbl2.setBounds(20,70,150,25);
		
		txtNom = new JTextField();
		txtNom.setBounds(150,70,100,25);
		
		lbl3 = new JLabel();
		lbl3.setText("Detalle el producto: ");
		lbl3.setBounds(20,100,150,25);
		
		txtDet = new JTextField();
		txtDet.setBounds(150,100,150,25);
		
		lbl4 = new JLabel();
		lbl4.setText("Categoría: ");
		lbl4.setBounds(20,130,120,25);
		
		bg = new ButtonGroup();
		
		rbca = new JRadioButton("Cabello");
		rbca.setBounds(90,130,80,25);
		add(rbca);
		bg.add(rbca);
		
		rbr = new JRadioButton("Rostro");
		rbr.setBounds(167,130,65,25);
		add(rbr);
		bg.add(rbr);
		
		rbm = new JRadioButton("Manos");
		rbm.setBounds(244,130,65,25);
		add(rbm);
		bg.add(rbm);
		
		rbp = new JRadioButton("Pies");
		rbp.setBounds(321,130,60,25);
		add(rbp);
		bg.add(rbp);
		
		rbcu = new JRadioButton("Cuerpo");
		rbcu.setBounds(393,130,67,25);
		add(rbcu);
		bg.add(rbcu);
		
		lbl5 = new JLabel();
		lbl5.setText("Ingrese Stock: ");
		lbl5.setBounds(20,160,120,25);
		
		txtSto = new JTextField();
		txtSto.setBounds(150,160,60,25);
		
		lbl6 = new JLabel();
		lbl6.setText("Ingrese Precio:");
		lbl6.setBounds(20,190,100,25);
		
		txtPre = new JTextField();
		txtPre.setBounds(150,190,60,25);
		
		btnnuevo = new JButton();
		btnnuevo.setText("NUEVO");
		btnnuevo.setBounds(10, 230, 100, 30);
		btnnuevo.addActionListener(this);
		
		btnagregar = new JButton();
		btnagregar.setText("AGREGAR");
		btnagregar.setBounds(130, 230, 100, 30);
		btnagregar.addActionListener(this);
		
		btneditar = new JButton();
		btneditar.setText("EDITAR");
		btneditar.setBounds(250, 230, 100, 30);
		btneditar.addActionListener(this);
		
		btnborrar = new JButton();
		btnborrar.setText("BORRAR");
		btnborrar.setBounds(370, 230, 100, 30);
		btnborrar.addActionListener(this);

		//Tabla
		tprod = new JTable();
		
		tprod.setRowHeight(25);
		
		sp = new JScrollPane(tprod);
		sp.setBounds(20,280,450,170);
		
		//Controles
		this.add(lbltl);
		this.add(lbl1);
		this.add(txtCod);
		this.add(lbl2);
		this.add(txtNom);
		this.add(lbl3);
		this.add(txtDet);
		this.add(lbl4);
		this.add(lbl5);
		this.add(txtSto);
		this.add(lbl6);
		this.add(txtPre);
		this.add(btnnuevo);
		this.add(btnagregar);
		this.add(btneditar);
		this.add(btnborrar);
		this.add(sp);
		
		ControladorTxt ct = new ControladorTxt();
		
		txtCod.addKeyListener(ct);
		txtNom.addKeyListener(ct);
		txtDet.addKeyListener(ct);
		txtSto.addKeyListener(ct);
		txtPre.addKeyListener(ct);
		
		ControladorClick click = new ControladorClick();
		tprod.addMouseListener(click);

	}
	
	private void MostrarDatos() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.setRowCount(0);
		
		Connection cnx = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cnx = cn.Conectar();
			stm = cnx.createStatement();
			String sql = "select * from productos order by NomProd asc";
			rs = stm.executeQuery(sql);
			
			int num_campos = rs.getMetaData().getColumnCount();
			for(int x=1; x<=num_campos; x++)
			modelo.addColumn(rs.getMetaData().getColumnName(x));
			while(rs.next()){
				Object[] registro = new Object[num_campos];
				for(int x=0; x<num_campos;x++)
					registro[x] = rs.getObject(x + 1);
				modelo.addRow(registro);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stm != null) stm.close();
				if(cnx != null) cnx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		tprod.setModel(modelo);
	}
	
	private class ControladorTxt implements KeyListener{
		public void keyTyped(KeyEvent e) {
			//int txtSto.setText() = e.getKeyCode();
			if(e.getSource()== txtCod && txtCod.getText().length() == 2)
				e.consume();
			else if(e.getSource()== txtNom && txtNom.getText().length() == 50)
				e.consume();
			else if(e.getSource()== txtDet && txtDet.getText().length() == 50)
				e.consume();
			else if(e.getSource()== txtSto && txtSto.getText().length() == 2)
				e.consume();
			else if(e.getSource()== txtPre && txtPre.getText().length() == 3)
				e.consume();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class ControladorClick extends MouseAdapter{
		public void mouseClicked (MouseEvent e) {
			int registro = tprod.getSelectedRow();
			
			if(registro >=0) {
				txtCod.setEditable(false);
				btnagregar.setEnabled(false);
				btneditar.setEnabled(true);
				btnborrar.setEnabled(true);
				
				String cod = (String)tprod.getValueAt(registro,0);
				String nom = (String)tprod.getValueAt(registro,1);
				String det = (String)tprod.getValueAt(registro,2);
				String cat = (String)tprod.getValueAt(registro,3);
				int sto = (int) tprod.getValueAt(registro,4);
				float pre = (float)tprod.getValueAt(registro,5);
				
				txtCod.setText(cod);
				txtNom.setText(nom);
				txtDet.setText(det);
				if(cat.equals("Cabello")) rbca.setSelected(true);
				if(cat.equals("Rostro")) rbr.setSelected(true);
				if(cat.equals("Manos")) rbm.setSelected(true);
				if(cat.equals("Pies")) rbp.setSelected(true);
				if(cat.equals("Cuerpo")) rbcu.setSelected(true);
				txtSto.setText(String.valueOf(sto));
				txtPre.setText(String.valueOf(pre));
				
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnnuevo) {
			LimpiarDatos();
			btnagregar.setEnabled(true);
			btneditar.setEnabled(false);
			btnborrar.setEnabled(false);
		}else {
			
			//Cod. Producto
			if(txtCod.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "¡Ingrese el código!");
				txtCod.requestFocus();
				return;
			}else if(txtNom.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "¡Ingrese el producto!");
				txtNom.requestFocus();
				return;
			}else if(txtDet.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "¡Escriba el detalle del producto!");
				txtDet.requestFocus();
				return;
			}else if(rbca.isSelected()==false && rbr.isSelected()==false && rbm.isSelected()==false && rbp.isSelected()==false && rbcu.isSelected()==false) {
				JOptionPane.showMessageDialog(null, "¡Seleccione la categoria!");
				return;
			}else if(txtPre.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "¡Ingrese el precio!");
				txtPre.requestFocus();
				return;
			}
		
		Producto pr = new Producto();
		
		
		pr.setCodProd(txtCod.getText());
		pr.setNomProd(txtNom.getText());
		pr.setDetaProd(txtDet.getText());
		if(rbca.isSelected()) pr.setCatProd("Cabello");
		if(rbr.isSelected()) pr.setCatProd("Rostro");
		if(rbm.isSelected()) pr.setCatProd("Manos");
		if(rbp.isSelected()) pr.setCatProd("Pies");
		if(rbcu.isSelected()) pr.setCatProd("Cuerpo");
		pr.setStocProd(txtSto.getText());
		pr.setPrecProd(txtPre.getText());
		
		Connection cnx = null;
		PreparedStatement pstm = null;
		
		try {
			cnx = cn.Conectar();
			String sql = "";
			
			if(e.getSource()==btnagregar) {
				btneditar.setEnabled(false);
				btnborrar.setEnabled(false);
				sql = "insert into productos values(?,?,?,?,?,?)";
				
				pstm = cnx.prepareStatement(sql);
				
				pstm.setString(1, pr.getCodProd());
				pstm.setString(2, pr.getNomProd());
				pstm.setString(3, pr.getDetaProd());
				pstm.setString(4, pr.getCatProd());
				pstm.setInt(5, pr.getStocProd());
				pstm.setFloat(6, pr.getPrecProd());
				
				pstm.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Ha REGISTRADO el producto "+txtNom.getText()+ " al listado.");
				
			}else if(e.getSource()==btneditar) {
				sql = "update productos set NomProd = ?, DetaProd = ?,CatProd = ?, StocProd = ?, PrecProd = ? where CodProd =?";
				pstm = cnx.prepareStatement(sql);
				
				pstm.setString(1, pr.getNomProd());
				pstm.setString(2, pr.getDetaProd());
				pstm.setString(3, pr.getCatProd());
				pstm.setInt(4, pr.getStocProd());
				pstm.setFloat(5, pr.getPrecProd());
				pstm.setString(6, pr.getCodProd());
				
				pstm.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "El producto " +txtNom.getText()+ " ha sido MODIFICADO!");
			}else if(e.getSource()==btnborrar) {
				int op = JOptionPane.showConfirmDialog(null,
						"¿Seguro de borrar el producto "+ txtNom.getText() + " del listado?", "SENATI", JOptionPane.YES_NO_OPTION);
				
				if(op== JOptionPane.YES_OPTION) {
					sql = "delete from productos where CodProd = ?";
					
					pstm = cnx.prepareStatement(sql);

					pstm.setString(1, pr.getCodProd());
					
					pstm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "El producto " +txtNom.getText()+ " ha sido ELIMINADO!");
				}
			}
			MostrarDatos();
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(cnx != null) cnx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		LimpiarDatos();
		}
	}
	
	private void LimpiarDatos() {
		txtCod.setEditable(true);
		tprod.clearSelection();
		JTextField txt;
		for(int i=0;i<this.getContentPane().getComponents().length;i++) {
			if(this.getContentPane().getComponent(i) instanceof JTextField) {
				txt = (JTextField) this.getContentPane().getComponent(i);
				txt.setText("");
			}
		}
		bg.clearSelection();
		txtCod.requestFocus();
	}
	
	public static void main(String[] args) {
		proyProductos frm = new proyProductos();
		frm.setVisible(true);

	}

}
