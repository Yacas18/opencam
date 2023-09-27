package com.main.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnCamara;   // Declaración del botón para abrir la cámara
    ImageView imgView;  // Declaración de un ImageView para mostrar la imagen capturada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de los elementos de la interfaz de usuario
        btnCamara = findViewById(R.id.btnCamara);  // Asociación del botón por su ID
        imgView = findViewById(R.id.imageView);    // Asociación del ImageView por su ID

        // Configuración de un listener para el botón que se ejecuta cuando se hace clic
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();  // Llama a la función para abrir la cámara cuando se hace clic en el botón
            }
        });
    }

    private void abrirCamara(){
        // Creación de un intent para abrir la cámara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Verifica si existe una actividad que pueda manejar el intent (abrir la cámara)
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);  // Inicia la actividad de la cámara y espera un resultado
        }
    }

    // Método llamado cuando la actividad de la cámara se cierra y retorna un resultado
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Obtiene los datos (imagen capturada) del intent
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");

            // Muestra la imagen capturada en el ImageView
            imgView.setImageBitmap(imgBitmap);
        }
    }
}
