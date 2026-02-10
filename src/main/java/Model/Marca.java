package Model;

import java.util.ArrayList;
import java.util.List;

public class Marca {
    private int id;
    private String nombre;

    public static List<Marca> listaMarcas = new ArrayList<>();
    
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Marca(int id) {
        this.id = id;
        this.nombre = nombre;
    }

    public Marca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return """
               Id:                   %s
               Nombre:               %s
               """.formatted(id, nombre);
    }
}