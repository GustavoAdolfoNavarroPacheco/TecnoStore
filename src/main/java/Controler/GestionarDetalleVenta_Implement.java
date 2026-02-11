package Controler;

import Model.Celular;
import Model.Cliente;
import Model.DetalleVenta;
import Model.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarDetalleVenta_Implement implements GestionDetalleVenta {
    
    Conexion c = new Conexion();

    @Override
    public void RegistrarDv(DetalleVenta Dv) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO venta(cantidad, subtotal, id_celular, id_venta) VALUES (?,?,?,?)");
            ps.setInt(1, Dv.getCantidad());
            ps.setDouble(2, Dv.getSubtotal());
            ps.setInt(3, Dv.getId_venta().getId());
            ps.setInt(4, Dv.getId_celular().getId());

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ActualizarDv(DetalleVenta Dv, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE venta SET fecha=?, total=?, id_cliente=? WHERE id=?");
            ps.setInt(1, Dv.getCantidad());
            ps.setDouble(2, Dv.getSubtotal());
            ps.setInt(3, Dv.getId_venta().getId());
            ps.setInt(4, Dv.getId_celular().getId());
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void EliminarDv(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM detalle_venta WHERE id=?");
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
    public ArrayList<DetalleVenta> ListarDv() {
        ArrayList<DetalleVenta> DetalleVentas = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT dv.id, dv.cantidad, dv.subtotal, v.id AS id_venta, ce.id AS id_celular FROM detalle_venta dv INNER JOIN venta v ON dv.id_venta = v.id INNER JOIN celular ce ON dv.id_celular = ce.id;");
            System.out.println("Ejecutando consulta...");
            while (rs.next()) {
                DetalleVenta Dv = new DetalleVenta();
                Dv.setId(rs.getInt(1));
                Dv.setCantidad(rs.getInt(2));
                Dv.setSubtotal(rs.getDouble(3));

                Venta Ve = new Venta();
                Ve.setId(rs.getInt(4));
                
                Celular Ce = new Celular();
                Ce.setModelo(rs.getString(5));

                Dv.setId_venta(Ve);
                Dv.setId_celular(Ce);
                DetalleVentas.add(Dv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return DetalleVentas;
    }
    
    @Override
    public ArrayList<Celular> ListarCelular() {
        ArrayList<Celular> Celulares = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM celular");
            while (rs.next()) {
                Celular Ce = new Celular();
                Ce.setId(rs.getInt("id_celular"));
                Ce.setModelo(rs.getString("modelo"));
                Ce.setSistema_operativo(rs.getString("sistema_operativo"));
                Ce.Stock(rs.getInt("stock"));


                Ventas.add(Ve);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ventas;
    }
    
    @Override
    public ArrayList<Venta> ListarVenta() {
        ArrayList<Venta> Ventas = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM venta");
            while (rs.next()) {
                Cliente Cl = new Cliente();
                Cl.setId(rs.getInt("id_cliente"));

                Venta Ve = new Venta(
                        rs.getInt("id"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        Cl
                );

                Ventas.add(Ve);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ventas;
    }
    
    @Override
    public DetalleVenta BuscarDv(int id) {
        DetalleVenta Dv = null;
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT dv.id, dv.cantidad, dv.subtotal, v.id AS id_venta, ce.id AS id_celular FROM detalle_venta dv INNER JOIN venta v ON dv.id_venta = v.id INNER JOIN celular ce ON dv.id_celular = ce.id WHERE dv.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Dv = new DetalleVenta();

                Dv.setId(rs.getInt("id"));
                Dv.setCantidad(rs.getInt("cantidad"));
                Dv.setSubtotal(rs.getDouble("subtotal"));
                
                Celular Ce = new Celular();
                Ce.setId(rs.getInt("id_cliente"));
                Ce.setModelo(rs.getString("modelo"));
                
                Venta Ve = new Venta();
                Ce.setId(rs.getInt("id_venta"));

                Dv.setId_celular(Ce);
                Dv.setId_venta(Ve);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Dv;
    }
}
