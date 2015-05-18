package com.example.irvandoval.reclamosgrupo17.categoriaempresa;

/**
 * Created by carlosx on 05-10-15.
 */
public class CategoriaEmpresa {
    private int idCategoriaEmp;
    private String nombreCategoriaEmp;
    private String descripcionCategoriaEmp;
    private int cantidadEmpresas;// cantidad de empresas que forman parte de esa categoria
    // inicializar a cero la cantidad cuando se crea una nueva

    public CategoriaEmpresa() {
    }

    public CategoriaEmpresa(int idCategoriaEmp, String descripcionCategoriaEmp, String nombreCategoriaEmp, int cantidadEmpresas) {
        this.idCategoriaEmp = idCategoriaEmp;
        this.descripcionCategoriaEmp = descripcionCategoriaEmp;
        this.nombreCategoriaEmp = nombreCategoriaEmp;
        this.cantidadEmpresas = cantidadEmpresas;
    }

    public int getIdCategoriaEmp() {
        return idCategoriaEmp;
    }

    public void setIdCategoriaEmp(int idCategoriaEmp) {
        this.idCategoriaEmp = idCategoriaEmp;
    }

    public String getNombreCategoriaEmp() {
        return nombreCategoriaEmp;
    }

    public void setNombreCategoriaEmp(String nombreCategoriaEmp) {
        this.nombreCategoriaEmp = nombreCategoriaEmp;
    }

    public String getDescripcionCategoriaEmp() {
        return descripcionCategoriaEmp;
    }

    public void setDescripcionCategoriaEmp(String descripcionCategoriaEmp) {
        this.descripcionCategoriaEmp = descripcionCategoriaEmp;
    }

    public int getCantidadEmpresas() {
        return cantidadEmpresas;
    }

    public void setCantidadEmpresas(int cantidadEmpresas) {
        this.cantidadEmpresas = cantidadEmpresas;
    }
}
