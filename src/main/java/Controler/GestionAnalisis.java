package Controler;

import Model.Celular;
import Model.Venta;
import java.util.ArrayList;

public interface GestionAnalisis {

    ArrayList<Celular> StockBajo();
    
    ArrayList<Celular> MasVendido();
    
    ArrayList<Venta> VentasPorMes();
    
}
