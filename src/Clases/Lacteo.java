package Clases;

import java.util.Scanner;

public class Lacteo extends Perecedero {
    private String lote;
    private final double VALOR_IVA = 0.04;
    private final boolean Fragil = false;

    public Lacteo(int codigo,String nombre, double precio, double peso, int cantidad,String fechaCad, String lote) {
        super(codigo, nombre, precio, peso, cantidad,  fechaCad);
        this.lote = lote;
        setIva(VALOR_IVA);
    }

    public Lacteo(Scanner in) {
        super(in);
        System.out.println("Introduce un lote:");
        while(true){
            try{
                lote = String.valueOf(in.nextInt());
                break;
            }catch (Exception e){
                System.out.println("Lote mal introducido");
                in.nextLine();
            }
        }
        setIva(VALOR_IVA);
    }

    @Override
    public String volcar() {
        return super.volcar() + " " + lote + " Lacteo";
    }

    @Override
    public boolean envioFragil() {
        return Fragil;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println(" Producto lácteo " + lote);
    }
}
