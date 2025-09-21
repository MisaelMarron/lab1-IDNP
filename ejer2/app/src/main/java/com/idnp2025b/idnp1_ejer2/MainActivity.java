package com.idnp2025b.idnp1_ejer2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.idnp2025b.idnp1_ejer2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String FILE = "file.txt";
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText edtNombres = binding.edtNombres;
        EditText edtApellidos = binding.edtApellidos;
        EditText edtCorreo = binding.edtCorreo;
        EditText edtTelefono = binding.edtTelefono;
        EditText edtCarrera = binding.edtCarrera;
        Button btnGuardar = binding.btnGuardar;
        Button btnHistorial = binding.btnHistorial;

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres = edtNombres.getText().toString();
                String apellidos = edtApellidos.getText().toString();
                String correo = edtCorreo.getText().toString();
                String telefono = edtTelefono.getText().toString();
                String carrera = edtCarrera.getText().toString();

                if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || carrera.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Se debe llenar todo los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                String datos =  "Nombres: "+edtNombres.getText().toString() + "\n" +
                                "Apellidos: "+edtApellidos.getText().toString() + "\n" +
                                "Correo: "+edtCorreo.getText().toString() + "\n" +
                                "Telefono: "+edtTelefono.getText().toString() + "\n" +
                                "Carrera: "+edtCarrera.getText().toString() + "\n";
                escribirArchivo(datos);

                Log.d(TAG, "Se guardo correctamente.\n"+datos);
                Toast.makeText(getApplicationContext(),"Se guardo correctamente.", Toast.LENGTH_SHORT).show();
                edtNombres.setText("");
                edtApellidos.setText("");
                edtCorreo.setText("");
                edtTelefono.setText("");
                edtCarrera.setText("");
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Apretar historial.", Toast.LENGTH_SHORT).show();
                leerArchivo();
            }
        });

    }

    private void escribirArchivo(String texto){
        try {
            FileOutputStream fos = openFileOutput(FILE, MODE_APPEND); // Se agrega al final
            fos.write(texto.getBytes());
            fos.close();
            Log.d(TAG, "Datos guardados correctamente.");
        } catch (Exception e) {
            Log.d(TAG, "No se guardo lo datos.",e);
        }
    }

    private void leerArchivo() {
        try {
            FileInputStream fis = openFileInput(FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                Log.d(TAG, linea);
            }
            reader.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "El archivo no existe todavía. Guarda datos primero.");
        } catch (Exception e) {
            Log.e(TAG, "Error al leer archivo", e);
        }
    }

}