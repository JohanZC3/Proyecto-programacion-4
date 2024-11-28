package classes.repositorios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import classes.Category;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoryRepository {

    private static ArrayList<Category> categories = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/categories.json";
    private static final Gson gson = new Gson();

    // Cargar categories desde el archivo JSON al iniciar y agregar datos de prueba si está vacío
    static {
        verificarOCrearCarpetaData();
        cargarCategoriesDesdeJSON();
        if (categories.isEmpty()) {
            // Agregar datos de prueba
            crearCategory(new Category(1, "Electrodomesticos", "Electrodomesticos para el hogar"));
            crearCategory(new Category(2, "Tecnologia", "Dispositivos tecnologicos"));
        }
    }

    public static void crearCategory(Category category) {
        categories.add(category);
        guardarCategoriesEnJSON();
    }

    public static ArrayList<Category> obtenerCategories() {
        return new ArrayList<>(categories);
    }

    public static Category obtenerCategoryPorId(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public static int obtenerIdPorNombre(String nombre) {
        for (Category category : categories) {
            if (category.getNombre().equalsIgnoreCase(nombre)) {
                return category.getId();
            }
        }
        return -1; // Return -1 if no category with the given name is found
    }

    public static void modificarCategory(int id, Category categoryModificado) {
        for (Category category : categories) {
            if (category.getId() == id) {
                category.setNombre(categoryModificado.getNombre());
                category.setDescripcion(categoryModificado.getDescripcion());
                guardarCategoriesEnJSON();
                break;
            }
        }
    }

    private static void guardarCategoriesEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(categories, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar categorias en JSON: " + e.getMessage());
        }
    }

    public static void eliminarCategory(int id) {
        categories.removeIf(category -> category.getId() == id);
        guardarCategoriesEnJSON();
    }

    private static void cargarCategoriesDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            categories = new ArrayList<>();
            return;
        }
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type listType = new TypeToken<ArrayList<Category>>() {
            }.getType();
            categories = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error al cargar categorias desde JSON: " + e.getMessage());
        }
    }

    private static void verificarOCrearCarpetaData() {
        File dataFolder = new File(DATA_FOLDER);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

}
