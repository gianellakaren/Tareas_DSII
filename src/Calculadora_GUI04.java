import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Calculadora_GUI04 extends JFrame implements ActionListener {
	private JLabel lblnum1,lblnum2,lbloper,lblres;
	private JTextField txtnum1,txtnum2,txtrpta;
	private JButton btnsum,btnres,btnmult,btndiv,btnmod,btnpor,btnra,btnpot,btnlim;
	
	public Calculadora_GUI04() {
		setLayout(null);
		
		lblnum1 = new JLabel("Elija el primer número: ");
		lblnum1.setBounds(10,10,150,30);
		
		txtnum1 = new JTextField();
		txtnum1.setBounds(10,40,90,30);
		
		lblnum2 = new JLabel("Escriba el otro número: ");
		lblnum2.setBounds(10,80,150,30);
		
		txtnum2 = new JTextField();
		txtnum2.setBounds(10,110,90,30);
		
		lbloper = new JLabel("Elija la operación: ");
		lbloper.setBounds(200,10,150,30);
		
		btnsum = new JButton("+");
		btnsum.setBounds(200,40,45,30);
		btnsum.addActionListener(this);
		
		btnres = new JButton("-");
		btnres.setBounds(250,40,45,30);
		btnres.addActionListener(this);
		
		btnmult = new JButton("*");
		btnmult.setBounds(300,40,45,30);
		btnmult.addActionListener(this);
		
		btndiv = new JButton("÷");
		btndiv.setBounds(200,80,45,30);
		btndiv.addActionListener(this);
		
		btnmod = new JButton("m");
		btnmod.setBounds(250,80,45,30);
		btnmod.addActionListener(this);
		
		btnpor = new JButton("%");
		btnpor.setBounds(300,80,45,30);
		btnpor.addActionListener(this);
		
		btnra = new JButton("v/");
		btnra.setBounds(200,120,45,30);
		btnra.addActionListener(this);
		
		btnpot = new JButton("^");
		btnpot.setBounds(250,120,45,30);
		btnpot.addActionListener(this);
		
		btnlim = new JButton("C");
		btnlim.setBounds(300,120,45,30);
		btnlim.addActionListener(this);
		
		lblres = new JLabel("Resultado:");
		lblres.setBounds(10,150,150,30);
		
		txtrpta = new JTextField();
		txtrpta.setBounds(10,180,90,30);
		txtrpta.setEnabled(false);
		
		add(lblnum1);
		add(txtnum1);
		add(lblnum2);
		add(txtnum2);
		add(lbloper);
		add(btnsum);
		add(btnres);
		add(btnmult);
		add(btndiv);
		add(btnmod);
		add(btnpor);
		add(btnra);
		add(btnpot);
		add(btnlim);
		add(lblres);
		add(txtrpta);
	}
	
	public void actionPerformed (ActionEvent e) {
		//float n1=0,n2=0;
		float n1 = Float.parseFloat(this.txtnum1.getText());
		float n2 = Float.parseFloat(this.txtnum2.getText());
		int s=0,r=0,m=0,mo=0;
		float d,p;
		double ra,pot;
		
		if(e.getSource().equals(btnsum)) {
			s = (int)(n1 + n2);
			txtrpta.setText(String.valueOf(s));
		}else if(e.getSource().equals(btnres)) {
			r = (int)(n1 - n2);
			txtrpta.setText(String.valueOf(r));
		}else if(e.getSource().equals(btnmult)) {
			m = (int)(n1 * n2);
			txtrpta.setText(String.valueOf(m));
		}else if(e.getSource().equals(btndiv)) {
			d = n1/n2;
			if(d==Double.POSITIVE_INFINITY) {
				JOptionPane.showMessageDialog(null,"Fallo");
			}else {
				txtrpta.setText(String.valueOf(d));
			}
		}else if(e.getSource().equals(btnmod)) {
			mo = (int)(n1 % n2);
			txtrpta.setText(String.valueOf(mo));
		}else if(e.getSource().equals(btnpor)) {
			p = (n1/100)*n2;
			txtrpta.setText(String.valueOf(p));
			
			/*if(this.txtnum1.getText().isEmpty()!=true && this.txtnum2.getText().isEmpty()==true) {
				p = (float)(n1/100);
				txtrpta.setText(String.valueOf(p));
				txtnum2.setEnabled(false);
			}*/
		}else if(e.getSource().equals(btnra)) {
			ra = Math.pow((double)n1,1/(double)n2);
			txtrpta.setText(String.valueOf(ra));
		}else if(e.getSource().equals(btnpot)) {
			pot = (int)Math.pow(n1, n2);
			txtrpta.setText(String.valueOf(pot));
		}else if(e.getSource().equals(btnlim)) {
			txtnum1.setText("");
			txtnum2.setText("");
			txtrpta.setText("");
		}	
	}
	
	public static void main(String[] args) {
		Calculadora_GUI04 ventana = new Calculadora_GUI04();
		ventana.setBounds(100,100,400,300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setTitle("Calculadora 4.0");
		ventana.setVisible(true);
	}

}
