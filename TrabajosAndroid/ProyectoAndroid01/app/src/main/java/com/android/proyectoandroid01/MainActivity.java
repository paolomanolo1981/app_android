package com.android.proyectoandroid01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.proyectoandroid01.PkgDatos.ClaseSql;
import com.android.proyectoandroid01.PkgEntidades.EntidadPersona;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Locale;

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
                if(ValidarIngreso()){
                    EntidadPersona objPersona=new EntidadPersona();
                    objPersona.setCodigo(t_codigo.getText().toString().toUpperCase());
                    objPersona.setPaterno(t_paterno.getText().toString().toUpperCase());
                    objPersona.setMaterno(t_materno.getText().toString().toUpperCase());
                    objPersona.setNombre(t_nombres.getText().toString().toUpperCase());
                    objPersona.setSexo(elementosexo);
                    objPersona.setDireccion(t_direccion.getText().toString().toUpperCase());
                    objPersona.setDistrito(t_distrito.getText().toString().toUpperCase());

                    //Se envia el nombre de la actividad: MainActivity hacia el
                    //parametro contexto del constructor de la clase: ClaseSql
                    //En esta actividad --> MainActivity, se ejecuta los metodos de la clase: ClaseSql
                    //se crea un objeto data de tipo clase: ClaseSql, con un parámetro de tipo Actividad MainActivity
                    ClaseSql  data=new ClaseSql(MainActivity.this);

                   try{
                       data.AbrirBDD();//se usa el metodo para abrir la base de datos
                       data.Ingreso_Persona(objPersona);
                       data.CerrarBDD();

                   }catch(Exception e1){

                   }

                }

            }
        });
        b_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evento onClick del objeto b_salir
                finish(); //Cierra la actividad
            }
        });



    }
    //Validacion de Ingreso de datos
    private boolean ValidarIngreso(){ //retorna Verdadero o falso
        boolean validar=true;
        if(t_codigo.getText().toString().equalsIgnoreCase("")){ //si el objeto t_codigo esta vacio
            t_codigo.setError("INGRESE UN CODIGO");
            t_codigo.requestFocus();
            validar=false;
        }else{
            if(t_paterno.getText().toString().equalsIgnoreCase("")){
                t_paterno.setError("INGRESE APELLIDO PATERNO");
                t_paterno.requestFocus();
                validar=false;
            }else{
                if(t_materno.getText().toString().equalsIgnoreCase("")){
                    t_materno.setError("INGRESE APELLIDO MATERNO");
                    t_materno.requestFocus();
                    validar=false;
                }else{
                    if(t_nombres.getText().toString().equalsIgnoreCase("")){
                        t_nombres.setError("INGRESE NOMBRES");
                        t_nombres.requestFocus();
                        validar=false;
                    }else{
                        if(indicesexo==0){
                            sp_sexo.setError("SELECCIONE UN SEXO");
                            sp_sexo.requestFocus();
                            validar=false;
                        }else{
                            if(indicesexo>0){
                                sp_sexo.setError(null);
                            }else{
                                if(t_direccion.getText().toString().equalsIgnoreCase("")){
                                    t_direccion.setError("INGRESE DIRECCION");
                                    t_direccion.requestFocus();
                                    validar=false;
                                }else{
                                    if(t_distrito.getText().toString().equalsIgnoreCase("")){
                                        t_distrito.setError("INGRESE DISTRITO");
                                        t_distrito.requestFocus();
                                        validar=false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return validar; //retorna el valor de la variable validar (true o false)
    }



}