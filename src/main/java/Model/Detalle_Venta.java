package Model;

public class Detalle_Venta {
    private int id;
    private String cantidad;
    private String subtotal;
    private Venta id_venta;
    private Celular id_celular;

    public Detalle_Venta(int id, String cantidad, String subtotal, Venta id_venta, Celular id_celular) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.id_venta = id_venta;
        this.id_celular = id_celular;
    }

    public Detalle_Venta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }

    public Celular getId_celular() {
        return id_celular;
    }

    public void setId_celular(Celular id_celular) {
        this.id_celular = id_celular;
    }
    
    @Override
    public String toString() {
        return """
                Id:                   %s
                Nombre:               %s
                Identificacion:       %s
                Correo:               %s
                Celular:              %s
               """.formatted(id, cantidad, subtotal, id_venta, id_celular);
    }
}