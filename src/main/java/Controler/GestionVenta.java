package Controler;

import Model.Venta;
import Model.Cliente;
import java.util.ArrayList;

public interface GestionVenta {

    void Registrar(Venta ve);
    
    void Actualizar(Venta ve, int id);

    void Eliminar(int id);

    ArrayList<Venta> Listar();
    
    ArrayList<Cliente> ListarCliente();
    
    Venta Buscar(int id);
}
