package com.example.irvandoval.reclamosgrupo17.prodserv;

/**
 * Created by irvandoval on 05-10-15.
 */
public class ProdServ {
    private int idProdServ;
    private int idCategoriaProd;
    private String nombreProdServ;
    private String descripcionProdServ;

    public ProdServ() {
    }

    public ProdServ(int idProdServ, int idCategoriaProd, String nombreProdServ, String descripcionProdServ) {
        this.idProdServ = idProdServ;
        this.idCategoriaProd = idCategoriaProd;
        this.nombreProdServ = nombreProdServ;
        this.descripcionProdServ = descripcionProdServ;
    }

    public int getIdProdServ() {
        return idProdServ;
    }

    public void setIdProdServ(int idProdServ) {
        this.idProdServ = idProdServ;
    }

    public int getIdCategoriaProd() {
        return idCategoriaProd;
    }

    public void setIdCategoriaProd(int idCategoriaProd) {
        this.idCategoriaProd = idCategoriaProd;
    }

    public String getNombreProdServ() {
        return nombreProdServ;
    }

    public void setNombreProdServ(String nombreProdServ) {
        this.nombreProdServ = nombreProdServ;
    }

    public String getDescripcionProdServ() {
        return descripcionProdServ;
    }

    public void setDescripcionProdServ(String descripcionProdServ) {
        this.descripcionProdServ = descripcionProdServ;
    }
}
