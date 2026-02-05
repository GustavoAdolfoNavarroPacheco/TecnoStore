package Model;

public class Cliente {
    private int id;
    private String nombre;
    private String identificacion;
    private String correo;
    private String celular;

    public Cliente(int id, String nombre, String identificacion, String correo, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.celular = celular;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    @Override
    public String toString() {
        return """
                Id:                   %s
                Nombre:               %s
                Identificacion:       %s
                Correo:               %s
                Celular:              %s
               """.formatted(id, nombre, identificacion, correo, celular);
    }
}