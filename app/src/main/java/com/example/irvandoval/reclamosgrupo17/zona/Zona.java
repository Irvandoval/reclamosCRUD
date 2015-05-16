package com.example.irvandoval.reclamosgrupo17.zona;

/**
 * Created by carlosx on 05-10-15.
 */
public class Zona {
    private int idZona;
    private String nombreZona;
    private String municipio;
    private String departamento;

    public Zona(){
    }

    public Zona(int idZona, String nombreZona, String municipio, String departamento) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.municipio = municipio;
        this.departamento = departamento;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
