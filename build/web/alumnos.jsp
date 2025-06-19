<%-- 
    Document   : alumnos.jsp
    Created on : 9 jun 2025, 5:34:43 p.m.
    Author     : Beths
--%>

<%@page import="modelo.Alumno"%>
<%@page import="dao.DAOAlumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DAOAlumno lista = new DAOAlumno();
    Alumno edit = null;
    
    if (request.getAttribute("edit") != null) 
    {
        edit = (Alumno) request.getAttribute("edit");
    }
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos.css"/>
    <title>Calificaciones</title>
</head>

<body>
    <div id='form_registro'>
        <h2><%= (edit !=null) ? "Modificar calificaciones" : "Registro de calificaciones" %></h2>
        <form method="post"> <!-- <form method="post" action="SAlumno"> -->
            <input type="hidden" name="accion"    id="accion"    value="<%= (edit != null) ? "Modificar"       : "Agregar" %>" />
            <input type="hidden" name="tfnLOld"   id="tfnLOld"   value="<%= (edit != null) ? edit.getNL()      : "" %>" />
            <input type="text"   name="tfNL"      id="tfNL"      value="<%= (edit != null) ? edit.getNL()      : "" %>" placeholder="NL"       required />
            <input type="text"   name="tfNombre"  id="tfNombre"  value="<%= (edit != null) ? edit.getNombre()  : "" %>" placeholder="Nombre"   required />
            <input type="text"   name="tfPaterno" id="tfPaterno" value="<%= (edit != null) ? edit.getPaterno() : "" %>" placeholder="Paterno"  required />
            <input type="text"   name="tfMaterno" id="tfMaterno" value="<%= (edit != null) ? edit.getMaterno() : "" %>" placeholder="Materno"  required />

            <input type="submit" value="<%= (edit != null) ? " Modificar" : "Agregar" %>" />
        </form>
    </div>
    <%= lista.mostrar() %>
</body>
</html>