import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

class Administrador {
    //defines
    private static final int NOMBRE = 1, NUMERO_CUENTA = 2, SALDO = 3, COMISION__TRANSACCIONES = 4, IMPORTE_TRANSACCIONES = 5, ULTIMOMESREGISTRO = 6;
    private static final int ERROR = 1;
    private static final int DONE = 0;
    final static int CUENTA_AHORRO = 1, CUENTA_CORRIENTE = 2, LLENO = -1, NOTFOUND = -1;
    int cuentasMaximas;
    ArrayList<Cuenta> db;
    public Administrador() {
        db = new ArrayList<Cuenta>();
        recuperarCuentas();
    };

    //LLAMAR AL CERRAR PROGRAMA !!!!ESTO EN C++ NO PASAAAAAAAA!!!!!!!!
    public void close() {
        saveCuentas();
    }
    //Gestion cuentas;

    private boolean recuperarCuentas() {
        //type(A|C):num:nombre:saldo:(comision|transacciones):importetransacciones:mes
        try {
            File file = new File("Cuentas.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null) {
                saveFormat2Cuenta(line);
            }
            br.close();
            fr.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveFormat2Cuenta(String line) {
        String elementos[] = line.split(":");
        if(elementos[0].equals("A")) {
            Cuenta_ahorro cuenta_ahorro = new Cuenta_ahorro(elementos[NOMBRE], elementos[NUMERO_CUENTA],Float.parseFloat(elementos[SALDO]), Float.parseFloat(elementos[COMISION__TRANSACCIONES]));
            cuenta_ahorro.setLastLoginMount(Integer.parseInt(elementos[ULTIMOMESREGISTRO]));
            cuenta_ahorro.aplicarCargosEIntereses();
            db.add(cuenta_ahorro);
            return true;
        }
        if(elementos[0].equals("C")) {
            Cuenta_corriente cuenta_corriente = new Cuenta_corriente(elementos[NOMBRE], elementos[NUMERO_CUENTA], Float.parseFloat(elementos[SALDO]),Float.parseFloat(elementos[COMISION__TRANSACCIONES]), Float.parseFloat(elementos[IMPORTE_TRANSACCIONES]));
            cuenta_corriente.setLastLoginMount(Integer.parseInt(elementos[ULTIMOMESREGISTRO]));
            cuenta_corriente.aplicarCargosEIntereses();
            db.add(cuenta_corriente);
            return true;
        }
        return false;
    }

    private boolean saveCuentas() {
        try {
            FileWriter fw = new FileWriter("Cuentas.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (Cuenta cuenta : db) {
                pw.println(cuenta.formatToSave());
            }
            pw.close();
            fw.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int altaCuenta(String num_cuenta, String nombre_cliente,float saldo, float comision) {
        if(buscarCuentaPorNumeroCuenta(num_cuenta) == "-1") {
            db.add(new Cuenta_ahorro(num_cuenta, nombre_cliente,saldo,comision));
            return DONE;
        }
        return ERROR;
    }

    public int altaCuenta(String num_cuenta, String nombre_cliente,float saldo, float transacciones, float importetransacciones) {
        if(buscarCuentaPorNumeroCuenta(num_cuenta) == "-1") {
            db.add(new Cuenta_corriente(num_cuenta, nombre_cliente,saldo,transacciones,importetransacciones ));
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
            return cuenta.info();
        }
        return "-1";
    }

    public String buscarCuentaPorNumeroCuenta(String nCuenta) {
        Cuenta cuenta;
        if(!isNull(cuenta = getCuentaPorNumCuenta(nCuenta))) {
            return cuenta.info();
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
