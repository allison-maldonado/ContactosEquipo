package mx.edu.utez.contactos.model.dao;

import mx.edu.utez.contactos.model.Contacto;
import mx.edu.utez.contactos.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoDao implements Dao<Contacto, Integer>{
    @Override
    public boolean create(Contacto entidad) {
        String sql = "INSERT INTO CONTACTOS(nombre, apellido, telefono, telefono_alternativo, correo_electronico, red_social) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellido());
            ps.setString(3, entidad.getTelefono());
            ps.setString(4, entidad.getTelefono_alternativo());
            ps.setString(5, entidad.getCorreo_electronico());
            ps.setString(6, entidad.getRed_social());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Contacto> getAll() {
        List<Contacto> datos = new ArrayList<>();
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM CONTACTOS");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contacto m = new Contacto();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setApellido(rs.getString("especie"));
                m.setTelefono(rs.getString("edad"));
                m.getTelefono_alternativo(rs.getString("personalidad"));
                m.setCorreo_electronico(rs.getString("foto"));
                m.getRed_social(rs.getString("vacunada"));
                datos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Mascota getById(Integer id) {
        String sql = "SELECT * FROM MASCOTAS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Mascota m = new Mascota();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEspecie(rs.getString("especie"));
                    m.setEdad(rs.getInt("edad"));
                    m.setPersonalidad(rs.getString("personalidad"));
                    m.setFoto(rs.getString("foto"));
                    m.setVacunada(rs.getInt("vacunada") == 1);
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Mascota entidad) {
        String sql = "UPDATE MASCOTAS SET nombre = ?, especie = ?, edad = ?, personalidad = ?, foto = ?, vacunada = ? WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getEspecie());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getPersonalidad());
            ps.setString(5, entidad.getFoto());
            ps.setInt(6, entidad.isVacunada() ? 1 : 0);
            ps.setInt(7, entidad.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM MASCOTAS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
