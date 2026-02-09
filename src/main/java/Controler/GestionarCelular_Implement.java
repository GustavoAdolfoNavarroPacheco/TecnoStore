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
            ps.setString(3, Ce.getGama());
            ps.setInt(4, (int) Ce.getPrecio());
            ps.setInt(5, Ce.getStock());
            ps.setString(6, Ce.getId_marca());

            ps.executeUpdate();
            System.out.println("Registro Realizado con Exito!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Actualizar(Celular Ce, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE celular SET modelo=?, sistema_operativo=?, gama=?, precio=?, stock=?, id_marca=?, WHERE id=?");
            ps.setString(1, Ce.getModelo());
            ps.setString(2, Ce.getSistema_operativo());
            ps.setString(3, Ce.getGama());
            ps.setInt(4, (int) Ce.getPrecio());
            ps.setInt(5, Ce.getStock());
            ps.setString(6, Ce.getId_marca());
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
            ResultSet rs = st.executeQuery("SELECT * FROM celular");

            while (rs.next()) {
                Celular Ce = new Celular(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        rs.getString("gama")
                        rs.getInt("precio"),
                        rs.getInt("stock")
                        rs.getString("marca")
                );
                Celulares.add(Ce);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Celulares;
    }

    @Override
    public Celular Buscar(int id) {
        Celular Ce = new Celular();
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM celular WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Ce.setId(rs.getInt(1));
                Ce.setModelo(rs.getString(2));
                Ce.setSistema_operativo(rs.getString(3));
                Ce.setGama(rs.getString(4));
                Ce.setPrecio(rs.getInt(5));
                Ce.setStock(rs.getInt(6));
                Ce.setMarca(rs.getString(7));
                return Ce;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Ce;
    }
}
