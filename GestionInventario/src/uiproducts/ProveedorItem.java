package uiproducts;

class ProveedorItem {
    private int id;
    private String nombre;

    public ProveedorItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Solo muestra el nombre en el JComboBox
    }
}
