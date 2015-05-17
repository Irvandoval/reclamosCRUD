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

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String apellidoUsuario, String dui, String email, String telefono, int edad) {
       // this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.dui = dui;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public String getDui() {
        return dui;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
