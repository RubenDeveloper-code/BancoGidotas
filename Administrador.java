import java.util.ArrayList;

class Administrador {
    //defines
    private static final int ERROR = 1;
    private static final int DONE = 0;
    final static int CUENTA_AHORRO = 1, CUENTA_CORRIENTE = 2, LLENO = -1, NOTFOUND = -1;
    int cuentasMaximas;
    ArrayList<Cuenta> db;
    public Administrador() {
        db = new ArrayList<Cuenta>();
    };
    //Gestion cuentas;

    public int altaCuenta(String num_cuenta, String nombre_cliente, int comision) {
        db.add(new Cuenta_ahorro(num_cuenta, nombre_cliente,comision));
        return DONE;
    }

    public int altaCuenta(String num_cuenta, String nombre_cliente, int transacciones, int importetransacciones) {
        db.add(new Cuenta_corriente(num_cuenta, nombre_cliente,transacciones,importetransacciones ));
        return DONE;
    }

    public int eliminarCuentaPorNombre(String nombre) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            db.remove(cuenta);
            return DONE;
        }
        return ERROR;
    }

    public int updateCuentaPorNombre(String nombre, Cuenta newData) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            db.add(newData);
            newData.setNombreCliente(cuenta.getNombreCliente());
            newData.setNumCuenta(cuenta.getNumCuenta());
            db.remove(cuenta);
            return DONE;
        }
        return ERROR;
    }

    public String buscarCuentaPorNombre(String nombre) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nombre))) {
            return cuenta.toString();
        }
        return "-1";
    }

    public String buscarCuentaPorNCuenta(String nCuenta) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNombre(nCuenta))) {
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

    public ArrayList<Cuenta_ahorro> getSubArrayCuentaAhorro() {
        ArrayList<Cuenta_ahorro> subarray = new ArrayList<Cuenta_ahorro>();
        for(Cuenta cuenta : db) {
            if(cuenta instanceof Cuenta_ahorro) {
                subarray.add((Cuenta_ahorro)cuenta);
            }
        }
        return subarray;
    }

    public ArrayList<Cuenta_corriente> getSubArrayCuentaCorriente() {
        ArrayList<Cuenta_corriente> subarray = new ArrayList<Cuenta_corriente>();
        for(Cuenta cuenta : db) {
            if(cuenta instanceof Cuenta_corriente) {
                subarray.add((Cuenta_corriente)cuenta);
            }
        }
        return subarray;
    }
    private boolean isNull(Object obj) {
        return obj == null;
    }
}
