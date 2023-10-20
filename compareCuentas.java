import java.util.Comparator;

class compareCuentas {
    //Estoy 100 % seguro que hay otra manera de hacer esto pero que weba
};
class compareNumeroCuenta implements Comparator<Cuenta> {
    @Override
    public int compare(Cuenta a, Cuenta b) {
        return a.getNumCuenta().compareTo(b.getNumCuenta());
    }
}

class compareNombre implements Comparator<Cuenta> {
    @Override
    public int compare(Cuenta a, Cuenta b) {
        return a.getNombreCliente().compareTo(b.getNombreCliente());
    }
}
