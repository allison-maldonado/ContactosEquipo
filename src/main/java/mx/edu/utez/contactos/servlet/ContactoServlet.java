package mx.edu.utez.contactos.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.contactos.model.Contacto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContactoServlet", value = "/contacto")
public class ContactoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaContactos", lista);
        // Apunta a index.jsp tal como lo solicitó el profesor
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Obtenemos los parámetros del formulario de contactos
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String telefonoAlt = request.getParameter("telefonoAlt");
        String correo = request.getParameter("correo");
        String redSocial = request.getParameter("redSocial");

        // Creamos el objeto del modelo Contacto
        Contacto nuevoContacto = new Contacto();


        // Guardamos en la base de datos a través del DAO

        // Redirecciona al mismo servlet (doGet) para actualizar la lista
        response.sendRedirect("contacto");
    }
}