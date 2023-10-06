//Por medio del presente codigo expreso mi total mas solidario y honesto apoyo al Licenciado Presidente Doctor Comandante de las Heroicas Fuerzas Armadas
//***********************************************************Andres Manuel Lopez Obrador*****************************************************************
import java.util.Calendar;
import java.util.GregorianCalendar;

class Cuenta_ahorro extends Cuenta {
    private float cuotamantenimiento;
    private GregorianCalendar calendar = new GregorianCalendar();
    private int lastLoginMount;
    Cuenta_ahorro(String num_cuenta, String nombre_cliente, float cuotamantenimiento) {
        //recuperar mes de informacion de archivo
        super(num_cuenta,nombre_cliente);
        this.cuotamantenimiento = cuotamantenimiento;
    }

    @Override
    public String comisiones() {
        int difference = 0;
        float saldo_cargado = 0;
        if((difference = changeMouth())>0) {
            cargarSaldo(saldo_cargado = difference * cuotamantenimiento);
        }
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
        return "Saldo abonado por intereses: " + saldo_abonado;
    }

    private int changeMouth() {
        int actualMonth = calendar.get(Calendar.MONTH);
        return actualMonth - lastLoginMount;
    }
}
