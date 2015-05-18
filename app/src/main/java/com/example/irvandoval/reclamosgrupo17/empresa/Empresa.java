package com.example.irvandoval.reclamosgrupo17.empresa;

/**
 * Created by carlosx on 05-10-15.
 */
public class Empresa {
    private int idEmpresa;
    private int idCategoriaEmp;
    private String nombreEmpresa;
    private int cantidadSucursales;

    public Empresa() {
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdCategoriaEmp() {
        return idCategoriaEmp;
    }

    public void setIdCategoriaEmp(int idCategoriaEmp) {
        this.idCategoriaEmp = idCategoriaEmp;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getCantidadSucursales() {
        return cantidadSucursales;
    }

    public void setCantidadSucursales(int cantidadSucursales) {
        this.cantidadSucursales = cantidadSucursales;
    }
}
