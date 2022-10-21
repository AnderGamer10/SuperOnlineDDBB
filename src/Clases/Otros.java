package Clases;

import java.util.Scanner;

public class Otros extends NoPerecedero{
    private String categoria;
    private final double VALOR_IVA = 0.16;

    public Otros(int codigo,String nombre, double precio, double peso, int cantidad, String categoria) {
        super(codigo, nombre, precio, peso, cantidad);
        this.categoria = categoria;
        setIva(VALOR_IVA);
    }

    public Otros(Scanner in) {
        super(in);
        System.out.println("Introduce la categoria");
        while(true){
            try{
                categoria = String.valueOf(in.nextInt());
                break;
            }catch (Exception e){
                System.out.println("Categoria mal introducida");
                in.nextLine();
            }
        }
        setIva(VALOR_IVA);
    }

    @Override
    public String volcar() {
        return super.volcar() + categoria + " Otros";
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println(" Categoría: " + categoria);
    }
}
