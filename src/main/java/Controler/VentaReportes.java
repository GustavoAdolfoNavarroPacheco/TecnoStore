package Controler;

import Model.DetalleVenta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class VentaReportes implements GestionReporte {

    @Override
    public void GenerarReporte(ArrayList<DetalleVenta> Ventas) {
        JFrame frameTemporal = new JFrame();
        frameTemporal.setAlwaysOnTop(true);
        frameTemporal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione Nombre y Ubicacion del Archivo");

        int resultado = selector.showSaveDialog(frameTemporal);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            String ruta = selector.getSelectedFile().getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".txt")) {
                ruta += ".txt";
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
                bw.write("=          REPORTE DE VENTAS          =");

                for (DetalleVenta dv : Ventas) {
                    String reporte = """
                                     ========================================
                                     Venta ID     : %s
                                     Fecha        : %s
                                     Cliente      : %s
                                     Total Venta  : %s
        
                                     ID Detalle   : %s
                                     Cantidad     : %s
                                     Subtotal     : %s
        
                                     Celular ID   : %d
                                     Marca        : %s
                                     Modelo       : %s
                                     S. Operativo : %s
                                     Gama         : %s
                                     Precio       : %s
                                     Stock        : %s
                                     ========================================
        """.formatted(
                            dv.getId_venta().getId(),
                            dv.getId_venta().getFecha(),
                            dv.getId_venta().getId_Cliente().getNombre(),
                            dv.getId_venta().getTotal(),
                            dv.getId(),
                            dv.getCantidad(),
                            dv.getSubtotal(),
                            dv.getId_celular().getId(),
                            dv.getId_celular().getId_marca().getNombre(),
                            dv.getId_celular().getModelo(),
                            dv.getId_celular().getSistema_operativo(),
                            dv.getId_celular().getGama(),
                            dv.getId_celular().getPrecio(),
                            dv.getId_celular().getStock()
                    );
                    bw.write(reporte);
                }

                JOptionPane.showMessageDialog(frameTemporal, "Reporte Guardado!.");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(frameTemporal, "Error: " + e.getMessage());
            }
        }

        frameTemporal.dispose();
    }
}
