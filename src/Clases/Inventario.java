package Clases;

import Interfaces.Enviable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
public class Inventario {
    private static Path archivoProductos = Path.of("src/data/productos.txt");
    private static ArrayList<Producto> listaProductos;
    private static Inventario instance;
    private Inventario(){
        listaProductos = new ArrayList<Producto>();
    }
    public static Inventario getInstance(){
        if (instance == null){
            instance = new Inventario();
        }
        return instance;
    }
    public static void cargarProductos(){
        System.out.println("[[Cargando productos...]]");
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
//            archivo = new File("C:\\Users\\2dam3.HZ114485\\IdeaProjects\\SuperOnline\\src\\Info\\productos.txt");
            archivo = new File(archivoProductos.toUri());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null){
                System.out.println(linea);
                String[] textoSeparado = linea.split(" ");
                switch (linea.substring(linea.lastIndexOf(" ")+1)){
                    case "Lacteo":
                        addNuevoProducto(new Lacteo( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
                        break;
                    case "FrutaHortaliza":
                        addNuevoProducto(new FrutaHortaliza( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
                        break;
                    case "Bebida":
                        addNuevoProducto(new Bebida( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
                        break;
                    case "Herramienta":
                        addNuevoProducto(new Herramienta( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3])));
                        break;
                    case "Otros":
                        addNuevoProducto(new Otros( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) , textoSeparado[5]));
                        break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        System.out.println("[[Productos cargados en inventario!]]");
    }
    public static void guardarProductos(){
        try {
            Files.writeString(archivoProductos, "");
            listaProductos.forEach(producto -> {
                try {
                    Files.writeString(archivoProductos, producto.volcar() + System.lineSeparator(), StandardOpenOption.APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void addNuevoProducto(Producto tipo){
        listaProductos.add(tipo);
    }
    public static void mostrarProductos(){
        listaProductos.forEach(producto -> {
            if(producto != null){
//               System.out.println("Id: " + producto.getCodigo() + " Nombre: " + producto.getNombre() + " Precio: " + producto.getPrecio() + " Peso: " + producto.getPeso() + " Stock: " + producto.getCantidad() );
                producto.imprimir();
            }
        });
    }
    public static Producto getProducto(int id){
        return listaProductos.get(id-1);
    }
    public static void actualizarCantidad(int codigo, int cant){
        listaProductos.get(codigo).setCantidad(cant);
    }
    public static int tamaño(){
        return listaProductos.size();
    }
    public static void mostarProductosEnviables(){
        listaProductos.forEach(producto -> {
            if(producto != null &&  producto instanceof Enviable){
                if(!((Enviable) producto).envioFragil()){
                    System.out.printf("Id: %d, Nombre: %s, peso: %.1f, IVA (%.2f%s), tarifa de envío: %.2f, PRECIO-TOTAL: %.2f\n", producto.getCodigo(), producto.getNombre(), producto.getPeso(), producto.getIva(), new String(new char[] { 37 }), ((Enviable) producto).tarifaEnvio(), (producto.calcularPrecioIVA() + ((Enviable) producto).tarifaEnvio()));
                }else{
                    System.out.printf("Id: %d, Nombre: %s, peso: %.1f, IVA (%.2f%s), tarifa de envío: %.2f, Producto frágil, PRECIO-TOTAL: %.2f\n", producto.getCodigo(), producto.getNombre(), producto.getPeso(), producto.getIva(), new String(new char[] { 37 }), ((Enviable) producto).tarifaEnvio(), (producto.calcularPrecioIVA() + ((Enviable) producto).tarifaEnvio()));
                }

            }
        });
    }
    public static void eliminarProducto(int id){
        listaProductos.remove(id);
    }

}

