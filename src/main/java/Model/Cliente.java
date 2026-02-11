package Model;

public class Cliente {
    private int id;
    private String nombre;
    private int identificacion;
    private String correo;
    private String telefono;

    public Cliente(int id, String nombre, int identificacion, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente() {
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

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return """
                  Id:                   %s
                  Nombre:               %s
                  Identificacion:       %s
                  Correo:               %s
                  Celular:              %s
               """.formatted(id, nombre, identificacion, correo, telefono);
    }
}