class tests {
    public static void main(String[] args) {
        Administrador admin = new Administrador();
        Movimientos movimientos = new Movimientos(admin);
        Reportes reportes = new Reportes(admin);

        admin.altaCuenta("123", "Josefino", 100, 10,10);
        admin.altaCuenta("1234", "Josefino2", 10,1);
        System.out.println(reportes.reporteCuentaAhorro());
        System.out.println(reportes.reporteCuentaCorriente());
        System.out.println(reportes.reporteNombre());
        System.out.println(reportes.reporteGeneral());
        System.out.println(reportes.reporteNumCuenta());
        System.out.println(reportes.buscarCuentaPorNumCuenta("1234"));
        admin.close();
    }
}
