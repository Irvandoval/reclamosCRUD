package com.example.irvandoval.reclamosgrupo17.detallereclamo;

/**
 * Created by irvandoval on 05-09-15.
 */
public class DetalleReclamo {
    private int idDetalle;
    private int idProdServ;
    private String descripcionDetalle;

    public DetalleReclamo() {
    }

    public DetalleReclamo(int idDetalle, int idProdServ, String descripcionDetalle) {
        this.idDetalle = idDetalle;
        this.idProdServ = idProdServ;
        this.descripcionDetalle = descripcionDetalle;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdProdServ() {
        return idProdServ;
    }

    public void setIdProdServ(int idProdServ) {
        this.idProdServ = idProdServ;
    }

    public String getDescripcionDetalle() {
        return descripcionDetalle;
    }

    public void setDescripcionDetalle(String descripcionDetalle) {
        this.descripcionDetalle = descripcionDetalle;
    }
}
