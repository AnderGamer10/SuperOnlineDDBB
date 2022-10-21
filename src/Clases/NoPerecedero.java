package Clases;

import java.util.Scanner;

public class NoPerecedero extends Producto{
    public NoPerecedero(int codigo,String nombre,double precio, double peso, int cantidad) {
        super(codigo, nombre, precio, peso, cantidad);
    }

    public NoPerecedero(Scanner in) {
        super(in);
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.print(" Producto no perecedero ");
    }
}
