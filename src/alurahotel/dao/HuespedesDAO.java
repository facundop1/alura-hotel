package alurahotel.dao;

import alurahotel.model.Huespedes;

import java.sql.*;
import java.util.ArrayList;

public class HuespedesDAO {
    final private Connection con;

    public HuespedesDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huespedes huespedes) {
        try {
            final PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPEDES "
                    + "(nombre,apellido,fecha_nacimiento,nacionalidad,telefono,reserva_id) "
                    + "VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, huespedes.getNombre());
                statement.setString(2, huespedes.getApellido());
                statement.setDate(3, huespedes.getFecha_nacimiento());
                statement.setString(4, huespedes.getNacionalidad());
                statement.setString(5, huespedes.getTelefono());
                statement.setInt(6, huespedes.getReserva_id());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        huespedes.setId(resultSet.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Huespedes> listar() {
        ArrayList<Huespedes> huespedes = new ArrayList<>();

        try {
            final PreparedStatement statement = con.prepareStatement("SELECT "
                    + "id,nombre,apellido,fecha_nacimiento,nacionalidad,telefono,reserva_id FROM HUESPEDES");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        huespedes.add(new Huespedes(resultSet.getInt("id"),
                                resultSet.getString("nombre"), resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("reserva_id")));
                    }
                }
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedes;
    }

    public int modificar(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad,
                         String telefono, Integer reserva_id) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET " +
                    "nombre = ?,apellido = ?,fecha_nacimiento = ?,nacionalidad = ?,telefono = ?,reserva_id = ? "
                    + "WHERE id = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, fecha_nacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, reserva_id);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE id = ?");

            try (statement){
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
