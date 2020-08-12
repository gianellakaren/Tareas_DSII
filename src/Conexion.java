import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tareads";
	private static final String USER = "root";
	private static final String PWD = "";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection Conectar () {
		Connection cnx = null;
		try {
			cnx = DriverManager.getConnection(URL,USER,PWD);
			//System.out.println("Se conecto a la bd");
			
		} catch (Exception e) {
			System.out.println("Hubo un error en la conexion a la bd");
			e.printStackTrace();
		}
		return cnx;
	}
	
	public static void main(String[] args) {
		//Conexion cn = new Conexion();
		//cn.Conectar();
	}

}
