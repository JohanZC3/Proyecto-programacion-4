package classes.caja;

public class Caja {
    private int id;
    private String operacion;
    private int producto;
    private int cantidad;
    private String estado;

    public Caja(int id, String operacion, int producto, int cantidad, String estado) {
        this.id = id;
        this.operacion = operacion;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getOperacion() {
        return operacion;
    }

    public int getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
