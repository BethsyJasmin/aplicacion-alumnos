
package control;

import dao.DAOAlumno;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alumno;

@WebServlet("/Alumno") public class Servlet_Alumno extends HttpServlet 
{

    private Alumno alumno;
    private DAOAlumno dao;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html;charset=UTF-8");
        Alumno edit=null;
        
        try 
        {
            String accion = request.getParameter("accion");
            if("Agregar".equals(accion))
            {
                alumno = new Alumno();
                alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                alumno.setNombre(request.getParameter("tfNombre"));
                alumno.setPaterno(request.getParameter("tfPaterno"));
                alumno.setMaterno(request.getParameter("tfMaterno"));
                
                dao=new DAOAlumno();
                dao.agregar(alumno);
                
                request.setAttribute("edit",edit);
            
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }else if ("Editar".equals(accion))
            {
                dao=new DAOAlumno();
                edit = dao.buscar(Integer.parseInt(request.getParameter("tfNL")));
                
                request.setAttribute("edit",edit);
            
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }else if ("Modificar".equals(accion))
            {
                alumno = new Alumno();
                alumno.setNL(Integer.parseInt(request.getParameter("tfNL")));
                alumno.setNombre(request.getParameter("tfNombre"));
                alumno.setPaterno(request.getParameter("tfPaterno"));
                alumno.setMaterno(request.getParameter("tfMaterno"));
                
                dao=new DAOAlumno();
                dao.editar (alumno, Integer.parseInt(request.getParameter("tfNL")));
                
                edit = null;
                request.setAttribute("edit",edit);
            
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }
            else if ("Eliminar".equals(accion))
            {
                int nl = Integer.parseInt(request.getParameter("tfNL"));
                dao=new DAOAlumno();
                dao.eliminar(nl);
                
                request.setAttribute("edit",edit);
            
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("edit",edit);
            
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alumnos.jsp");
                rd.forward(request, response);
            }

        } catch (IOException | ServletException ex) 
        {
            Logger.getLogger(Servlet_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    {
        processRequest(request, response);
    }
}
