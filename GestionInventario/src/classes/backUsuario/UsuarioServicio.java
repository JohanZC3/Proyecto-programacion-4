package classes.backUsuario;

public class UsuarioServicio {
    public boolean validarUsuario(String email, String password) {
        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password) && usuario.isEstadoActivo()) {
            return true;
        }
        return false;
    }

    public static Boolean validarInformacion(String nombre, String apellido, String numeroDocumento, String direccion, String telefono, String password, String tipoUsuario) {
        if (nombre.isEmpty() || apellido.isEmpty() || numeroDocumento.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || password.isEmpty() || tipoUsuario.isEmpty()) {
            return false;
        }
        return true;
    }
}
