package classes.backUsuario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsuarioRepositorio {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static final String DATA_FOLDER = "GestionInventario/Data";
    private static final String ARCHIVO_JSON = DATA_FOLDER + "/usuarios.json";
    private static final Gson gson = new Gson();

    // Cargar usuarios desde el archivo JSON al iniciar y agregar datos de prueba si está vacío
    static {
        verificarOCrearCarpetaData();
        cargarUsuariosDesdeJSON();
        if (usuarios.isEmpty()) {
            // Agregar datos de prueba
            crearUsuario(new Usuario(1, "Juan", "Perez", "CC", "12345", "juan@example.com", "3118776765", true, "administrador" ,"pJuan123"));
            crearUsuario(new Usuario(2, "Maria", "Lopez", "CC", "67890", "maria@example.com", "3213322232", true, "auxiliar","pMaria456"));
        }
    }

    public static void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuariosEnJSON();
    }

    public static void modificarUsuario(int id, Usuario usuarioModificado) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setNombre(usuarioModificado.getNombre());
                usuario.setApellido(usuarioModificado.getApellido());
                usuario.setTipoDocumento(usuarioModificado.getTipoDocumento());
                usuario.setNumeroDocumento(usuarioModificado.getNumeroDocumento());
                usuario.setDireccion(usuarioModificado.getDireccion());
                usuario.setTelefono(usuarioModificado.getTelefono());
                usuario.setEstadoActivo(usuarioModificado.isEstadoActivo());
                usuario.setTipoUsuario(usuarioModificado.getTipoUsuario());
                usuario.setPassword(usuarioModificado.getPassword());
                guardarUsuariosEnJSON();
                break;
            }
        }
    }

    public static Usuario obtenerUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDireccion().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static ArrayList<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public static void activarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setEstadoActivo(true);
                guardarUsuariosEnJSON();
                break;
            }
        }
    }

    public static void inactivarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setEstadoActivo(false);
                guardarUsuariosEnJSON();
                break;
            }
        }
    }

    private static void guardarUsuariosEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios en JSON: " + e.getMessage());
        }
    }

    private static void cargarUsuariosDesdeJSON() {
        File archivo = new File(ARCHIVO_JSON);
        if (!archivo.exists()) {
            usuarios = new ArrayList<>();
            return;
        }

        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            Type tipoListaUsuarios = new TypeToken<ArrayList<Usuario>>(){}.getType();
            usuarios = gson.fromJson(reader, tipoListaUsuarios);
            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios desde JSON: " + e.getMessage());
            usuarios = new ArrayList<>();
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
