package mx.edu.utez.contactos.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.contactos.model.Contacto;
import mx.edu.utez.contactos.model.dao.ContactoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContactoServlet", value = "/contacto")
public class ContactoServlet extends HttpServlet {

    private final ContactoDao contactoDao = new ContactoDao();

    // El método doGet se encarga de listar los contactos y mandarlos a la vista (index.jsp)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Obtenemos la lista de contactos desde la Base de Datos a través del DAO
        List<Contacto> lista = contactoDao.getAll();

        // 2. Colocamos la lista en el alcance del request para que el JSP la pueda leer con JSTL
        request.setAttribute("listaContactos", lista);

        // 3. Redirigimos la petición al archivo index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // El método doPost se encarga de recibir los datos del formulario y registrarlos
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try{
            // 1. Recibimos los parámetros enviados desde el formulario del index.jsp
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String telefono = request.getParameter("telefono");
            String telefonoAlt = request.getParameter("telefonoAlt");
            String correo = request.getParameter("correo");
            String redSocial = request.getParameter("redSocial");

            // 2. Creamos el objeto del modelo y le asignamos los valores recibidos
            Contacto nuevoContacto = new Contacto();
            nuevoContacto.setNombre(nombre);
            nuevoContacto.setApellido(apellidos);
            nuevoContacto.setTelefono(telefono);
            nuevoContacto.setTelefono_alternativo(telefonoAlt);
            nuevoContacto.setCorreo_electronico(correo);
            nuevoContacto.setRed_social(redSocial);

            // 3. Ejecutamos el método del DAO para guardarlo en la Base de Datos
            contactoDao.create(nuevoContacto);
        } catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
        }

        // 4. Redireccionamos de vuelta al servlet (doGet) mediante un sendRedirect
        // para limpiar el formulario y evitar que si el usuario refresca la página se vuelva a registrar el mismo contacto.
        response.sendRedirect("contacto");
    }
}