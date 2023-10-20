abstract class Cuenta {
    private String num_cuenta, nombre_cliente;
    float saldo;
    int lastLoginMount = 0;

    public Cuenta(String num_cuenta, String nombre_cliente, float saldo) {
        this.nombre_cliente = nombre_cliente;
        this.num_cuenta = num_cuenta;
        this.saldo = saldo;
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

    public void setLastLoginMount(int llm) {
        lastLoginMount = llm;
    }

    public void aplicarCargosEIntereses() {
        comisiones();
        intereses();
    }

    public String cargarSaldo(float saldo) {
        this.saldo-=saldo;
        return "JESUUUUUUUUUUUUUSSS";
    }

    public String abonarSaldo(float saldo) {
        this.saldo+=saldo;
        return "puto :3";
    }

    public abstract String formatToSave();
    public abstract String info();
    public abstract String comisiones();
    public abstract String intereses();
}
