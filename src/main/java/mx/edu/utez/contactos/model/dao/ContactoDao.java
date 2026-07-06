package mx.edu.utez.contactos.model.dao;

import mx.edu.utez.contactos.model.Contacto;
import mx.edu.utez.contactos.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoDao implements Dao<Contacto,Integer>{
    @Override
    public boolean create(Contacto entidad) {
        String sql = "INSERT INTO CONTACTO(NOMBRE, APELLIDO, TELEFONO, TELEFONO_ALTERNATIVO, CORREO_ELECTRONICO, RED_SOCIAL) VALUES(?, ?, ?, ?, ?, ?)";
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
             PreparedStatement ps = con.prepareStatement("SELECT * FROM CONTACTO");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contacto m = new Contacto();
                m.setId(rs.getInt("ID_CONTACTO"));
                m.setNombre(rs.getString("NOMBRE"));
                m.setApellido(rs.getString("APELLIDO"));
                m.setTelefono(rs.getString("TELEFONO"));
                m.setTelefono_alternativo(rs.getString("TELEFONO_ALTERNATIVO"));
                m.setCorreo_electronico(rs.getString("CORREO_ELECTRONICO"));
                m.setRed_social(rs.getString("RED_SOCIAL"));

                datos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Contacto getById(Integer id) {
        String sql = "SELECT * FROM CONTACTO WHERE ID_CONTACTO = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Contacto c = new Contacto();
                    c.setId(rs.getInt("ID_CONTACTO"));
                    c.setNombre(rs.getString("NOMBRE"));
                    c.setApellido(rs.getString("APELLIDO"));
                    c.setTelefono(rs.getString("TELEFONO"));
                    c.setTelefono_alternativo(rs.getString("TELEFONO_ALTERNATIVO"));
                    c.setCorreo_electronico(rs.getString("CORREO_ELECTRONICO"));
                    c.setRed_social(rs.getString("RED_SOCIAL"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Contacto entidad) {
        String sql = "UPDATE CONTACTO SET NOMBRE = ?, APELLIDO = ?, TELEFONO = ?, TELEFONO_ALTERNATIVO = ?, CORREO_ELECTRONICO = ?, RED_SOCIAL = ? WHERE ID_CONTACTO = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellido());
            ps.setString(3, entidad.getTelefono());
            ps.setString(4, entidad.getTelefono_alternativo());
            ps.setString(5, entidad.getCorreo_electronico());
            ps.setString(6, entidad.getRed_social());
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
        String sql = "DELETE FROM CONTACTO WHERE ID_CONTACTO = ?";
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
