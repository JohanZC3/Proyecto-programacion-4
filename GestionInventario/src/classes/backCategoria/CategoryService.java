package classes.backCategoria;

import java.time.LocalDate;
import java.util.List;

import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backProducto.Producto;
import classes.backProducto.ProductoRepositorio;
import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;

public class CategoryService {

    public static void categoryChange(int categoryId){
        List<Producto> productosFiltrados = ProductoRepositorio.obtenerProductosPorCategoryId(categoryId);
        if (productosFiltrados.size() > 0) {
            for (Producto producto : productosFiltrados) {
                producto.setCategoria(0);
                ProductoRepositorio.guardarProductosEnJSON();
            }            
            SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
            int lastId = serialId.getLastidHistorial();
            Historial historial = new Historial(lastId, "modificacion por eliminacion de categoria", LocalDate.now(), categoryId, "cambio de categoria "+ categoryId, "Categorias + productos (varios)");
            HistorialRepository.crearHistorial(historial);
        }
    }

}
