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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IniciarSesion extends AppCompatActivity {
    EditText edtUsuarioLogin, edtContraseñaLogin;
    Button btnIniciarSesion;
    TextView tvRegistrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        initComponentes();
        listeners();
    }

    private void initComponentes() {
        edtUsuarioLogin = findViewById(R.id.edt_user_usuario_login);
        edtContraseñaLogin = findViewById(R.id.edt_contraseña_login);
        tvRegistrarUsuario = findViewById(R.id.tv_registrar_usuario_login);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion_login);
    }

    private void listeners() {
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validarDatos = validaDatos(edtUsuarioLogin.getText().toString(), edtContraseñaLogin.getText().toString());
                if (validarDatos) {
                    Intent intentHome = new Intent(getApplicationContext(), Principal.class);
                    startActivity(intentHome);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese usuario y contraseña válidos", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistrarUsuario = new Intent(getApplicationContext(), RegistroUsuario.class);
                startActivity(intentRegistrarUsuario);
            }
        });
    }

    private boolean validaDatos(String usuarioLogin, String contrasenaLogin) {
        boolean datosCorrectos;
        File archivo = new File(getFilesDir(), "registroUsuarios.txt");
        if (archivo.exists()) {
            try {
                FileReader lectorArchivo = new FileReader(archivo);
                BufferedReader bufferedLectorArchivo = new BufferedReader(lectorArchivo);
                String linea;
                ArrayList<Usuario> listaUsuariosRecuperados = new ArrayList<>();

                while ((linea = bufferedLectorArchivo.readLine()) != null) {
                    String usuarioRegistrado[] = linea.split(",");

                    Usuario usuarioRecuperado = new Usuario(
                            usuarioRegistrado[0],
                            usuarioRegistrado[1],
                            usuarioRegistrado[2],
                            usuarioRegistrado[3],
                            usuarioRegistrado[4]
                    );
                    listaUsuariosRecuperados.add(usuarioRecuperado);
                }

                boolean usuarioEncontrado = buscarUsuario(listaUsuariosRecuperados, usuarioLogin);

                if (usuarioEncontrado) {
                    boolean contrasenaCorrecta = contrasenaCorrecta(listaUsuariosRecuperados, contrasenaLogin);
                    if (contrasenaCorrecta) {
                        datosCorrectos = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique los datos de ingreso", Toast.LENGTH_LONG).show();
                        datosCorrectos = false;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique los datos de ingreso", Toast.LENGTH_LONG).show();
                    datosCorrectos = false;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Toast.makeText(getApplicationContext(), "No hay archivo de registro de usuarios", Toast.LENGTH_LONG).show();
            datosCorrectos = false;
        }
        return datosCorrectos;
    }

    private boolean buscarUsuario(ArrayList<Usuario> listaUsuariosConsultar, String usuarioLogin) {
        boolean usuarioEncontrado = false;
        for (int i = 0; i < listaUsuariosConsultar.size(); i++) {
            if (usuarioLogin.equals(listaUsuariosConsultar.get(i).getUsuarioLogin().toString()) ||
                    usuarioLogin.equals(listaUsuariosConsultar.get(i).getCorreo().toString())) {
                usuarioEncontrado = true;
                return usuarioEncontrado;
            }
        }
        return usuarioEncontrado;
    }

    private boolean contrasenaCorrecta(ArrayList<Usuario> listaUsuariosRecuperados, String contrasenaLogin) {
        boolean contrasenaCorrecta = false;
        for (int i = 0; i < listaUsuariosRecuperados.size(); i++) {
            if (contrasenaLogin.equals(listaUsuariosRecuperados.get(i).getContraseña().toString())) {
                contrasenaCorrecta = true;
            }
            return contrasenaCorrecta;
        }
        return contrasenaCorrecta;
    }

}