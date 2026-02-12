package Model;

public class DetalleVenta {
    private int id;
    private int cantidad;
    private double subtotal;
    private Celular id_celular;
    private Venta id_venta;
    

    public DetalleVenta(int id, int cantidad, double subtotal, Celular id_celular, Venta id_venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.id_celular = id_celular;
        this.id_venta = id_venta;
        
    }

    public DetalleVenta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Celular getId_celular() {
        return id_celular;
    }

    public void setId_celular(Celular id_celular) {
        this.id_celular = id_celular;
    }
    
    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }
    
    @Override
    public String toString() {
        return """
                Id:                   %s
                Cantidad:             %s
                Subtotal:             %s
                ID Venta:             %s
                Celular:              %s
               """.formatted(id, cantidad, subtotal,  id_venta != null ? id_venta.getId() : "N/A", id_celular != null ? id_celular.getModelo() : "N/A");
    }

}