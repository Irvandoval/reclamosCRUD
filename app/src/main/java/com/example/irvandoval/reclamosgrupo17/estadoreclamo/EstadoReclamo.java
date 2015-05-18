package com.example.irvandoval.reclamosgrupo17.estadoreclamo;

/**
 * Created by irvandoval on 05-10-15.
 */
public class EstadoReclamo {
    private int idEstadoReclamo;
    private String nombreEstado;
    private String descripcionEstado;

    public EstadoReclamo() {
    }

    public EstadoReclamo(int idEstadoReclamo, String nombreEstado, String descripcionEstado) {
        this.idEstadoReclamo = idEstadoReclamo;
        this.nombreEstado = nombreEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public int getIdEstadoReclamo() {
        return idEstadoReclamo;
    }

    public void setIdEstadoReclamo(int idEstadoReclamo) {
        this.idEstadoReclamo = idEstadoReclamo;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }
}
