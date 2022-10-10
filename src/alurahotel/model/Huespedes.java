package alurahotel.model;

import java.sql.Date;

public class Huespedes {
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String telefono;
    private Integer reserva_id;

    public Huespedes(String nombre, String apellido, Date fecha_nacimiento,
                     String nacionalidad, String telefono, Integer reserva_id) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reserva_id = reserva_id;
    }

    public Huespedes(Integer id, String nombre, String apellido,
                     Date fecha_nacimiento, String nacionalidad, String telefono, Integer reserva_id) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reserva_id = reserva_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Integer reserva_id) {
        this.reserva_id = reserva_id;
    }
}
