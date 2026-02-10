package Model;

public class Celular {
    private int id;
    private String modelo;
    private String sistema_operativo;
    public Gama gama;
    private int stock;
    private double precio;
    private Marca id_marca;

    public Celular(int id, String modelo, String sistema_operativo, Gama gama, int stock, double precio, Marca id_marca) {
        this.id = id;
        this.modelo = modelo;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
        this.stock = stock;
        this.precio = precio;
        this.id_marca = id_marca;
    }

    public Celular() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Marca getId_marca() {
        return id_marca;
    }

    public void setId_marca(Marca id_marca) {
        this.id_marca = id_marca;
    }
    
    @Override
    public String toString() {
        return """
                Id:                  %s
                Modelo:              %s
                Sistema Operativo:   %s
                Gama:                %s
                Stock:               %s
                Precio:              %s
                Marca                %s
               """.formatted(id, modelo, sistema_operativo, gama, stock, precio, id_marca);
    }
}