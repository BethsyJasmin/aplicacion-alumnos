package dao;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Alumno;

public class DAOAlumno {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Alumno alumno;

    public ArrayList<Alumno> listar() {
        ArrayList<Alumno> list = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";

        try {
            con = ConexionMySQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setNL(rs.getInt("NL"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setPaterno(rs.getString("Paterno"));
                alumno.setMaterno(rs.getString("Materno"));
                list.add(alumno);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public String mostrar() 
    {
        String r;
        String fila;

        r = """
          <br><br>
        <table border="0" class="tabla-contenido">
            <caption>Lista de alumnos<caption>
            <theard>
                <tr>
                    <th>NL</th>
                    <th>Nombre</th>
                    <th>Paterno</th>
                    <th>Materno</th>
                    <th colspan="2">Acciones</th>
                </tr>
            </theard>
          <tbody>
         """;

        for (Alumno reg : listar()) 
        {
            
         fila ="""
            <tr>
                    <td>%d</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>
                        <form method='post'>
                            <input type='hidden' name='accion' value='Editar'/>
                            <input type='hidden' name='tfNL' value='%d'/>
                            <input type='submit' value='Editar' class= "btn">
                        </form>
                    </td>
                    <td>
                        <form method='post'>
                            <input type='hidden' name='accion' value='Eliminar'/>
                            <input type='hidden' name='tfNL' value='%d'/>
                            <input type='submit' value='Eliminar' class= "btn"/>
                        </form>
                    </td>
            </tr>
            """;
         r = r + String.format(fila, reg.getNL(), reg.getNombre(), reg.getPaterno(), reg.getMaterno(), reg.getNL(), reg.getNL());
        }
        r = r + """
            </tbody>
        </table>
        """;
        return r;
    }

    public Alumno buscar(int nL) 
    {
        alumno = null;
        String sql = "SELECT * FROM alumnos WHERE NL =" + nL;
        try 
        {
            con = ConexionMySQL.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                alumno = new Alumno();
                alumno.setNL(rs.getInt("NL"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setPaterno(rs.getString("Paterno"));
                alumno.setMaterno(rs.getString("Materno"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {}
        return alumno;
    }

    public boolean agregar(Alumno alumno) 
    {
        String sql = "INSERT INTO alumnos VALUES("
                + alumno.getNL()            + ", "
                + "'" + alumno.getNombre()  + "', "
                + "'" + alumno.getPaterno() + "', "
                + "'" + alumno.getMaterno() + "')";
        try 
        {
            con = ConexionMySQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {}
        return true;
    }

    public boolean editar(Alumno alumno, int old) 
    {
       String sql = "UPDATE alumnos SET "
        + "NL       = "  + alumno.getNL()       + ", "
        + "Nombre   = '" + alumno.getNombre()   + "', "
        + "Paterno  = '" + alumno.getPaterno()  + "', "
        + "Materno  = '" + alumno.getMaterno()  + "' "
        + "WHERE NL = "  + old;

        try 
        {
            con = ConexionMySQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {}
        return true;
    }

    public boolean eliminar(int nL) 
    {
        String sql = "DELETE FROM alumnos WHERE NL =" + nL;
        try 
        {
            con = ConexionMySQL.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {}
        return true;
    }
}
