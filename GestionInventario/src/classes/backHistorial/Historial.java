package classes.backHistorial;

import java.time.LocalDate;

public class Historial {
    private int id;
    private String accion;
    private LocalDate fecha;
    private int idAfectado;
    private String tabla;
    private String razon;
    private int usuario;

    public Historial(int id, int usuario, String accion, LocalDate fecha, int idAfectado, String razon, String tabla) {
        this.id = id;
        this.usuario = usuario;
        this.accion = accion;
        this.fecha = fecha;
        this.idAfectado = idAfectado;
        this.razon = razon;
        this.tabla = tabla;
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

    public int getIdAfectado() {
        return idAfectado;
    }

    public void setIdAfectado(int idAfectado) {
        this.idAfectado = idAfectado;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
}
