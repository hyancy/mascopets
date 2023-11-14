package com.example.mascotas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mascotas.R;
import com.example.mascotas.modelos.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class RegistroUsuario extends AppCompatActivity {
    EditText edtNombresUsuario, edtApellidosUsuario, edtUserUsuario, edtCorreo, edtConfirmarCorreo,
            edtContraseña, edtConfirmarContraseña;
    Button btnCrearUsuario;
    TextView tvIniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        initComponents();
        listeners();
    }

    private void initComponents() {
        edtNombresUsuario = findViewById(R.id.edt_nombres_usuario);
        edtApellidosUsuario = findViewById(R.id.edt_apellidos_usuario);
        edtUserUsuario = findViewById(R.id.edt_user_usuario);
        edtCorreo = findViewById(R.id.edt_correo_electronico);
        edtConfirmarCorreo = findViewById(R.id.edt_confirmar_correo_electronico);
        edtContraseña = findViewById(R.id.edt_contraseña);
        edtConfirmarContraseña = findViewById(R.id.edt_confirmar_contraseña);
        btnCrearUsuario = findViewById(R.id.btn_registrar_usuario_register);
        tvIniciarSesion = findViewById(R.id.tv_iniciar_sesion_register);
    }

    private void listeners() {
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validarCampos = validarCamposCompletos();
                boolean validarContraseña = confirmarContraseña();
                boolean validarCorreo = confirmaCorreo();
                if (validarCampos == true) {
                    if (validarContraseña) {
                        if (validarCorreo) {
                            crearArchivo(usuarioRegistrar());
                        } else {
                            Toast.makeText(getApplicationContext(), "Verificar el correo electrónico", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Verificar la contraseña", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIniciarSesion = new Intent(getApplicationContext(), IniciarSesion.class);
                startActivity(intentIniciarSesion);
            }
        });
    }

    public Usuario usuarioRegistrar() {
        Usuario usuarioRegistrar = new Usuario(
                edtNombresUsuario.getText().toString(),
                edtApellidosUsuario.getText().toString(),
                edtUserUsuario.getText().toString(),
                edtCorreo.getText().toString(),
                edtContraseña.getText().toString()
        );
        return usuarioRegistrar;
    }

    public void crearArchivo(Usuario usuarioRegistrar) {
        File archivo = new File(getFilesDir(), "registroUsuarios.txt");
        if (!archivo.exists()) {
            try {
                FileWriter escritorArchivo = new FileWriter(archivo);
                escritorArchivo.write(usuarioRegistrar.toString() + "\n");
                escritorArchivo.flush();
                escritorArchivo.close();
                Toast.makeText(getApplicationContext(), "Usuario registrado con éxito!!!", Toast.LENGTH_LONG).show();
                limpiarCampos();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "No se pudo registar el usuario!!!", Toast.LENGTH_LONG).show();
            }
        } else {
            try {
                Files.write(Paths.get(getFilesDir() + "/registroUsuarios.txt"), usuarioRegistrar.toString().getBytes(), StandardOpenOption.APPEND);
                Toast.makeText(getApplicationContext(), "Usuario registrado con éxito!!!", Toast.LENGTH_LONG).show();
                limpiarCampos();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "No se pudo registar el usuario!!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validarCamposCompletos() {
        boolean validarCampo;
        if (
                !edtNombresUsuario.getText().toString().equals("") &&
                        !edtApellidosUsuario.getText().toString().equals("") &&
                        !edtUserUsuario.getText().toString().equals("") &&
                        !edtCorreo.getText().toString().equals("") &&
                        !edtConfirmarCorreo.getText().toString().equals("") &&
                        !edtContraseña.getText().toString().equals("") &&
                        !edtConfirmarContraseña.getText().toString().equals("")

        ) {
            validarCampo = true;
        } else {
            validarCampo = false;
        }
        return validarCampo;
    }

    public boolean confirmarContraseña() {
        boolean validarContraseña;
        if (edtContraseña.getText().toString().equals(edtConfirmarContraseña.getText().toString())) {
            validarContraseña = true;
        } else {
            validarContraseña = false;
        }
        return validarContraseña;
    }

    public boolean confirmaCorreo() {
        boolean validarCorreo;
        if (edtCorreo.getText().toString().equals(edtConfirmarCorreo.getText().toString())) {
            validarCorreo = true;
        } else {
            validarCorreo = false;
        }
        return validarCorreo;
    }

    public void limpiarCampos() {
        edtNombresUsuario.setText("");
        edtApellidosUsuario.setText("");
        edtUserUsuario.setText("");
        edtCorreo.setText("");
        edtConfirmarCorreo.setText("");
        edtContraseña.setText("");
        edtConfirmarContraseña.setText("");
    }
}