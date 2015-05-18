package com.example.irvandoval.reclamosgrupo17.reclamo;

/**
 * Created by carlosx on 05-10-15.
 */
public class Reclamo {
    private int idReclamo;
    private String dui;
    private int idEstadoReclamo;
    private int idSucursal;
    private int idDetalle;
    private String titulo;
    private String motivoReclamo;
    private String fechaReclamo;

    public Reclamo() {
    }

    public Reclamo(int idReclamo, String dui, int idEstadoReclamo, int idSucursal, int idDetalle, String titulo, String fechaReclamo, String motivoReclamo) {
        this.idReclamo = idReclamo;
        this.dui = dui;
        this.idEstadoReclamo = idEstadoReclamo;
        this.idSucursal = idSucursal;
        this.idDetalle = idDetalle;
        this.titulo = titulo;
        this.fechaReclamo = fechaReclamo;
        this.motivoReclamo = motivoReclamo;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public int getIdEstadoReclamo() {
        return idEstadoReclamo;
    }

    public void setIdEstadoReclamo(int idEstadoReclamo) {
        this.idEstadoReclamo = idEstadoReclamo;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(String fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public String getMotivoReclamo() {
        return motivoReclamo;
    }

    public void setMotivoReclamo(String motivoReclamo) {
        this.motivoReclamo = motivoReclamo;
    }
}
