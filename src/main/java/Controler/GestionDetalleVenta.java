package Controler;

import Model.Celular;
import Model.Venta;
import Model.DetalleVenta;
import java.util.ArrayList;

public interface GestionDetalleVenta {

    void RegistrarDv(DetalleVenta Dv);
    
    void ActualizarDv(DetalleVenta Dv, int id);

    void EliminarDv(int id);

    ArrayList<DetalleVenta> ListarDv();
    
    ArrayList<Venta> ListarVenta();
    
    ArrayList<Celular> ListarCelular();
    
    DetalleVenta BuscarDv(int id);
    
    double SubtotalDv(int cantidad,double precio);
}
