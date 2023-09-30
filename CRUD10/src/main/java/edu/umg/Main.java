package edu.umg;

import edu.umg.conexion.clsConexion;

import java.sql.SQLException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        clsConexion conexion = new clsConexion();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar datos");
            System.out.println("2. Actualizar datos");
            System.out.println("3. Eliminar datos");
            System.out.println("4. Mostrar datos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    conexion.insertarDatos();
                    break;
                case 2:
                    conexion.actualizarDatos();
                    break;
                case 3:
                    conexion.eliminar();
                    break;
                case 4:
                    conexion.leerDatos();
                    break;
                case 5:
                    conexion.cerrarConexion();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
//    public static void main(String[] args) throws SQLException {
//        clsConexion conexion = new clsConexion();
//
//        conexion.insertarDatos();
//        conexion.leerDatos();
//        conexion.leerDatos();
//        conexion.cerrarConexion();
//        conexion.updates();
//        conexion.leerDatos();
//    }
