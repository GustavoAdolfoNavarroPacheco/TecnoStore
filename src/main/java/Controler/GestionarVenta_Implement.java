package Controler;

import Model.Celular;
import Model.Cliente;
import Model.Gama;
import Model.Marca;
import Model.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarVenta_Implement implements GestionVenta {
    
    Conexion c = new Conexion();

    @Override
    public void Registrar(Venta Ve) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO venta(fecha, total, id_cliente) VALUES (?,?,?)");
            ps.setString(1, Ve.getFecha());
            ps.setDouble(2, Ve.getTotal());
            ps.setInt(3, Ve.getId_Cliente().getId());

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Actualizar(Venta Ve, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE venta SET fecha=?, total=?, id_cliente=? WHERE id=?");
            ps.setString(1, Ve.getFecha());
            ps.setDouble(2, Ve.getTotal());
            ps.setInt(3, Ve.getId_Cliente().getId());
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Eliminar(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM venta WHERE id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la venta?", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                System.out.println("Venta Eliminada Correctamente!");
            } else {
                System.out.println("Operacion Cancelada!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Venta> Listar() {
        ArrayList<Venta> Ventas = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ve.id, ve.fecha, ve.total, cl.id AS id_cliente, cl.nombre AS cliente FROM venta ve INNER JOIN cliente cl ON ve.id_cliente = cl.id;");
            System.out.println("Ejecutando consulta...");
            while (rs.next()) {
                Venta ve = new Venta();
                ve.setId(rs.getInt(1));
                ve.setFecha(rs.getString(2));
                ve.setTotal(rs.getDouble(3));

                Cliente cl = new Cliente();
                cl.setNombre(rs.getString(7));

                ve.setId_Cliente(cl);
                Ventas.add(ve);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ventas;
    }

    @Override
    public ArrayList<Cliente> ListarCliente() {
        ArrayList<Cliente> Clientes = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente cl = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("identificacion"),
                        rs.getString("correo"),
                        rs.getString("celular")
                );

                Clientes.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Clientes;
    }
    
    @Override
    public Venta Buscar(int id) {
        Venta Ve = null;
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT ve.id, ve.fecha, ve.total, cl.id AS id_cliente, cl.nombre AS cliente FROM venta ve INNER JOIN cliente cl ON ve.id_cliente = cl.id WHERE c.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Ve = new Venta();

                Ve.setId(rs.getInt("id"));
                Ve.setFecha(rs.getString("fecha"));
                Ve.setTotal(rs.getDouble("total"));

                Cliente Cl = new Cliente();
                Cl.setId(rs.getInt("id_cliente"));
                Cl.setNombre(rs.getString("nombre_cliente"));

                Ve.setId_Cliente(Cl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ve;
    }

    
    
}
