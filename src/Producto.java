import javax.swing.JOptionPane;

public class Producto {
	private String CodProd;
	private String NomProd;
	private String DetaProd;
	private String CatProd;
	private int StocProd;
	private float PrecProd;
	public String getCodProd() {
		return CodProd;
	}
	public void setCodProd(String codProd) {
		CodProd = codProd;
	}
	public String getNomProd() {
		return NomProd;
	}
	public void setNomProd(String nomProd) {
		NomProd = nomProd;
	}
	public String getDetaProd() {
		return DetaProd;
	}
	public void setDetaProd(String detaProd) {
		DetaProd = detaProd;
	}
	
	public String getCatProd() {
		return CatProd;
	}
	public void setCatProd(String catProd) {
		CatProd = catProd;
	}
	public int getStocProd() {
		return StocProd;
	}
	public void setStocProd(String stocProd) {
		StocProd = Integer.parseInt(stocProd);
	}
	public float getPrecProd() {
		return PrecProd;
	}
	public void setPrecProd(String precProd) {
		PrecProd = Float.parseFloat(precProd);
	}
	
	
	
}
