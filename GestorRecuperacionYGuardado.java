//******************************************type(A|C):num:nombre:saldo:(comision|transacciones):importetransacciones*:mes*******************************************
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

class GestorRecuperacionYGuardado {
    private static final int NOMBRE = 1, NUMERO_CUENTA = 2, SALDO = 3, COMISION__TRANSACCIONES = 4, IMPORTE_TRANSACCIONES = 5, ULTIMOMESREGISTRO = 6;
    ArrayList<Cuenta> db;
    GestorRecuperacionYGuardado(ArrayList<Cuenta> db) {
        this.db = db;
    }
    public boolean recuperarCuentas() {
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

    public boolean saveFormat2Cuenta(String line) {
        String elementos[] = line.split(":");
        if(elementos[0].equals("A")) {
            Cuenta_ahorro cuenta_ahorro = new Cuenta_ahorro(elementos[NOMBRE], elementos[NUMERO_CUENTA],Float.parseFloat(elementos[SALDO]), Float.parseFloat(elementos[COMISION__TRANSACCIONES]));
            cuenta_ahorro.setLastLoginMount(Integer.parseInt(elementos[ULTIMOMESREGISTRO]));
            cuenta_ahorro.aplicarCargosEIntereses();
            db.add(cuenta_ahorro);
            return true;
        }
        if(elementos[0].equals("C")) {
            Cuenta_corriente cuenta_corriente = new Cuenta_corriente(elementos[NOMBRE], elementos[NUMERO_CUENTA], Float.parseFloat(elementos[SALDO]), Float.parseFloat(elementos[IMPORTE_TRANSACCIONES]));
            cuenta_corriente.setLastLoginMount(Integer.parseInt(elementos[ULTIMOMESREGISTRO]));
            cuenta_corriente.setTransacciones(Integer.parseInt(elementos[IMPORTE_TRANSACCIONES]));
            cuenta_corriente.aplicarCargosEIntereses();
            db.add(cuenta_corriente);
            return true;
        }
        return false;
    }

    public boolean saveCuentas() {
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
}
