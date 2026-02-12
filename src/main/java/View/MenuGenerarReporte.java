package View;

import Controler.GestionarDetalleVenta_Implement;
import Controler.GestionDetalleVenta;
import Controler.GestionReporte;
import Controler.VentaReportes;
import Model.DetalleVenta;
import java.util.ArrayList;

public class MenuGenerarReporte {

    GestionDetalleVenta Gdv = new GestionarDetalleVenta_Implement();

    public void MenuGenerarReporte() {
        ArrayList<DetalleVenta> Ventas = Gdv.ListarRdv();

        if (!Ventas.isEmpty()) {
            System.out.println("Generando Reporte...");
            GestionReporte reporte = new VentaReportes();
            reporte.GenerarReporte(Ventas);
        } else {
            System.out.println("Ventas insuficientes para generacion de reporte!");
        }
    }
}
