package Model;

public class Venta {
    private int id;
    private String fecha;
    private double total;
    private Cliente id_cliente;

    public Venta(int id, String fecha, double total, Cliente id_Cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.id_cliente = id_Cliente;
    }

    public Venta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getId_Cliente() {
        return id_cliente;
    }

    public void setId_Cliente(Cliente id_Cliente) {
        this.id_cliente = id_Cliente;
    }

    @Override
    public String toString() {
        return """
                Id:                   %s
                Fecha:                %s
                Total:                %s
                Cliente:              %s
               """.formatted(id, fecha, total, id_cliente != null ? id_cliente.getId() : "N/A");
    }
}