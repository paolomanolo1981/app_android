package com.android.proyectoandroid01.PkgDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.android.proyectoandroid01.PkgEntidades.EntidadPersona;

public class ClaseSql {
    //Se declara un objeto contexto de tipo clase Context
    private final Context contexto;
    //Se declara un objeto cn de tipo clase Conexion
    private Conexion cn;
    //Se declara un objeto db de tipo clase SQLiteDatabase
    private SQLiteDatabase db;
    //Constructor de la clase ClaseSql
    public ClaseSql(Context contexto) {
        this.contexto = contexto;
    }

    //Se crea un metodo AbrirBDD de tipo ClaseSql con una excepcion
    public ClaseSql AbrirBDD() throws SQLException{
      //Se crea al objeto cn de tipo clase Conexion
      //con un parametro conexto de tipo clase Context
      cn=new Conexion(contexto);
      //Permiso de : Escritura y lectura de una tabla
      //que se encuentra en una base de datos
      db=cn.getWritableDatabase();
      return this; //retorna la conexion
    }

    //Se crea un metodo para cerrar una BDD
    public void CerrarBDD(){
       cn.close();
    }

    //Se crea un metodo Ingreso_Persona
    //Con un parametro entpersona de tipo clase EntidadPersona
    public Long Ingreso_Persona(EntidadPersona entpersona){
        //La clase ContentValues, es una clase contenedora de valores
        //se crea objeto cv de tipo clase ContentValues
        ContentValues cv=new ContentValues();
        //cv.put(campo,valor)
        //Se envian los valores de los atributos hacias los campos de la tabla,
        //por medio de sus propiedad (get)
        cv.put("codpersona",entpersona.getCodigo());
        cv.put("patpersona",entpersona.getPaterno());
        cv.put("matpersona",entpersona.getMaterno());
        cv.put("nompersona",entpersona.getNombre());
        cv.put("sexopersona",entpersona.getSexo());
        cv.put("direpersona",entpersona.getDireccion());
        cv.put("distpersona",entpersona.getDistrito());
        //Se retorna por medio de un insert los valores hacia la tabla Persona,
        //utilizando al objeto cv de tipo clase: ContentValues
        return db.insert("Persona",null,cv);
    }
}

