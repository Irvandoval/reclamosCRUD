package com.example.irvandoval.reclamosgrupo17.sucursal;

/**
 * Created by carlosx on 05-10-15.
 */
public class Sucursal {
    private int idSucursal;
    private int idEmpresa;
    private int idZona;
    private String nombreSucursal;
    private String jefeSucursal;
    private String direccionSucursal;
    private String telefonoSucursal;


    private Sucursal(){
    }

    public Sucursal(int idSucursal, int idEmpresa, int idZona, String nombreSucursal, String jefeSucursal, String direccionSucursal, String telefonoSucursal) {
        this.idSucursal = idSucursal;
        this.idEmpresa = idEmpresa;
        this.idZona = idZona;
        this.nombreSucursal = nombreSucursal;
        this.jefeSucursal = jefeSucursal;
        this.direccionSucursal = direccionSucursal;
        this.telefonoSucursal = telefonoSucursal;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getJefeSucursal() {
        return jefeSucursal;
    }

    public void setJefeSucursal(String jefeSucursal) {
        this.jefeSucursal = jefeSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }
}
