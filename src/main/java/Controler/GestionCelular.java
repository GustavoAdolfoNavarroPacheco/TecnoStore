package Controler;

import Model.Celular;
import java.util.ArrayList;

public interface GestionCelular {

    void Guardar(Celular ce);

    void Actualizar(Celular ce, int id);

    void Eliminar(int id);

    ArrayList<Celular> Listar();

    Celular Buscar(int id);
}
