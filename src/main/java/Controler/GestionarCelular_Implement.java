package Controler;

import Model.Celular;
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
    public void Guardar(Celular ce) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Celular(modelo, sistema_operativo, gama, stock, precio, id_marca) values (?,?,?,?,?,?)");
            ps.setString(1, ce.getModelo());
            ps.setString(2, ce.getSistema_operativo());
            ps.setObject(3, ce.getGama());
            ps.setString(4, String.valueOf(ce.getStock()));
            ps.setString(5, String.valueOf(ce.getPrecio()));
            ps.setString(6, String.valueOf(ce.getId_marca().getId()));
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Actualizar(Celular ce, int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Celular SET modelo=?, sistema_operativo=?, gama=?, stock=?, precio=?, id_marca=? WHERE id=?");
            ps.setString(1, ce.getModelo());
            ps.setString(2, ce.getSistema_operativo());
            ps.setObject(3, ce.getGama());
            ps.setString(4, String.valueOf(ce.getStock()));
            ps.setString(5, String.valueOf(ce.getPrecio()));
            ps.setString(6, String.valueOf(ce.getId_marca().getId()));
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Eliminar(int id) {
        try (Connection con = c.Conexion()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM celular WHERE id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el area?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Operacion cancelada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> Listar() {
        ArrayList<Celular> celulares = new ArrayList<>();
        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Celular");
            while (rs.next()) {
                Celular ce = new Celular();
                ce.setId(rs.getInt("id"));
                ce.setModelo(rs.getString("modelo"));
                ce.setSistema_operativo(rs.getString("sistema_operativo"));
                ce.setGama((Gama) rs.getObject("gama"));
                ce.setStock(rs.getInt("stock"));
                ce.setPrecio(rs.getDouble("precio"));
                ce.setId_marca(new Marca(rs.getInt("id_marca")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }

    @Override
    public Celular Buscar(int id) {
        Celular ce = new Celular();
        try (Connection con = c.Conexion()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Celular WHERE id=" + id);
            while (rs.next()) {
                ce.setId(rs.getInt(1));
                ce.setModelo(rs.getString(2));
                ce.setSistema_operativo(rs.getString(3));
                ce.setGama((Gama) rs.getObject(4));
                ce.setStock(rs.getInt(5));
                ce.setPrecio(rs.getDouble(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ce;
    }
}
