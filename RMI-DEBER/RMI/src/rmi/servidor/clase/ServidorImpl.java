package rmi.servidor.clase;

import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {
    private final String URL = "jdbc:sqlite:C:/Users/Richard/Desktop/empleados.db";

    public ServidorImpl() throws Exception {
        Class.forName("org.sqlite.JDBC");
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override
    public Persona consultar(int id) throws Exception {
        Connection con = conectar();
        String sql = "SELECT * FROM empleado WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Persona p = null;
        if (rs.next()) {
            p = new Persona(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
            );
        }
        rs.close();
        ps.close();
        con.close();
        return p;
    }

    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> lista = new ArrayList<>();
        Connection con = conectar();
        String sql = "SELECT * FROM empleado";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Persona(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
            ));
        }
        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    @Override
    public String insertar(Persona p) throws Exception {
        Connection con = conectar();
        String sql = "INSERT INTO empleado(nombre, correo, cargo, sueldo) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getCorreo());
        ps.setString(3, p.getCargo());
        ps.setDouble(4, p.getSueldo());
        int rows = ps.executeUpdate();
        ps.close();
        con.close();
        return rows > 0 ? "Insertado correctamente" : "Error al insertar";
    }

    @Override
    public String actualizar(Persona p) throws Exception {
        Connection con = conectar();
        String sql = "UPDATE empleado SET nombre = ?, correo = ?, cargo = ?, sueldo = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getCorreo());
        ps.setString(3, p.getCargo());
        ps.setDouble(4, p.getSueldo());
        ps.setInt(5, p.getClave());
        int rows = ps.executeUpdate();
        ps.close();
        con.close();
        return rows > 0 ? "Actualizado correctamente" : "Error al actualizar";
    }

    @Override
    public String eliminar(int id) throws Exception {
        Connection con = conectar();
        String sql = "DELETE FROM empleado WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        ps.close();
        con.close();
        return rows > 0 ? "Eliminado correctamente" : "Error al eliminar";
    }
}
