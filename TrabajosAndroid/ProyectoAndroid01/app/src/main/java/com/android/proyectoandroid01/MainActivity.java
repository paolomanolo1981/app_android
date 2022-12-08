package com.android.proyectoandroid01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity {
 //Se generan los objetos de tipo clase EditText
 EditText t_codigo,t_paterno,t_materno,t_nombres,t_direccion,t_distrito;
 //Se crea un objeto de tipo clase MaterialSpinner
 MaterialSpinner sp_sexo;
 //Se generan los objetos de tipo clase Button
 Button b_nuevo,b_grabar,b_salir;
 //Se declaran las variables
 int indicesexo; //captura el indice del elemento seleccionado
 String elementosexo; //captura el texto del elemento seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se utiliza al metodo Agregar_Referencias_Principal
        Agregar_Referencias_Principal();
    }

    private void Agregar_Referencias_Principal() {
        //findViewById --> El metodo findViewById, Encuentra una vista por su Id (Identificador)
        //el objeto t_codigo se relaciona con la vista tcodigo
        //t_codigo=findViewById(R.id.tcodigo);
        // objeto =  metodo           vista
        t_codigo=findViewById(R.id.tcodigo);
        t_paterno=findViewById(R.id.tpaterno);
        t_materno=findViewById(R.id.tmaterno);
        t_nombres=findViewById(R.id.tnombres);
        t_direccion=findViewById(R.id.tdireccion);
        t_distrito=findViewById(R.id.tdistrito);
        sp_sexo=findViewById(R.id.mssexo);

        //evento del objeto sp_sexo
        sp_sexo.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                //se captura el indice del elemento
                indicesexo=sp_sexo.getSelectedIndex();
                //se captura el texto del elemento
                elementosexo=sp_sexo.getText().toString();


            }
        });

        //sp_sexo.setItems("SEXO...","MASCULINO","FEMENINO");
        //Indice de la opcion: 0         1           2
        sp_sexo.setItems("SEXO...","MASCULINO","FEMENINO");
        b_nuevo=findViewById(R.id.bnuevo);
        b_grabar=findViewById(R.id.bgrabar);
        b_salir=findViewById(R.id.bsalir);
        //Eventos de los botones
        b_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Evento onClick del objeto b_nuevo
               //Limpiar los cuadros de
               t_codigo.setText("");
               t_paterno.setText("");
               t_materno.setText("");
               t_nombres.setText("");
               sp_sexo.setSelectedIndex(0);
               t_direccion.setText("");
               t_distrito.setText("");
               //Para tomar el enfoque del objeto
               t_codigo.requestFocus();
            }
        });
        b_grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evento onClick del objeto b_grabar

            }
        });
        b_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evento onClick del objeto b_salir
                finish(); //Cierra la actividad
            }
        });

        //validacion de ingreso de datos
        private boolean ValidarIngreso(){//retorna verdadero o falso
            boolean validar=true;
            if(t_codigo.getText().toString().equalsIgnoreCase("")){
                t_codigo.setError("Ingresa un código");
                t_codigo.requestFocus();
                validar=false;

            }else{

                if(t_paterno.getText().toString().equalsIgnoreCase("")){
                    t_paterno.setError("Ingresa el apellido paterno");
                    t_paterno.requestFocus();
                    validar=false;

                }else{
                    if(t_materno.getText().toString().equalsIgnoreCase("")){
                        t_materno.setError("Ingresa el apellido materno");
                        t_materno.requestFocus();
                        validar=false;

                    }
                }
            }


        }

    }
}