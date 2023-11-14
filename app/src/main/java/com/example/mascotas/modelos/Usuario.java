package com.example.mascotas.modelos;

import com.example.mascotas.R;

public class Usuario {
    private String nombresUsuario;
    private String apellidosUsuario;
    private String usuarioLogin;
    private String correo;
    private String contraseña;

    public Usuario(String nombresUsuario, String apellidosUsuario, String usuarioLogin, String correo, String contraseña) {
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuarioLogin = usuarioLogin;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return nombresUsuario + ',' +
                apellidosUsuario + ',' +
                usuarioLogin + ',' +
                correo + ',' +
                contraseña;
    }
}
