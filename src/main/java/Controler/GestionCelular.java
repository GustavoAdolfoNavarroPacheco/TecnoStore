package Controler;

import Model.Celular;
import Model.Marca;
import java.util.ArrayList;

public interface GestionCelular {

    void Registrar(Celular ce);

    void Actualizar(Celular ce, int id);

    void Eliminar(int id);

    ArrayList<Celular> Listar();
    
    ArrayList<Marca> ListarMarca();

    Celular Buscar(int id);
}
