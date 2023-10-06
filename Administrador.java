class Administrador {
    Cuenta cuentas[];
    final static int CUENTA_AHORRO = 1, CUENTA_CORRIENTE = 2, LLENO = -1, NOTFOUND = -1;
    int cuentasMaximas;
    public Administrador() {
        cuentas = new Cuenta[cuentasMaximas = 20];
    };
    //Gestion cuentas;
    public String altaCuenta(int type, String num_cuenta, String nombre_cliente) {
        if(indexToAllocIn(cuentas) == LLENO) return "Ya no se pueden agregar mas cuentas";

        if(type == CUENTA_AHORRO)
            cuentas[indexToAllocIn(cuentas)] = new Cuenta_ahorro(num_cuenta,nombre_cliente,0/*numero*/);

        if(type == CUENTA_CORRIENTE)
            cuentas[indexToAllocIn(cuentas)] = new Cuenta_corriente(num_cuenta,nombre_cliente,0/*numero*/,0);

        return "Cuenta agregada!!";
    }

    public String actualizarCuenta(String nombre, Cuenta newData) {
        int index;
        if((index = indexToElementIn(cuentas, buscarCuenta(nombre))) != NOTFOUND) {
            cuentas[index] = newData;
            return "Datos Actualizados";
        }
        return "Cuenta no encontrada";
    }

    public Cuenta buscarCuenta(String nombre) {
        for(Cuenta cuenta : cuentas) {
            if(isNull(cuenta))continue;
            if(cuenta.getNombreCliente()==nombre)return cuenta;
        }
        return null;
    }

    public String eliminarCuenta(String nombre) {
        Object cuenta;
        if(!isNull((cuenta = buscarCuenta(nombre)))) {
            cuentas[indexToElementIn(cuentas, cuenta)] = null;
            return "Cuenta eliminada";
        }
        return "Cuenta no encontrada";
    }

    private int indexToElementIn(Object[]array, Object objToFind) {
        int i = 0;
        for(Object obj : array) {
            if(obj == objToFind)return i;
            i++;
        }
        return NOTFOUND;
    }

    private int indexToAllocIn(Object [] array) {
        int i = 0;
        for(Object obj : array) {
            if(isNull(obj))return i;
            i++;
        }
        return LLENO;
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }
}
