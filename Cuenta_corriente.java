import java.util.Calendar;
import java.util.GregorianCalendar;

class Cuenta_corriente extends Cuenta {
    private float transacciones, importetransacciones;
    private GregorianCalendar calendar = new GregorianCalendar();
    private int lastLoginMount;
    Cuenta_corriente(String num_cuenta, String nombre_cliente, float transacciones, float importetransacciones) {
        //recuperar mes de informacion de archivo
        super(num_cuenta,nombre_cliente);
        this.transacciones = transacciones;
        this.importetransacciones = importetransacciones;
    }

    @Override
    public String comisiones() {
        int difference = 0;
        float saldo_cargado = 0;
        if((difference = changeMouth())>0) {
            cargarSaldo(saldo_cargado = difference * (transacciones * importetransacciones));
        }
        return "Comisiones no se tu acomodale uwu: " + saldo_cargado;
    }

    @Override
    public String intereses() {
        int difference = 0;
        float saldo_abonado = 0;
        if((difference = changeMouth()) > 0) {
            float p_intereses = 0;
            if(getSaldo() > 20000)p_intereses = 0.10f;
            if(getSaldo() > 5000 && getSaldo() < 10000)p_intereses = 0.05f;
            float interesesTT = p_intereses * getSaldo();
            abonarSaldo(saldo_abonado = difference*(interesesTT));
        }
        return "Wey a que olera el peje?: " + saldo_abonado;
    }

    private int changeMouth() {
        int actualMonth = calendar.get(Calendar.MONTH);
        return actualMonth - lastLoginMount;
    }
}
