package classes.backProducto;

import java.time.LocalDate;

public class Producto {
    // Id, nombre, categoría, cantidad, precio unitario, fecha de expiración y proveedor
    private int id;
    private String nombre;
    private int categoria;
    private int cantidad;
    private int minima;
    private int maxima;
    private double precioUnitario;
    private LocalDate fechaExpiracion;
    private int proveedorId;  // Agregado para vinculación con proveedor

    public Producto(int id, String nombre, int categoria, int cantidad, int minima, int maxima, double precioUnitario, LocalDate fechaExpiracion, int proveedorId) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaExpiracion = fechaExpiracion;
        this.proveedorId = proveedorId; // Inicialización del proveedor
        this.minima = minima;
        this.maxima = maxima;
    }

    // Getters y Setters

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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
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

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }
}
