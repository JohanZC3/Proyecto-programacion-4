package classes;

import java.time.LocalDate;

public class Producto {
    // Id, nombre, categoría, cantidad, precio unitario, fecha de expiración y proveedor
    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precioUnitario;
    private LocalDate fechaExpiracion;
    private int proveedorId;  // Agregado para vinculación con proveedor

    public Producto(int id, String nombre, String categoria, int cantidad, double precioUnitario, LocalDate fechaExpiracion, int proveedorId) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaExpiracion = fechaExpiracion;
        this.proveedorId = proveedorId; // Inicialización del proveedor
    }

    // Getters y Setters

    public Producto(String idString, String productName, String productCategory, int amoundint, double price) {
        //TODO Auto-generated constructor stub
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getProveedorId() {
        return proveedorId;  // Getter para el proveedor
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;  // Setter para el proveedor
    }
}
