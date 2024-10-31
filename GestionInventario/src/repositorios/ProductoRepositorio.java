package repositorios;

import classes.LocalDateAdapter;
import classes.Producto;

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
            crearProducto(new Producto(1, "Producto 1", "Categoria 1", 10, 100.0, LocalDate.of(2024, 12, 31),1));
            crearProducto(new Producto(2, "Producto 2", "Categoria 2", 20, 150.0, LocalDate.of(2024, 12, 31),2));
        }
    }

    public static void crearProducto(Producto producto) {
        productos.add(producto);
        guardarProductosEnJSON();
    }

    public static ArrayList<Producto> obtenerProductos() {
        return new ArrayList<>(productos);
    }

    private static void guardarProductosEnJSON() {
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

    // Método para verificar y crear la carpeta "Data" si no existe
    private static void verificarOCrearCarpetaData() {
        File carpetaData = new File(DATA_FOLDER);
        if (!carpetaData.exists()) {
            carpetaData.mkdir();
            System.out.println("Carpeta 'Data' creada.");
        }
    }
}
