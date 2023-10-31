import java.util.ArrayList;

class Administrador {
    //defines
    private static final int ERROR = 1;
    private static final int DONE = 0;
    final static int CUENTA_AHORRO = 1, CUENTA_CORRIENTE = 2, LLENO = -1, NOTFOUND = -1;
    int cuentasMaximas;
    ArrayList<Cuenta> db;
    GestorRecuperacionYGuardado gryg;
    public Administrador() {
        db = new ArrayList<Cuenta>();
        gryg = new GestorRecuperacionYGuardado(db);
        gryg.recuperarCuentas();
    };

    //LLAMAR AL CERRAR PROGRAMA !!!!ESTO EN C++ NO PASAAAAAAAA!!!!!!!!
    public void close() {
        gryg.saveCuentas();
    }

    public int altaCuenta(Cuenta cuenta) {
        if(buscarCuentaPorNumeroCuenta(cuenta.getNumCuenta())!="-1") {
            db.add(cuenta);
            return DONE;
        }
        return ERROR;
    }

    public int eliminarCuentaPorNombre(String nombre) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            db.remove(cuenta);
            return DONE;
        }
        return ERROR;
    }
    //Aca puedes declarar un cuenta_ahooro o cuenta_corriente lo pasas y ya estufas
    public int updateCuentaPorNombre(String nombre, Cuenta newData) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            db.add(newData);
            db.remove(cuenta);
            return DONE;
        }
        return ERROR;
    }

    //No uses estas usa las de Reportes uwu
    public String buscarCuentaPorNombre(String nombre) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            return cuenta.toString();
        }
        return "-1";
    }

    public String buscarCuentaPorNumeroCuenta(String nCuenta) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNumCuenta(nCuenta))) {
            return cuenta.toString();
        }
        return "-1";
    }

    public Cuenta getCuentaPorNombre(String nombre) {
        for (Cuenta cuenta : db) {
            if(cuenta.getNombreCliente().equals(nombre)) {
                return cuenta;
            }
        }
        return null;
    }
    public Cuenta getCuentaPorNumCuenta(String num) {
        for (Cuenta cuenta : db) {
            if(cuenta.getNumCuenta().equals(num)) {
                return cuenta;
            }
        }
        return null;
    }

    public ArrayList<Cuenta> getSubArrayCuentaAhorro() {
        ArrayList<Cuenta> subarray = new ArrayList<Cuenta>();
        for(Cuenta cuenta : db) {
            if(cuenta instanceof Cuenta_ahorro) {
                subarray.add(cuenta);
            }
        }
        return subarray;
    }

    public ArrayList<Cuenta> getSubArrayCuentaCorriente() {
        ArrayList<Cuenta> subarray = new ArrayList<Cuenta>();
        for(Cuenta cuenta : db) {
            if(cuenta instanceof Cuenta_corriente) {
                subarray.add(cuenta);
            }
        }
        return subarray;
    }
    private boolean isNull(Object obj) {
        return obj == null;
    }
}
