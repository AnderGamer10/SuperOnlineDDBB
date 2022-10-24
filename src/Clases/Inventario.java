package Clases;

import Interfaces.Enviable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;
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
//        System.out.println("[[Cargando productos...]]");
//        File archivo = null;
//        FileReader fr = null;
//        BufferedReader br = null;
//        try {
//            archivo = new File(archivoProductos.toUri());
//            fr = new FileReader (archivo);
//            br = new BufferedReader(fr);
//
//            String linea;
//            while((linea=br.readLine())!=null){
//                System.out.println(linea);
//                String[] textoSeparado = linea.split(" ");
//                switch (linea.substring(linea.lastIndexOf(" ")+1)){
//                    case "Lacteo":
//                        addNuevoProducto(new Lacteo( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
//                        break;
//                    case "FrutaHortaliza":
//                        addNuevoProducto(new FrutaHortaliza( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
//                        break;
//                    case "Bebida":
//                        addNuevoProducto(new Bebida( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) ,textoSeparado[5] ,textoSeparado[6]));
//                        break;
//                    case "Herramienta":
//                        addNuevoProducto(new Herramienta( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3])));
//                        break;
//                    case "Otros":
//                        addNuevoProducto(new Otros( Integer.parseInt(textoSeparado[0]),  textoSeparado[1],  Double.parseDouble(textoSeparado[2]),  Double.parseDouble(textoSeparado[4]),  Integer.parseInt(textoSeparado[3]) , textoSeparado[5]));
//                        break;
//                }
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if( null != fr ){
//                    fr.close();
//                }
//            }catch (Exception e2){
//                e2.printStackTrace();
//            }
//        }
//        System.out.println("[[Productos cargados en inventario!]]");



    }



    public static void migrarProductos(){
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/prueba",
                    "root", "admin"
            );
            try (PreparedStatement statement = connection.prepareStatement("""
                        CREATE TABLE IF NOT EXISTS Productos (
                            Codigo INT NOT NULL AUTO_INCREMENT,
                            Nombre VARCHAR(50) NOT NULL,
                            Precio DOUBLE(20,2) NOT NULL,
                            Cantidad INT NOT NULL,
                            Peso DOUBLE(20,2) NOT NULL,
                            FechaCad VARCHAR(50),
                            Especificacion VARCHAR(50),
                            Tipo VARCHAR(50) NOT NULL,
                            PRIMARY KEY (Codigo)
                        );
                    """)) {
                statement.executeUpdate();
            }

            listaProductos.forEach(pr -> {
                String[] arDatos = pr.volcar().split(" ");
                switch (arDatos[arDatos.length-1]){
                    case "Herramienta":
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO Productos (Nombre, Precio, Cantidad,Peso,Tipo)
                            VALUES(?, ?, ?, ?, ?);
                        """)) {
                            statement.setString(1, arDatos[1]);
                            statement.setDouble(2, Double.parseDouble(arDatos[2]));
                            statement.setInt(3, Integer.parseInt(arDatos[3]));
                            statement.setDouble(4, Double.parseDouble(arDatos[4]));
                            statement.setString(5, arDatos[5]);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "Otros":
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO Productos (Nombre, Precio, Cantidad,Peso,Especificacion,Tipo)
                            VALUES(?, ?, ?, ?, ?, ?);
                        """)) {
                            statement.setString(1, arDatos[1]);
                            statement.setDouble(2, Double.parseDouble(arDatos[2]));
                            statement.setInt(3, Integer.parseInt(arDatos[3]));
                            statement.setDouble(4, Double.parseDouble(arDatos[4]));
                            statement.setString(5, arDatos[5]);
                            statement.setString(6, arDatos[6]);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO Productos (Nombre, Precio, Cantidad,Peso,FechaCad,Especificacion,Tipo)
                            VALUES(?, ?, ?, ?, ?, ?, ?);
                        """)) {
                            statement.setString(1, arDatos[1]);
                            statement.setDouble(2, Double.parseDouble(arDatos[2]));
                            statement.setInt(3, Integer.parseInt(arDatos[3]));
                            statement.setDouble(4, Double.parseDouble(arDatos[4]));
                            statement.setString(5, arDatos[5]);
                            statement.setString(6, arDatos[6]);
                            statement.setString(7, arDatos[7]);
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                }

            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




    public static void addNuevoProducto(Producto tipo){
        listaProductos.add(tipo);
    }
    public static void mostrarProductos(){
        listaProductos.forEach(producto -> {
            if(producto != null){
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

