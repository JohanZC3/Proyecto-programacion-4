package classes.caja;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class CajaRepositorio {
    private static ArrayList<Caja> pedidos = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/pedidos.json";
    private static final Gson gson = new Gson();

    static {
        verificarOCrearCarpetaData();
        cargarPedidosDesdeJSON();
    }

    public static void crearPedido(Caja pedido) {
        pedidos.add(pedido);
        guardarpedidosEnJSON();
    }

    public static ArrayList<Caja> obtenerPedidos() {
        return new ArrayList<>(pedidos);
    }

    public static void guardarpedidosEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios en JSON: " + e.getMessage());
        }
    }


    private static void cargarPedidosDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            pedidos = new ArrayList<>();
            return;
        }
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaPedidos = new TypeToken<ArrayList<Caja>>() {
            }.getType();
            pedidos = gson.fromJson(reader, tipoListaPedidos);
        } catch (IOException e) {
            System.out.println("Error al cargar pedidos desde JSON: " + e.getMessage());
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
