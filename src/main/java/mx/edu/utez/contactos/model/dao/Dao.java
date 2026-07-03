package mx.edu.utez.contactos.model.dao;

import mx.edu.utez.contactos.model.Contacto;

import java.util.List;

public interface Dao {
    boolean create(Contacto entidad);

    List<Contacto> getAll();
}
