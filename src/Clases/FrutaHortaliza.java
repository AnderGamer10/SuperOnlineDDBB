package Clases;

import java.util.Scanner;

public class FrutaHortaliza extends Perecedero{
    private String origen;
    private final double VALOR_IVA = 0.16;
    private final boolean Fragil = false;

    public FrutaHortaliza(int codigo,String nombre, double precio, double peso, int cantidad, String fechaCad, String origen) {
        super(codigo, nombre, precio, peso, cantidad,  fechaCad);
        this.origen = origen;
        setIva(VALOR_IVA);
    }

    public FrutaHortaliza(Scanner in) {
        super(in);
        System.out.println("Introduce el Origen");
        while(true){
            try{
                origen = String.valueOf(in.nextInt());
                break;
            }catch (Exception e){
                System.out.println("Origen mal introducido");
                in.nextLine();
            }
        }
        setIva(VALOR_IVA);
    }

    @Override
    public String volcar() {
        return super.volcar() + " " + origen + " FrutaHortaliza";
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println(" Origen: " + origen);
    }
    @Override
    public boolean envioFragil() {
        return Fragil;
    }

    public void calcularPrecioEnvio(){
        Double precioEnvio = super.tarifaEnvio();
    }
}
