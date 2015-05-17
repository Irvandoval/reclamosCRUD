package com.example.irvandoval.reclamosgrupo17.usuario;

/**
 * Created by irvandoval on 05-09-15.
 */
public class Usuario {
   // private int idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String dui;
    private String email;
    private String telefono;
    private int edad;
    private String sexo;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String apellidoUsuario, String dui, String email, String telefono, int edad, String sexo) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.dui = dui;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
