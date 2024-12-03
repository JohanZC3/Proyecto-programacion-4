package classes.backSerialId;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SerialIdRepository {

    private static ArrayList<SerialId> serialIds = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/SerialIds.json";
    private static final Gson gson = new Gson();

    // Cargar serialIds desde el archivo JSON al iniciar y agregar datos de prueba si está vacío
    static {
        verificarOCrearCarpetaData();
        cargarSerialIdsDesdeJSON();
        if (serialIds.isEmpty()) {
            // Agregar datos de prueba
            crearSerialId(new SerialId(1, 2, 2, 2, 2, 2, 0));
        }
    }

    public static void crearSerialId(SerialId serialId) {
        serialIds.add(serialId);
        guardarSerialIdsEnJSON();
    }

    public static SerialId obtenerSerialIdPorId(int id) {
        for (SerialId serialId : serialIds) {
            if (serialId.getId() == id) {
                return serialId;
            }
        }
        return null; // or throw an exception if preferred
    }

    public static ArrayList<SerialId> obtenerSerialIds() {
        return new ArrayList<>(serialIds);
    }

    public static void modificarSerialId(SerialId serialIdModificado) {
        for (SerialId serialId : serialIds) {
            serialId.setLastidUsuario(serialIdModificado.getLastidUsuario());
            serialId.setLastidProducto(serialIdModificado.getLastidProducto());
            serialId.setLastidProveedor(serialIdModificado.getLastidProveedor());
            serialId.setLastidHistorial(serialIdModificado.getLastidHistorial());
            serialId.setLastidCategory(serialIdModificado.getLastidCategory());
        }
        guardarSerialIdsEnJSON();
    }

    private static void guardarSerialIdsEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(serialIds, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar productos en JSON: " + e.getMessage());
        }
    }

    private static void verificarOCrearCarpetaData() {
        File carpetaData = new File(DATA_FOLDER);
        if (!carpetaData.exists()) {
            carpetaData.mkdir();
            System.out.println("Carpeta 'Data' creada.");
        }
    }

    private static void cargarSerialIdsDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            serialIds = new ArrayList<>();
            return;
        }

        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaSerialIds = new TypeToken<ArrayList<SerialId>>(){}.getType();
            serialIds = gson.fromJson(reader, tipoListaSerialIds);
            if (serialIds == null) {
                serialIds = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos desde JSON: " + e.getMessage());
            serialIds = new ArrayList<>();
        }
    }

}
