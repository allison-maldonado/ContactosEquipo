package mx.edu.utez.integradora_poo_2026.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradora_poo_2026.model.Contacto;
import mx.edu.utez.integradora_poo_2026.model.dao.ContactoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContactoServlet", value = "/contacto")
public class ContactoServlet extends HttpServlet {

    private final ContactoDao contactoDao = new ContactoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Contacto> lista = contactoDao.getAll();
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
        nuevoContacto.setNombre(nombre);
        nuevoContacto.setApellidos(apellidos);
        nuevoContacto.setTelefono(telefono);
        nuevoContacto.setTelefonoAlt(telefonoAlt);
        nuevoContacto.setCorreo(correo);
        nuevoContacto.setRedSocial(redSocial);

        // Guardamos en la base de datos a través del DAO
        contactoDao.create(nuevoContacto);

        // Redirecciona al mismo servlet (doGet) para actualizar la lista
        response.sendRedirect("contacto");
    }
}