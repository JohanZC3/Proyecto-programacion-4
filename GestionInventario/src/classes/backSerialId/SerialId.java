package classes.backSerialId;

public class SerialId {
    private int id;
    private int lastidUsuario;
    private int lastidProducto;
    private int lastidProveedor;
    private int lastidHistorial;
    private int lastidCategory;

    public SerialId(int id, int lastidUsuario, int lastidProducto, int lastidProveedor, int lastidHistorial, int lastidCategory) {
        this.lastidUsuario = lastidUsuario;
        this.lastidProducto = lastidProducto;
        this.lastidProveedor = lastidProveedor;
        this.lastidHistorial = lastidHistorial;
        this.lastidCategory = lastidCategory;
        this.id = id;
    }

    

    public int getLastidUsuario() {
        return lastidUsuario;
    }

    public void setLastidUsuario(int lastidUsuario) {
        this.lastidUsuario = lastidUsuario;
    }

    public int getLastidProducto() {
        return lastidProducto;
    }

    public void setLastidProducto(int lastidProducto) {
        this.lastidProducto = lastidProducto;
    }

    public int getLastidProveedor() {
        return lastidProveedor;
    }

    public void setLastidProveedor(int lastidProveedor) {
        this.lastidProveedor = lastidProveedor;
    }

    public int getLastidHistorial() {
        return lastidHistorial;
    }

    public void setLastidHistorial(int lastidHistorial) {
        this.lastidHistorial = lastidHistorial;
    }

    public int getLastidCategory() {
        return lastidCategory;
    }

    public void setLastidCategory(int lastidCategory) {
        this.lastidCategory = lastidCategory;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }
}
