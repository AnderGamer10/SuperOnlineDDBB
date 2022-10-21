package Clases;

import Interfaces.Enviable;

import java.util.Scanner;

abstract public class Perecedero extends Producto implements Enviable {
    private String fechaCad;
    private final boolean Fragil = false;
    private final double TARIFA_ENVIO = 1.71;

    public Perecedero(int codigo,String nombre,double precio, double peso, int cantidad,  String fechaCad) {
        super(codigo, nombre, precio, peso, cantidad);
        this.fechaCad = fechaCad;
    }

    public Perecedero(Scanner in) {
        super(in);
        System.out.println("Introduce una fecha de caducidad:");
        while(true){
            try{
                fechaCad = in.nextLine();
                break;
            }catch (Exception e){
                System.out.println("La fecha ha sido mal introducida");
            }
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.print(" Fecha de caducidad: " + fechaCad);
    }

    @Override
    public void imprimirEnvio() {
        super.imprimirEnvio();
        System.out.println("Tarifa de envio: " + tarifaEnvio() + " \nPrecio total: " + getPrecio());
    }

    @Override
    public boolean envioFragil() {
        return Fragil;
    }

    @Override
    public double tarifaEnvio() {
        return TARIFA_ENVIO;
    }

    @Override
    public String volcar() {
        return super.volcar() + fechaCad;
    }
    public double getPeso(){
        return super.getPeso();
    }
}
