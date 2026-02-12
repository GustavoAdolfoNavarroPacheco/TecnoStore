package Controler;

import Model.DetalleVenta;
import java.util.ArrayList;

public interface GestionReporte {
    void GenerarReporte(ArrayList<DetalleVenta> Ventas);
}