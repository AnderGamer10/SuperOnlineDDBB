package Clases;

import java.util.Scanner;

public class Bebida extends Perecedero{
    private String graduacion;
    private final double VALOR_IVA = 0.16;
    private final boolean Fragil = false;

    public Bebida(int codigo,String nombre, double precio, double peso, int cantidad, String fechaCad, String graduacion) {
        super(codigo, nombre, precio, peso, cantidad,  fechaCad);
        this.graduacion = graduacion;
        setIva(VALOR_IVA);
    }

    public Bebida(Scanner in) {
        super(in);
        System.out.println("Introduce la graduacion");
        while(true){
            try{
                graduacion = String.valueOf(in.nextInt());
                break;
            }catch (Exception e){
                System.out.println("Graduacion mal introducida");
                in.nextLine();
            }
        }
        setIva(VALOR_IVA);
    }

    @Override
    public String volcar() {
        return super.volcar() + " " + graduacion + " Bebida";
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println(" Graduación: " + graduacion);
    }

    @Override
    public boolean envioFragil() {
        return Fragil;
    }
}
