package servicios;

import classes.Usuario;
import repositorios.UsuarioRepositorio;

public class UsuarioServicio {
    public boolean validarUsuario(String email, String password) {
        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
