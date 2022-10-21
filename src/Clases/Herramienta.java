package Clases;

import Interfaces.Enviable;

import java.util.Scanner;

public class Herramienta extends NoPerecedero implements Enviable {
    private final double VALOR_IVA = 0.4;
    private final double TARIFA_ENVIO = 3.71;
    private final boolean Fragil = false;

    public Herramienta(int codigo,String nombre, double precio, double peso, int cantidad) {
        super(codigo, nombre, precio, peso, cantidad);
        setIva(VALOR_IVA);
    }

    public Herramienta(Scanner in) {
        super(in);
        setIva(VALOR_IVA);
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
        return super.volcar() + "Herramienta";
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println(" Herramienta");
    }

    @Override
    public void imprimirEnvio() {
        super.imprimirEnvio();
    }
    public double getPeso(){
        return super.getPeso();
    }
}
