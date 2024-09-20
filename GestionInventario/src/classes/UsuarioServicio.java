package classes;

public class UsuarioServicio {
    private UsuarioRepositorio repositorio;

    public UsuarioServicio() {
        this.repositorio = new UsuarioRepositorio();
    }

    public boolean validarUsuario(String email, String password) {
        Usuario usuario = UsuarioRepositorio.obtenerUsuarioPorEmail(email);
        if (usuario != null && usuario.getNumeroDocumento().equals(password)) {
            return true;
        }
        return false;
    }
}
