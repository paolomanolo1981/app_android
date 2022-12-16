package com.android.proyectoandroid01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.proyectoandroid01.PkgDatos.ClaseSql;

public class Listado extends AppCompatActivity {

    TextView tv_listado;
    Button bt_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        AgregarReferenciaListado();
    }

    private void AgregarReferenciaListado() {
        tv_listado=findViewById(R.id.tvListado);
        bt_cerrar=findViewById(R.id.btnCerrar);

        try{
            //se crea un objeto datalista de tipo ClaseSql
            //Se le envia al parametro del constructor de la clase: ClaseSql, el nombre de la actividad Listado
            //con this

            ClaseSql datalista=new ClaseSql(this);
            datalista.AbrirBDD();//se abre la bd
            String listadatos=datalista.ListadoPersona();//el resultado del metodo: ListadoPersona, se almacena hacia la variable listadatos
            datalista.CerrarBDD();

            tv_listado.setText(listadatos);//se vuelca el resultado al textview


        }catch (SQLException e1){

        }

        bt_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}