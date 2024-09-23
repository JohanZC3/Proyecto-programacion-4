package classes;

import java.util.ArrayList;

public class UsuarioRepositorio {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    static {
        crearUsuario(new Usuario(1, "Juan", "Perez", "CC", "12345", "juan@example.com", "3118776765", true, "pJuan123"));
        crearUsuario(new Usuario(2, "Maria", "Lopez", "CC", "67890", "maria@example.com", "3213322232", true, "pMaria456"));
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
                usuario.setPassword(usuarioModificado.getPassword());
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
            }
        }
    }

    public static void inactivarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuario.setEstadoActivo(false);
            }
        }
    }
}

