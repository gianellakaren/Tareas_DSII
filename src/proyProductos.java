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
	private JLabel lbltl, lbl1,lbl2,lbl3,lbl4,lbl5,lbl6;
	private JTextField txtCod, txtNom, txtDet, txtSto, txtPre;
	private JButton btnnuevo,btnagregar,btneditar,btnborrar;
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
		lbltl.setText("Listado de productos / spa");
		lbltl.setFont(new Font("Didot", Font.BOLD,15));
		lbltl.setForeground(Color.RED);
		lbltl.setBounds(100,10,300,25);
		
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
		lbl4.setText("Ingrese Stock: ");
		lbl4.setBounds(20,130,120,25);
		
		txtSto = new JTextField();
		txtSto.setBounds(150,130,60,25);
		
		lbl5 = new JLabel();
		lbl5.setText("Ingrese Precio: ");
		lbl5.setBounds(20,160,120,25);
		
		txtPre = new JTextField();
		txtPre.setBounds(150,160,60,25);
		
		btnnuevo = new JButton();
		btnnuevo.setText("NUEVO");
		btnnuevo.setBounds(10, 190, 100, 30);
		btnnuevo.addActionListener(this);
		
		btnagregar = new JButton();
		btnagregar.setText("AGREGAR");
		btnagregar.setBounds(110, 190, 100, 30);
		btnagregar.addActionListener(this);
		
		btneditar = new JButton();
		btneditar.setText("EDITAR");
		btneditar.setBounds(210, 190, 100, 30);
		btneditar.addActionListener(this);
		
		btnborrar = new JButton();
		btnborrar.setText("BORRAR");
		btnborrar.setBounds(320, 190, 100, 30);
		btnborrar.addActionListener(this);
		
		
		
		//Tabla
		tprod = new JTable();
		
		tprod.setRowHeight(25);
		
		sp = new JScrollPane(tprod);
		sp.setBounds(20,240,450,150);
		
		//Controles
		this.add(lbltl);
		this.add(lbl1);
		this.add(txtCod);
		this.add(lbl2);
		this.add(txtNom);
		this.add(lbl3);
		this.add(txtDet);
		this.add(lbl4);
		this.add(txtSto);
		this.add(lbl5);
		this.add(txtPre);
		this.add(btnnuevo);
		this.add(sp);
		
		ControladorTxt ct = new ControladorTxt();
		
		txtCod.addKeyListener(ct);
		txtNom.addKeyListener(ct);
		txtDet.addKeyListener(ct);
		txtSto.addKeyListener(ct);
		txtPre.addKeyListener(ct);

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
			String sql = "select * from productos order by CodProd";
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
			if(e.getSource()== txtCod && txtCod.getText().length() == 2)
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
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnnuevo) {
			LimpiarDatos();
		}else {
			//Cod. Producto
			if(txtCod.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "¡Ingrese el código!");
				txtCod.requestFocus();
				return;
			}
		
		Producto pr = new Producto();
		
		pr.setCodProd(txtCod.getText());
		pr.setNomProd(txtNom.getText());
		pr.setDetaProd(txtDet.getText());
		pr.setStocProd(txtSto.getText());
		pr.setPrecProd(txtPre.getText());
		
		Connection cnx = null;
		PreparedStatement pstm = null;
		
		try {
			cnx = cn.Conectar();
			String sql = "";
			
			if(e.getSource()==btnagregar) {
				sql = "insert into productos values(?,?,?,?,?)";
				
				pstm = cnx.prepareStatement(sql);
				
				pstm.setString(1, pr.getCodProd());
				pstm.setString(2, pr.getNomProd());
				pstm.setString(3, pr.getDetaProd());
				pstm.setString(4, Character.toString(pr.getStocProd()));
				pstm.setString(5, Float.toString(pr.getPrecProd()));
				
				pstm.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Producto registrado");
			}else if(e.getSource()==btneditar) {
				sql = "update productos set NomProd = ?, DetaProd = ?, StocProd = ?, PrecProd = ? where CodProd =?";
				pstm = cnx.prepareStatement(sql);
				
				pstm.setString(1, pr.getCodProd());
				pstm.setString(2, pr.getNomProd());
				pstm.setString(3, pr.getDetaProd());
				pstm.setString(4, Character.toString(pr.getStocProd()));
				pstm.setString(5, Float.toString(pr.getPrecProd()));
				
				pstm.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Producto registrado");
			}else if(e.getSource()==btnborrar) {
				int op = JOptionPane.showConfirmDialog(null,
						"¿Seguro de borrar el registro?", "SENATI", JOptionPane.YES_NO_OPTION);
				
				if(op== JOptionPane.YES_OPTION) {
					sql = "delete from productos where CodProd = ?";
					
					pstm = cnx.prepareStatement(sql);

					pstm.setString(1, pr.getCodProd());
					
					pstm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Producto eliminado");
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
		JTextField txt;
		for(int i=0;i<this.getContentPane().getComponents().length;i++) {
			if(this.getContentPane().getComponent(i) instanceof JTextField) {
				txt = (JTextField) this.getContentPane().getComponent(i);
				txt.setText("");
			}
		}
		txtCod.requestFocus();
	}
	
	public static void main(String[] args) {
		proyProductos frm = new proyProductos();
		frm.setVisible(true);

	}

}
