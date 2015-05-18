package com.example.irvandoval.reclamosgrupo17.categoriaprodserv;

/**
 * Created by irvandoval on 05-10-15.
 */
public class CategoriaProdServ {
    private int idCategoriaProdServ;
    private String nombreCategoriaPs;
    private String descripcionCategoriaPs;
    private int cantidadProductos;

    public CategoriaProdServ() {
    }

    public CategoriaProdServ(int idCategoriaProdServ, String nombreCategoriaPs, String descripcionCategoriaPs, int cantidadProductos) {
        this.idCategoriaProdServ = idCategoriaProdServ;
        this.nombreCategoriaPs = nombreCategoriaPs;
        this.descripcionCategoriaPs = descripcionCategoriaPs;
        this.cantidadProductos = cantidadProductos;
    }

    public int getIdCategoriaProdServ() {
        return idCategoriaProdServ;
    }

    public void setIdCategoriaProdServ(int idCategoriaProdServ) {
        this.idCategoriaProdServ = idCategoriaProdServ;
    }

    public String getNombreCategoriaPs() {
        return nombreCategoriaPs;
    }

    public void setNombreCategoriaPs(String nombreCategoriaPs) {
        this.nombreCategoriaPs = nombreCategoriaPs;
    }

    public String getDescripcionCategoriaPs() {
        return descripcionCategoriaPs;
    }

    public void setDescripcionCategoriaPs(String descripcionCategoriaPs) {
        this.descripcionCategoriaPs = descripcionCategoriaPs;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
}
