package Controler;

import Model.Celular;
import Model.Cliente;
import Model.Gama;
import Model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCelular_Implement implements GestionCelular {

    Conexion c = new Conexion();

    @Override
    public void Registrar(Celular Ce) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO celular(modelo, sistema_operativo, gama, precio, stock, id_marca) VALUES (?,?,?,?,?,?)");
            ps.setString(1, Ce.getModelo());
            ps.setString(2, Ce.getSistema_operativo());
            ps.setString(3, Ce.getGama().name());
            ps.setString(4, String.valueOf(Ce.getStock()));
            ps.setString(5, String.valueOf(Ce.getPrecio()));
            ps.setInt(6, Ce.getId_marca().getId());

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Actualizar(Celular Ce, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE celular SET modelo=?, sistema_operativo=?, gama=?, precio=?, stock=?, id_marca=? WHERE id=?");
            ps.setString(1, Ce.getModelo());
            ps.setString(2, Ce.getSistema_operativo());
            ps.setString(3, Ce.getGama().name());
            ps.setDouble(4, Ce.getPrecio());
            ps.setInt(5, Ce.getStock());
            ps.setInt(6, Ce.getId_marca().getId());
            ps.setInt(7, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Eliminar(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM celular WHERE id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el celular?", "Confirmacion", JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                System.out.println("Celular Eliminado Correctamente!");
            } else {
                System.out.println("Operacion Cancelada!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> Listar() {
        ArrayList<Celular> Celulares = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ce.id, ce.modelo, ce.sistema_operativo, ce.gama, ce.stock, ce.precio, m.id AS id_marca, m.nombre AS marca FROM celular ce INNER JOIN marca m ON ce.id_marca = m.id;");
            System.out.println("Ejecutando consulta...");
            while (rs.next()) {
                Celular ce = new Celular();
                ce.setId(rs.getInt(1));
                ce.setModelo(rs.getString(2));
                ce.setSistema_operativo(rs.getString(3));

                ce.setStock(rs.getInt(5));
                ce.setPrecio(rs.getDouble(6));

                String ga = rs.getString(4);
                if (ga != null) {
                    ce.setGama(Gama.valueOf(ga.trim()));
                }

                Marca ma = new Marca();
                ma.setNombre(rs.getString(7));

                ce.setId_marca(ma);
                Celulares.add(ce);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Celulares;
    }

    @Override
    public ArrayList<Marca> ListarMarca() {
        ArrayList<Marca> Marcas = new ArrayList<>();

        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM marca");
            while (rs.next()) {
                Marca ma = new Marca(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                Marcas.add(ma);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Marcas;
    }

    @Override
    public Celular Buscar(int id) {
        Celular Ce = null;
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT c.*, m.nombre AS nombre_marca FROM celular c INNER JOIN marca m ON c.id_marca = m.id WHERE c.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Ce = new Celular();

                Ce.setId(rs.getInt("id"));
                Ce.setModelo(rs.getString("modelo"));
                Ce.setSistema_operativo(rs.getString("sistema_operativo"));
                Ce.setGama(Gama.valueOf(rs.getString("gama")));
                Ce.setStock(rs.getInt("stock"));
                Ce.setPrecio(rs.getDouble("precio"));

                Marca ma = new Marca();
                ma.setId(rs.getInt("id_marca"));
                ma.setNombre(rs.getString("nombre_marca"));

                Ce.setId_marca(ma);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ce;
    }
    
    @Override
    public void ActualizarStock(int idCelular, int cantidadVendida) {

        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE celular SET stock = stock - ? where id = ?");
            ps.setInt(1, cantidadVendida);
            ps.setInt(2, idCelular);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
