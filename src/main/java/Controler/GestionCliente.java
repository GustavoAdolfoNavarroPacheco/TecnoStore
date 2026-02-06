package Controler;

import Model.Cliente;
import java.util.ArrayList;

public interface GestionCliente {

    void Guardar(Cliente cl);

    void Actualizar(Cliente cl, int id);

    void Eliminar(int id);

    ArrayList<Cliente> Listar();

    Cliente Buscar(int id);
}