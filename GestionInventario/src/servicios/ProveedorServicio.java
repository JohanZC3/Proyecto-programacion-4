package servicios;

public class ProveedorServicio {
    public static boolean validarInformacion(int id, String nombre, String direccion, String telefono) {
        if (id <= 0) {
            return false;
        }
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }
        if (direccion == null || direccion.isEmpty()) {
            return false;
        }
        if (telefono == null || telefono.isEmpty()) {
            return false;
        }
        return true;
    }
}
