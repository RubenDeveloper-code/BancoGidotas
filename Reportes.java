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
        for(Cuenta cuenta : admin.db) {
            reporte += cuenta.info() + "\n";
        }
        return reporte;
    }

    public String reporteNumCuenta() {
        Collections.sort(admin.db, new compareNumeroCuenta());
        String reporte = "------REPORTE NUM CUENTA DESENDIENTE------\n";
        for (int i = admin.db.size()-1; i >= 0; i--) {
            reporte += admin.db.get(i).info() + "\n";
        }
        return reporte;
    }

    public String reporteNombre() {
        Collections.sort(admin.db, new compareNombre());
        String reporte = "------REPORTE NOMBRE------\n";
        for (Cuenta cuenta : admin.db) {
            reporte += cuenta.info() + "\n";
        }
        return reporte;
    }

    public String reporteCuentaAhorro() {
        ArrayList<Cuenta_ahorro> cA = admin.getSubArrayCuentaAhorro();
        Collections.sort(cA, new compareNombre());
        String reporte = "------REPORTE CUENTA AHORRO------\n";
        for (Cuenta cuenta : cA) {
            reporte += cuenta.info() + "\n";
        }
        return reporte;

    }

    public String reporteCuentaCorriente() {
        ArrayList<Cuenta_corriente> cA = admin.getSubArrayCuentaCorriente();
        Collections.sort(cA, new compareNombre());
        String reporte = "------REPORTE CUENTA CORRIENTE DESENDIENTE------\n";
        for (int i = cA.size()-1; i >= 0; i--) {
            reporte += cA.get(i).info() + "\n";
        }
        return reporte;

    }

}
