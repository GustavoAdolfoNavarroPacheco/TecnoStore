package Controler;

import Model.Cliente;
import java.util.ArrayList;

public interface GestionCliente {

    void Registrar(Cliente cl);
    
    void Actualizar(Cliente cl, int id);

    void Eliminar(int id);

    ArrayList<Cliente> Listar();
    
    boolean VerificarID(String identificacion);

    Cliente Buscar(int id);
}
