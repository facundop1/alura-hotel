package alurahotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory con = new ConnectionFactory();
        Connection connection = con.recuperarConexion();

        System.out.println("Cerrando conexion");

        connection.close();
    }
}
