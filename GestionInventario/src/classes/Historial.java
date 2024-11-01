package classes;

import java.time.LocalDate;

public class Historial {
    private int id;
    private String accion;
    private LocalDate fecha;
    private int idProducto;
    private String razon;

    public Historial(int id, String accion, LocalDate fecha, int idProducto, String razon) {
        this.id = id;
        this.accion = accion;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
    
}
