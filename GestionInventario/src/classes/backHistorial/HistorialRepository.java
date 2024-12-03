package classes.backHistorial;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import classes.LocalDateAdapter;

public class HistorialRepository {
    private static ArrayList<Historial> historiales = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/historiales.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
    
    static {
        verificarOCrearCarpetaData();
        cargarHistorialesDesdeJSON();
        if (historiales.isEmpty()) {
            // Agregar datos de prueba
            crearHistorial(new Historial(1,0, "Creacion", LocalDate.now(), 1, "Creación de producto 1", "Productos"));
            crearHistorial(new Historial(2,0, "Creacion", LocalDate.now(), 2, "Creacion de producto 2", "Productos"));
        }
    }

    public static void crearHistorial(Historial historial) {
        historiales.add(historial);
        guardarHistorialesEnJSON();
    }

    public static ArrayList<Historial> obtenerHistoriales() {
        return new ArrayList<>(historiales);
    }

    public static Historial obtenerHistorialPorId(int id) {
        for (Historial historial : historiales) {
            if (historial.getId() == id) {
                return historial;
            }
        }
        return null;
    }

    public static void modificarHistorial(int id, Historial historialModificado) {
        for (Historial historial : historiales) {
            if (historial.getId() == id) {
                historial.setAccion(historialModificado.getAccion());
                historial.setUsuario(historialModificado.getUsuario());
                historial.setFecha(historialModificado.getFecha());
                historial.setIdAfectado(historialModificado.getIdAfectado());
                historial.setRazon(historialModificado.getRazon());
                historial.setTabla(historialModificado.getTabla());
                guardarHistorialesEnJSON();
                return;
            }
        }
    }

    public static void eliminarHistorial(int id) {
        historiales.removeIf(historial -> historial.getId() == id);
        guardarHistorialesEnJSON();
    }

    private static void verificarOCrearCarpetaData() {
        File carpetaData = new File(DATA_FOLDER);
        if (!carpetaData.exists()) {
            carpetaData.mkdirs();
        }
    }

    private static void guardarHistorialesEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(historiales, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar historiales en JSON: " + e.getMessage());
        }
    }

    private static void cargarHistorialesDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            historiales = new ArrayList<>();
            return;
        }

        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaHistoriales = new TypeToken<ArrayList<Historial>>() {
            }.getType();
            historiales = gson.fromJson(reader, tipoListaHistoriales);
        } catch (IOException e) {
            System.out.println("Error al cargar historiales desde JSON: " + e.getMessage());
        }
    }
}
