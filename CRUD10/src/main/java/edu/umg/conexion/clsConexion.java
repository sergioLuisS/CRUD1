package edu.umg.conexion;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class clsConexion {
    private Connection conn = null;

    public clsConexion() {
        // Constructor para inicializar la conexión a la base de datos
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprogra2b", "root", "12345678");
            System.out.println("Conexión realizada exitosamente");
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
        }
    }

    public void cerrarConexion() {
        // Método para cerrar la conexión a la base de datos
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }

    public void leerDatos() {
        // Método para leer y mostrar los datos de la tabla
        try {
            String sql = "SELECT * FROM tb_informacion";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("codigo") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Apellido: " + rs.getString("apellido") +
                        ", Edad: " + rs.getInt("edad") +
                        ", Sexo: " + rs.getString("sexo") +
                        ", Fecha de Registro: " + rs.getDate("fecha_de_registro") +
                        ", Sueldo Base: " + rs.getDouble("sueldo_base") +
                        ", Longitud de Casa: " + rs.getDouble("longitud_casa") +
                        ", Latitud de Casa: " + rs.getDouble("latitud_casa") +
                        ", Comentario: " + rs.getString("comentario"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer datos: " + ex.getMessage());
        }
    }

    public void insertarDatos() {
        // Método para insertar nuevos datos en la tabla
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el apellido");
            String apellido = sc.nextLine();
            System.out.println("Ingrese la edad");
            int edad = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada
            System.out.println("Ingrese el sexo");
            String sexo = sc.nextLine();
            System.out.println("Ingrese el sueldo base");
            double sueldoBase = sc.nextDouble();
            System.out.println("Ingrese la longitud de la casa");
            double longitudCasa = sc.nextDouble();
            System.out.println("Ingrese la latitud de la casa");
            double latitudCasa = sc.nextDouble();
            sc.nextLine(); // Limpiar el buffer de entrada
            System.out.println("Ingrese un comentario");
            String comentario = sc.nextLine();
            java.sql.Date fechadeRegistro = new java.sql.Date(System.currentTimeMillis());
            String sql = "INSERT INTO tb_informacion(nombre, apellido, edad, sexo, fecha_de_registro, sueldo_base, longitud_casa, latitud_casa, comentario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, sexo);
            ps.setDate(5, fechadeRegistro);
            ps.setDouble(6, sueldoBase);
            ps.setDouble(7, longitudCasa);
            ps.setDouble(8, latitudCasa);
            ps.setString(9, comentario);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Datos ingresados correctamente");
            } else {
                System.out.println("No se pudo insertar los datos");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar datos: " + ex.getMessage());
        }
    }

    public void actualizarDatos() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el código del registro que desea actualizar: ");
            int codigo = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada
            System.out.println("Ingrese el nuevo nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el nuevo apellido");
            String apellido = sc.nextLine();
            System.out.println("Ingrese la nueva edad");
            int edad = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada
            System.out.println("Ingrese el nuevo sexo");
            String sexo = sc.nextLine();
            System.out.println("Ingrese el nuevo sueldo base");
            double sueldoBase = sc.nextDouble();
            System.out.println("Ingrese la nueva longitud de la casa");
            double longitudCasa = sc.nextDouble();
            System.out.println("Ingrese la nueva latitud de la casa");
            double latitudCasa = sc.nextDouble();
            sc.nextLine(); // Limpiar el buffer de entrada
            System.out.println("Ingrese un nuevo comentario");
            String comentario = sc.nextLine();

            String sql = "UPDATE tb_informacion SET nombre = ?, apellido = ?, edad = ?, sexo = ?, sueldo_base = ?, longitud_casa = ?, latitud_casa = ?, comentario = ? WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, sexo);
            ps.setDouble(5, sueldoBase);
            ps.setDouble(6, longitudCasa);
            ps.setDouble(7, latitudCasa);
            ps.setString(8, comentario);
            ps.setInt(9, codigo);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Datos actualizados correctamente");
            } else {
                System.out.println("No se pudo actualizar los datos");
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar datos: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese el código del registro que desea eliminar: ");
            int codigo = sc.nextInt();

            String sql = "DELETE FROM tb_informacion WHERE codigo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el registro");
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el registro: " + ex.getMessage());
        }
    }
}
