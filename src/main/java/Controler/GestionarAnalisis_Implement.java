package Controler;

import Model.Celular;
import Model.Venta;
import Model.Marca;
import Model.Gama;
import java.sql.*;
import java.util.ArrayList;

public class GestionarAnalisis_Implement implements GestionAnalisis {

    Conexion c = new Conexion();

    @Override
    public ArrayList<Celular> StockBajo() {

        ArrayList<Celular> lista = new ArrayList<>();

        String sql = "SELECT c.id, c.modelo, c.stock, c.precio, c.gama, m.id AS marca_id, m.nombre AS marca_nombre FROM celular c JOIN marca m ON c.id_marca = m.id WHERE c.stock < 5";

        try (Connection con = c.Conexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Encontrado: " + rs.getString("modelo"));
                Marca marca = new Marca();
                marca.setId(rs.getInt("marca_id"));
                marca.setNombre(rs.getString("marca_nombre"));

                Celular celular = new Celular();
                celular.setId(rs.getInt("id"));
                celular.setModelo(rs.getString("modelo"));
                celular.setStock(rs.getInt("stock"));
                celular.setPrecio(rs.getDouble("precio"));
                celular.setGama(Gama.valueOf(rs.getString("gama")));
                celular.setId_marca(marca);

                lista.add(celular);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public ArrayList<Celular> MasVendido() {

        ArrayList<Celular> lista = new ArrayList<>();

        String sql = "SELECT ce.id, ce.modelo, SUM(dv.cantidad) AS total_vendido FROM detalle_venta dv JOIN celular ce ON dv.id_celular = ce.id GROUP BY ce.id, ce.modelo ORDER BY total_vendido DESC LIMIT 3";

        try (Connection con = c.Conexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Celular celular = new Celular();
                celular.setId(rs.getInt("id"));
                celular.setModelo(rs.getString("modelo"));

                System.out.println("Modelo: " + rs.getString("modelo") +
                                   " | Total Vendido: " + rs.getInt("total_vendido"));

                lista.add(celular);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    @Override
    public ArrayList<Venta> VentasPorMes() {

        ArrayList<Venta> lista = new ArrayList<>();

        String sql = "SELECT YEAR(fecha) AS anio, MONTH(fecha) AS mes, SUM(total) AS total_mes FROM venta GROUP BY YEAR(fecha), MONTH(fecha) ORDER BY anio, mes";

        try (Connection con = c.Conexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Venta venta = new Venta();

                System.out.println("Anio: " + rs.getInt("anio") +
                                   " | Mes: " + rs.getInt("mes") +
                                   " | Total: " + rs.getDouble("total_mes"));

                lista.add(venta);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }
}