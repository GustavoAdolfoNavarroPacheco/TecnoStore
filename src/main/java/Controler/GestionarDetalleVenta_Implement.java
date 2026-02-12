package Controler;

import Model.Celular;
import Model.Cliente;
import Model.DetalleVenta;
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

public class GestionarDetalleVenta_Implement implements GestionDetalleVenta {

    Conexion c = new Conexion();

    @Override
    public void RegistrarDv(DetalleVenta Dv) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO detalle_venta(cantidad, subtotal, id_celular, id_venta) VALUES (?,?,?,?)");
            ps.setInt(1, Dv.getCantidad());
            ps.setDouble(2, Dv.getSubtotal());
            ps.setInt(3, Dv.getId_celular().getId());
            ps.setInt(4, Dv.getId_venta().getId());
            System.out.println("Ejecutando INSERT detalle venta...");

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ActualizarDv(DetalleVenta Dv, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE detalle_venta SET cantidad=?, subtotal=?, id_celular=?, id_venta=? WHERE id=?");
            ps.setInt(1, Dv.getCantidad());
            ps.setDouble(2, Dv.getSubtotal());
            ps.setInt(3, Dv.getId_celular().getId());
            ps.setInt(4, Dv.getId_venta().getId());
            ps.setInt(5, id);

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
    public ArrayList<DetalleVenta> ListarRdv() {
        ArrayList<DetalleVenta> lista = new ArrayList<>();

        String sql = ("SELECT dv.id AS dv_id, dv.cantidad, dv.subtotal, v.id AS venta_id, v.fecha, v.total, c.id AS cliente_id, c.nombre, ce.id AS celular_id, ce.modelo, ce.sistema_operativo, ce.gama, ce.precio, ce.stock, m.id AS marca_id, m.nombre AS marca_nombre FROM detalle_venta dv JOIN venta v ON dv.id_venta = v.id JOIN cliente c ON v.id_cliente = c.id JOIN celular ce ON dv.id_celular = ce.id JOIN marca m ON ce.id_marca = m.id");
        try (Connection con = c.Conexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setNombre(rs.getString("nombre"));

                Venta venta = new Venta();
                venta.setId(rs.getInt("venta_id"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setId_Cliente(cliente);
                
                Marca marca = new Marca();
                marca.setId(rs.getInt("marca_id"));
                marca.setNombre(rs.getString("marca_nombre"));

                Celular celular = new Celular();
                celular.setId(rs.getInt("celular_id"));
                celular.setModelo(rs.getString("modelo"));
                celular.setSistema_operativo(rs.getString("sistema_operativo"));
                celular.setGama(Gama.valueOf(rs.getString("gama")));
                celular.setPrecio(rs.getDouble("precio"));
                celular.setStock(rs.getInt("stock"));
                celular.setId_marca(marca);

                DetalleVenta dv = new DetalleVenta();
                dv.setId(rs.getInt("dv_id"));
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setSubtotal(rs.getDouble("subtotal"));
                dv.setId_venta(venta);
                dv.setId_celular(celular);

                lista.add(dv);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public ArrayList<Celular> ListarCelular() {
        ArrayList<Celular> Celulares = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM celular");
            while (rs.next()) {
                Celular Ce = new Celular();
                Ce.setId(rs.getInt("id"));
                Ce.setModelo(rs.getString("modelo"));
                Ce.setSistema_operativo(rs.getString("sistema_operativo"));
                String ga = rs.getString(4);
                if (ga != null) {
                    Ce.setGama(Gama.valueOf(ga.trim()));
                }
                Ce.setStock(rs.getInt("stock"));
                Ce.setPrecio(rs.getDouble("precio"));

                Marca ma = new Marca();
                ma.setNombre(rs.getString(7));

                Ce.setId_marca(ma);
                Celulares.add(Ce);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Celulares;
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

            PreparedStatement ps = con.prepareStatement("SELECT dv.id, dv.cantidad, dv.subtotal, v.id AS id_venta, ce.id AS id_celular, ce.modelo FROM detalle_venta dv INNER JOIN venta v ON dv.id_venta = v.id INNER JOIN celular ce ON dv.id_celular = ce.id WHERE dv.id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Dv = new DetalleVenta();
                Dv.setId(rs.getInt("id"));
                Dv.setCantidad(rs.getInt("cantidad"));
                Dv.setSubtotal(rs.getDouble("subtotal"));

                Celular Ce = new Celular();
                Ce.setId(rs.getInt("id_celular"));
                Ce.setModelo(rs.getString("modelo"));

                Venta Ve = new Venta();
                Ve.setId(rs.getInt("id_venta"));

                Dv.setId_celular(Ce);
                Dv.setId_venta(Ve);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Dv;
    }

    @Override
    public double SubtotalDv(int cantidad, double precio) {
        double subtotal = 0;
        subtotal = cantidad * precio;
        return subtotal;
    }
}
