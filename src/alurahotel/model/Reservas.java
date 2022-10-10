package alurahotel.model;

import java.sql.Date;

public class Reservas {

    private Integer id;
    private Date fecha_entrada;
    private Date fecha_salida;
    private String valor;
    private String forma_pago;

    public Reservas(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.valor = valor;
        this.forma_pago = forma_pago;
    }

    public Reservas(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
        this.id = id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.valor = valor;
        this.forma_pago = forma_pago;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_entrada() {
        return this.fecha_entrada;
    }

    public Date getFecha_salida() {
        return this.fecha_salida;
    }

    public String getValor() {
        return this.valor;
    }

    public String getForma_pago() {
        return this.forma_pago;
    }
}
