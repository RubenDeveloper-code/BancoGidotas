abstract class Cuenta {
    private String num_cuenta, nombre_cliente;
    float saldo;

    public Cuenta(String num_cuenta, String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
        this.num_cuenta = num_cuenta;
    }

    public void setNumCuenta(String val) {
        num_cuenta = val;
    }
    public void setNombreCliente(String val) {
        nombre_cliente = val;
    }
    public void setSaldo(float val) {
        saldo = val;
    }

    public String getNumCuenta() {
        return num_cuenta;
    }
    public String getNombreCliente() {
        return nombre_cliente;
    }
    public float getSaldo() {
        return saldo;
    }

    public String cargarSaldo(float saldo) {
        this.saldo-=saldo;
        return "JESUUUUUUUUUUUUUSSS";
    }

    public String abonarSaldo(float saldo) {
        this.saldo+=saldo;
        return "puto :3";
    }

    public abstract String comisiones();
    public abstract String intereses();
}
