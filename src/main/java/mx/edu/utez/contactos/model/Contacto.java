package mx.edu.utez.contactos.model;

public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String telefono_alternativo;
    private String correo_electronico;
    private String red_social;


    public Contacto() {
    }

    public Contacto(int id,String nombre, String telefono, String apellido, String telefono_alternativo, String correo_electronico, String red_social) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido = apellido;
        this.telefono_alternativo = telefono_alternativo;
        this.correo_electronico = correo_electronico;
        this.red_social = red_social;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_alternativo() {
        return telefono_alternativo;
    }

    public void setTelefono_alternativo(String telefono_alternativo) {
        this.telefono_alternativo = telefono_alternativo;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getRed_social() {
        return red_social;
    }

    public void setRed_social(String red_social) {
        this.red_social = red_social;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + ',' + nombre + ',' + apellido + ',' + telefono + ','
                + telefono_alternativo + ',' + correo_electronico + ',' + red_social;
    }
}
