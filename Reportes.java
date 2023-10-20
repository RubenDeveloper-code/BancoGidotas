import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class Reportes {
    Administrador admin;
    Reportes(Administrador admin) {
        this.admin = admin;
    }

    public String buscarCuentaPorNombre(String nombre) {
        return ">>> " + admin.buscarCuentaPorNombre(nombre);
    }

    public String buscarCuentaPorNumCuenta(String numero) {
        return ">>> " + admin.buscarCuentaPorNumeroCuenta(numero);
    }

    public String reporteGeneral() {
        String reporte = "-------REPORTE GENERAL_______\n";
        reporte += readArray(admin.db);
        return reporte;
    }

    public String reporteNumCuenta() {
        Collections.sort(admin.db, new compareNumeroCuenta());
        String reporte = "------REPORTE NUM CUENTA DESENDIENTE------\n";
        reporte += readArrayInverse(admin.db);
        return reporte;
    }

    public String reporteNombre() {
        Collections.sort(admin.db, new compareNombre());
        String reporte = "------REPORTE NOMBRE------\n";
        reporte += readArray(admin.db);
        return reporte;
    }

    public String reporteCuentaAhorro() {
        ArrayList<Cuenta> cuentas_ahorro = admin.getSubArrayCuentaAhorro();
        Collections.sort(cuentas_ahorro, new compareNombre());
        String reporte = "------REPORTE CUENTA AHORRO------\n";
        reporte += readArray(cuentas_ahorro);
        return reporte;

    }

    public String reporteCuentaCorriente() {
        ArrayList<Cuenta> cuenta_corrientes = admin.getSubArrayCuentaCorriente();
        Collections.sort(cuenta_corrientes, new compareNombre());
        String reporte = "------REPORTE CUENTA CORRIENTE DESENDIENTE------\n";
        reporte += readArrayInverse(cuenta_corrientes);
        return reporte;

    }

    private String readArray(ArrayList<Cuenta> array) {
        String res = "";
        for (Object cuenta : array) {
            res+=cuenta.toString()+"\n";
        }
        return res;
    }

    private String readArrayInverse(ArrayList<Cuenta> array) {
        String res = "";
        for (int i = array.size()-1; i >= 0; i--) {
            res += array.get(i).toString() + "\n";
        }
        return res;
    }

}
