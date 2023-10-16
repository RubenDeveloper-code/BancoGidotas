import java.util.ArrayList;
//Carhos abonos saldo_cuenta
class Movimientos {
    private static final int ERROR = 1;
    private static final int DONE = 0;
    ArrayList<Cuenta> db;
    Administrador admin;
    Movimientos(ArrayList<Cuenta> db, Administrador admin) {
        this.db = db;
        this.admin = admin;
    }

    public int cargar(String nombre, int cargos) {
        Cuenta cuenta;
        if(!isNull(cuenta = admin.getCuentaPorNombre(nombre))) {
            cuenta.cargarSaldo(cargos);
            return DONE;
        }
        return ERROR;
    }

    public int abonar(String nombre, int abono) {
        Cuenta cuenta;
        if(!isNull(cuenta = admin.getCuentaPorNombre(nombre))) {
            cuenta.abonarSaldo(abono);
            return DONE;
        }
        return ERROR;
    }

    public float saldo(String nombre) {
        Cuenta cuenta;
        if(!isNull(cuenta = admin.getCuentaPorNombre(nombre))) {
            return cuenta.getSaldo();
        }
        return ERROR;
    }

    public int cargar(Cuenta cuenta, int cargos) {
        cuenta.cargarSaldo(cargos);
        return DONE;
    }
    private boolean isNull(Object obj) {
        return obj == null;
    }
}
