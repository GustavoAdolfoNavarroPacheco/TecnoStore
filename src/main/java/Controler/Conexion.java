package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection Conexion() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecnostore_db", "root", "29112007");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}