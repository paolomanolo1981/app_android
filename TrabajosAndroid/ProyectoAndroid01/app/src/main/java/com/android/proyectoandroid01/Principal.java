package com.android.proyectoandroid01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity implements View.OnClickListener{
//se crean los objetos de tipo clase Button
    Button bt_ingreso, bt_listado, bt_busqueda, bt_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        AgregarReferenciaPrincipal();
    }

    private void AgregarReferenciaPrincipal() {
        bt_ingreso=findViewById(R.id.btningreso);
        bt_listado=findViewById(R.id.btnlistado);
        bt_busqueda=findViewById(R.id.btnbusqueda);
        bt_salir=findViewById(R.id.btnsalirprincipal);
        //los eventos de los objetos de tipo clase button se ejecutan en la actividad actual
        //this => Es la actividad actual, en este caso principal
        bt_ingreso.setOnClickListener(this);
        bt_listado.setOnClickListener(this);
        bt_busqueda.setOnClickListener(this);
        bt_salir.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();//se captura el id del boton y se almacena en la variable id
        //con el boton: Ingreso
        //si el id es igual al id de la vista: btningreso
        if(id==R.id.btningreso){
            //Intent=> permite llamar a otra actividad
            //se crea un objeto vingreso de tipo de clase intent
            //primer argumenteo es el contexto de la actividad actual
            //segundo argumento es el nombre de la actividad a llamar

            Intent vingreso=new Intent(getApplicationContext(),MainActivity.class);
            //se inicia la actividad MainActivity, por medio del objeto vingreso
            startActivity(vingreso);

        }

        if(id==R.id.btnlistado){
            Intent vListado=new Intent(getApplicationContext(),Listado.class);
            startActivity(vListado);
        }

        if(id==R.id.btnbusqueda){

        }

        if(id==R.id.btnsalirprincipal){
            finish();
        }


    }
}