package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL 
{

    private static String servidor;
    private static String nombreBD;
    private static String usuario;
    private static String password;
    private static String puerto;
    
    public static Connection getConnection() throws SQLException
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("No se Cargo el Driver");
        }
        servidor = "localhost";
        nombreBD = "BD";
        usuario = "root";              
        password = "";            
        puerto = ":3306";            

        return DriverManager.getConnection( "jdbc:mysql://" + servidor + puerto + "/" + nombreBD,usuario,password);
    }

}
