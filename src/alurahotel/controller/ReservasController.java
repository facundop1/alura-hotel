package alurahotel.controller;

import alurahotel.dao.ReservasDAO;
import alurahotel.factory.ConnectionFactory;
import alurahotel.model.Reservas;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReservasController {
    private ReservasDAO reservasDAO;

    public ReservasController() {
        ConnectionFactory factory = new ConnectionFactory();
        this.reservasDAO = new ReservasDAO(factory.recuperarConexion());
    }

    public void guardar(Reservas reservas) {
        this.reservasDAO.guardar(reservas);
    }

    public ArrayList<Reservas> listar() {
        return this.reservasDAO.listar();
    }

    public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
        return this.reservasDAO.modificar(id,fecha_entrada,fecha_salida,valor,forma_pago);
    }

    public int eliminar(Integer id) {
        return this.reservasDAO.eliminar(id);
    }
}
