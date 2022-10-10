package alurahotel.dao;

import alurahotel.model.Reservas;

import java.sql.*;
import java.util.ArrayList;

public class ReservasDAO {
    final private Connection con;

    public ReservasDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reservas reservas) {
        try {
            final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVAS " +
                    "(fecha_entrada, fecha_salida, valor, forma_pago) " +
                    "VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {

                statement.setDate(1, reservas.getFecha_entrada());
                statement.setDate(2, reservas.getFecha_salida());
                statement.setString(3, reservas.getValor());
                statement.setString(4, reservas.getForma_pago());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reservas.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Reservas> listar() {
        ArrayList<Reservas> reservas = new ArrayList<>();

        try {
            final PreparedStatement statement = con.prepareStatement("SELECT "
                    + "id,fecha_entrada,fecha_salida,valor,forma_pago FROM RESERVAS");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet){
                    while (resultSet.next()) {
                        reservas.add(new Reservas(resultSet.getInt("id"),
                                resultSet.getDate("fecha_entrada"),
                                        resultSet.getDate("fecha_salida"),
                                        resultSet.getString("valor"),
                                        resultSet.getString("forma_pago")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " +
                    "fecha_entrada = ?,fecha_salida = ?,valor = ?,forma_pago = ? WHERE id = ?");

            try (statement){
                statement.setDate(1,fecha_entrada);
                statement.setDate(2, fecha_salida);
                statement.setString(3, valor);
                statement.setString(4, forma_pago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE id = ?");

            try (statement){
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
