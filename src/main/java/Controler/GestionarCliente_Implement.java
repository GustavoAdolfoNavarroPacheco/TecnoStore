package Controler;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCliente_Implement implements GestionCliente {
    Conexion c = new Conexion();

    @Override
    public void Registrar(Cliente cl) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Cliente(nombre, identificacion, correo, telefono) values (?,?,?,?)");
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
            PreparedStatement ps = con.prepareStatement("UPDATE Cliente SET nombre = ?, identificacion = ?, correo = ?, telefono = ? WHERE id = ?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void Eliminar(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliete?", null, JOptionPane.YES_NO_OPTION);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public ArrayList<Cliente> Listar() {
        System.out.println("");
        return null;
    }
    
    /*
    @Override
    public Cliente Buscar(int id) {
        Cliente c = new Cliente();
        Gestion gc = new GestionarCelularImpl();
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where id=" + id);
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setIdentificacion(rs.getString(3));
                c.setCorreo(rs.getString(4));
                c.setTelefono(rs.getString(5));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    */
    
    @Override
    public boolean VerificarID(String identificacion) {
        System.out.println("");
        return false;
    }

    @Override
    public Cliente Buscar(int id) {
        System.out.println("");
        return null;
    }

}

