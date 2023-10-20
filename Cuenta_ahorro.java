//Por medio del presente codigo expreso mi total mas solidario y honesto apoyo al Licenciado Presidente Doctor Comandante de las Heroicas Fuerzas Armadas
//***********************************************************Andres Manuel Lopez Obrador*****************************************************************
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Cuenta_ahorro extends Cuenta {
    private float cuotamantenimiento;
    private GregorianCalendar calendar = new GregorianCalendar();
    private String nCliente, numCuenta;
    Cuenta_ahorro(String num_cuenta, String nombre_cliente,float saldo,float cuotamantenimiento) {
        super(num_cuenta,nombre_cliente,saldo);
        nCliente = nombre_cliente;
        numCuenta = num_cuenta;
        this.cuotamantenimiento = cuotamantenimiento;
    }

    @Override
    public String comisiones() {
        int difference = 0;
        float saldo_cargado = 0;
        if((difference = changeMouth())>0) {
            cargarSaldo(saldo_cargado = difference * cuotamantenimiento);
        }
        System.out.println("saldo cargado" + saldo_cargado);
        return "Saldo cargado por mantenimiento: " + saldo_cargado;
    }

    @Override
    public String intereses() {
        int difference = 0;
        float saldo_abonado = 0;
        if((difference = changeMouth()) > 0) {
            float interesU = getSaldo() * 0.15f;
            abonarSaldo(saldo_abonado = difference*interesU);
        }
        System.out.println("saldo abonado" + saldo_abonado);
        return "Saldo abonado por intereses: " + saldo_abonado;
    }
    @Override
    public String info() {
        return numCuenta + " -> " + nCliente + " Saldo: " + saldo + " cuota Mantenimiento: " + cuotamantenimiento;
    }

    @Override
    public String formatToSave() {
        return "A:" + numCuenta + ":" + nCliente + ":" + saldo + ":" + cuotamantenimiento + ":_:" + calendar.get(Calendar.MONTH);
    }

    private int changeMouth() {
        int actualMonth = calendar.get(Calendar.MONTH);
        return actualMonth - lastLoginMount;
    }
}
