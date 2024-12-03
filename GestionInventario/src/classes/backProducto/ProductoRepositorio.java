package classes.backProducto;

import classes.LocalDateAdapter;
import classes.backCategoria.CategoryRepository;
import classes.backHistorial.Historial;
import classes.backHistorial.HistorialRepository;
import classes.backHistorial.HistorialService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductoRepositorio {
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/productos.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    static {
        verificarOCrearCarpetaData();
        cargarProductosDesdeJSON();
        if (productos.isEmpty()) {
            // Agregar datos de prueba
            crearProducto(new Producto(1, "Producto 1", 1, 10, 5, 50, 100.0, LocalDate.of(2024, 12, 31),1));
            crearProducto(new Producto(2, "Producto 2", 2, 20, 10, 100, 150.0, LocalDate.of(2024, 12, 31),2));
        }
    }

    public static void crearProducto(Producto producto) {
        productos.add(producto);
        guardarProductosEnJSON();
    }

    public static ArrayList<Producto> obtenerProductos() {
        return new ArrayList<>(productos);
    }

    public static Producto obtenerProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public static Producto obtenerProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public static int obtenerIdPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto.getId();
            }
        }
        return -1; // Return -1 if no product is found with the given name
    }

    public static void modificarProducto(int id, Producto productoModificado) {
    for (Producto producto : productos) {
        if (producto.getId() == id) {
            producto.setNombre(productoModificado.getNombre());
            producto.setCategoria(productoModificado.getCategoria());
            producto.setCantidad(productoModificado.getCantidad());
            producto.setPrecioUnitario(productoModificado.getPrecioUnitario());
            producto.setFechaExpiracion(productoModificado.getFechaExpiracion());
            producto.setProveedorId(productoModificado.getProveedorId());
            producto.setMinima(productoModificado.getMinima());
            guardarProductosEnJSON();
            break;
        }
    }
    }

    public static void guardarProductosEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(productos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar productos en JSON: " + e.getMessage());
        }
    }

    private static void cargarProductosDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            productos = new ArrayList<>();
            return;
        }

        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaProductos = new TypeToken<ArrayList<Producto>>(){}.getType();
            productos = gson.fromJson(reader, tipoListaProductos);
            if (productos == null) {
                productos = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos desde JSON: " + e.getMessage());
            productos = new ArrayList<>();
        }
    }

    public static void eliminarProducto(int id) {
        productos.removeIf(producto -> producto.getId() == id);
        guardarProductosEnJSON();
    }

    // Método para verificar y crear la carpeta "Data" si no existe
    private static void verificarOCrearCarpetaData() {
        File carpetaData = new File(DATA_FOLDER);
        if (!carpetaData.exists()) {
            carpetaData.mkdir();
            System.out.println("Carpeta 'Data' creada.");
        }
    }

    public static List<Producto> obtenerProductosFiltrados(String criterio) {
    List<Producto> productosFiltrados = new ArrayList<>();
    criterio = criterio.toLowerCase();
    for (Producto producto : productos) {
        if (producto.getNombre().toLowerCase().contains(criterio) || 
            CategoryRepository.obtenerCategoryPorId(producto.getCategoria()).getNombre().toLowerCase().contains(criterio)) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
    }

    public static List<Producto> obtenerProductosPorCategoryId(int CategoryId) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria() == CategoryId) 
            {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
        }

    public static void actualizarVentaCantidadProducto(int id, int cantidad, int userId) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                if (producto.getCantidad() < cantidad) {
                    System.out.println("No hay suficiente cantidad de " + producto.getNombre());
                    return;
                }else{
                    producto.setCantidad(producto.getCantidad() - cantidad);
                    guardarProductosEnJSON();
                    int idHistorial = HistorialService.loadHistorialId();
                    Historial historial = new Historial(idHistorial, userId, "Venta", LocalDate.now(), id, "venta de " + cantidad + " unidades del producto " + producto.getNombre(), "Productos");
                    HistorialRepository.crearHistorial(historial);
                    HistorialService.actualizarIds();
                    break;
                }
            }
        }
    }

    public static void actualizarCompraCantidadProducto(int id, int cantidad, int userId) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                if (producto.getCantidad() + cantidad == producto.getMaxima()) {
                    System.out.println("la compra sobrepasa el limite de  " + producto.getMaxima() + " para el producto " + producto.getNombre());
                    return;
                }else{
                    producto.setCantidad(producto.getCantidad() + cantidad);
                    guardarProductosEnJSON();
                    int idHistorial = HistorialService.loadHistorialId();
                    Historial historial = new Historial(idHistorial, userId, "Compra", LocalDate.now(), id, "compra de " + cantidad + " unidades del producto " + producto.getNombre(), "Productos");
                    HistorialRepository.crearHistorial(historial);
                    HistorialService.actualizarIds();
                    break;
                }
            }
        }
    }

    public static void exportarProductosACSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV", "csv"));
        
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            
            if (!filePath.endsWith(".csv")) {
                filePath += ".csv";
            }
            
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.append("Nombre del Producto,Cantidad,Precio,Costo Total\n");
                for (Producto producto : productos) {
                    double costoTotal = producto.getCantidad() * producto.getPrecioUnitario();
                    writer.append(producto.getNombre())
                          .append(",")
                          .append(String.valueOf(producto.getCantidad()))
                          .append(",")
                          .append(String.valueOf(producto.getPrecioUnitario()))
                          .append(",")
                          .append(String.valueOf(costoTotal))
                          .append("\n");
                }
                System.out.println("Archivo CSV guardado en: " + filePath);
            } catch (IOException e) {
                System.out.println("Error al guardar archivo CSV: " + e.getMessage());
            }
        }
    }

}
