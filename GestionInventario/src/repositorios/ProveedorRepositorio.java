package repositorios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import classes.Proveedor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProveedorRepositorio {
    private static ArrayList<Proveedor> proveedores = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/proveedores.json";
    private static final Gson gson = new GsonBuilder().create();

    static {
        verificarOCrearCarpetaData();
        cargarProveedoresDesdeJSON();
        if (proveedores.isEmpty()) {
            // Agregar datos de prueba si es necesario
            crearProveedor(new Proveedor(1, "Proveedor 1", "Direccion 1", "123456789"));
            crearProveedor(new Proveedor(2, "Proveedor 2", "Direccion 2", "987654321"));
        }
    }

    public static void crearProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
        guardarProveedoresEnJSON();
    }

    public static void modificarProveedor(int id, Proveedor proveedorModificado) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                proveedor.setNombre(proveedorModificado.getNombre());
                proveedor.setDireccion(proveedorModificado.getDireccion());
                proveedor.setTelefono(proveedorModificado.getTelefono());
                guardarProveedoresEnJSON();
                break;
            }
        }
    }

    public static Proveedor obtenerProveedorPorId(int id) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null;
    }

    public static Proveedor obtenerProveedorPorNombre(String nombre) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombre().equalsIgnoreCase(nombre)) {
                return proveedor;
            }
        }
        return null;
    }

    public static ArrayList<Proveedor> obtenerProveedores() {
        return new ArrayList<>(proveedores); // Retornar una copia para evitar modificaciones externas
    }

    public static void eliminarProveedor(int id) {
        proveedores.removeIf(proveedor -> proveedor.getId() == id);
        guardarProveedoresEnJSON();
    }

    private static void guardarProveedoresEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(proveedores, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar proveedores en JSON: " + e.getMessage());
        }
    }

    private static void cargarProveedoresDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            proveedores = new ArrayList<>(); // Inicializar con una lista vacía si el archivo no existe
            return;
        }

        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaProveedores = new TypeToken<ArrayList<Proveedor>>(){}.getType();
            proveedores = gson.fromJson(reader, tipoListaProveedores);
            if (proveedores == null) {
                proveedores = new ArrayList<>(); // Si el archivo estaba vacío, inicializar como lista vacía
            }
        } catch (IOException e) {
            System.out.println("Error al cargar proveedores desde JSON: " + e.getMessage());
            proveedores = new ArrayList<>();
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
