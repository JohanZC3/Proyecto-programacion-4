package servicios;

import java.time.LocalDate;

import classes.Producto;
import repositorios.ProductoRepositorio;

public class ProductoServicio {
    public void creacionProducto (int idString, String productName, String productCategory, int amoundint, int price, int year, int month, int day, int idProveedor) {
        ProductoRepositorio.crearProducto(new Producto(idString, productName, productCategory, amoundint, price, LocalDate.of(year,month,day), idProveedor));
    }
}
