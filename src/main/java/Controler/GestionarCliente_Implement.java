package Controler;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCliente_Implement implements GestionCliente {

    Conexion c = new Conexion();

    @Override
    public void Registrar(Cliente cl) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente(nombre, identificacion, correo, celular) VALUES (?,?,?,?)");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Actualizar(Cliente cl, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET nombre=?, identificacion=?, correo=?, celular=? WHERE id=?");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Eliminar(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                System.out.println("Cliente Eliminado Correctamente!");
            } else {
                System.out.println("Operacion Cancelada!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> Listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");

            while (rs.next()) {
                Cliente Cl = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("celular")
                );
                clientes.add(Cl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clientes;
    }

    @Override
    public boolean VerificarID(String identificacion) {
        System.out.println("");
        return false;
    }

    @Override
    public Cliente Buscar(int id) {
        Cliente Cl = new Cliente();
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cl.setId(rs.getInt(1));
                Cl.setNombre(rs.getString(2));
                Cl.setIdentificacion(rs.getString(3));
                Cl.setCorreo(rs.getString(4));
                Cl.setTelefono(rs.getString(5));
                return Cl;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Cl;
    }
}
