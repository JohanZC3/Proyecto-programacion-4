package classes;

import java.util.ArrayList;

public class UsuarioRepositorio {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
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

