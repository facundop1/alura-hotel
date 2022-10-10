package alurahotel.controller;

import alurahotel.dao.HuespedesDAO;
import alurahotel.factory.ConnectionFactory;
import alurahotel.model.Huespedes;

import java.sql.Date;
import java.util.ArrayList;

public class HuespedesController {
    private HuespedesDAO huespedesDAO;

    public HuespedesController() {
        ConnectionFactory factory = new ConnectionFactory();
        this.huespedesDAO = new HuespedesDAO(factory.recuperarConexion());
    }

    public void guardar(Huespedes huespedes) {
        this.huespedesDAO.guardar(huespedes);
    }

    public ArrayList<Huespedes> listar() {
        return this.huespedesDAO.listar();
    }

    public int modificar(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad,
                         String telefono, Integer reserva_id) {
        return this.huespedesDAO.modificar(id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id);
    }

    public int eliminar(Integer id) {
        return this.huespedesDAO.eliminar(id);
    }
}
