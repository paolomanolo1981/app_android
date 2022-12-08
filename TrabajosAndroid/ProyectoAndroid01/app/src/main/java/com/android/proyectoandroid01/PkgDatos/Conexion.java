package com.android.proyectoandroid01.PkgDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//La clase conexion hereda los metodos de la clase SQLiteOpenHelper
public class Conexion extends SQLiteOpenHelper {
    //Se crea una base de datos: BDPersona.bd
    private static final String BDD="BDPersona.bd";
    //Se crea una variable Version de tipo entero cuyo valor inicial es uno
    private static final int Version=1;
    private String Sql="create table Persona(id integer primary key autoincrement,codpersona text," +
                       "patpersona text,matpersona text,nompersona text,sexopersona text," +
                        "direpersona text, distpersona text)";

    //Constructor de la clase Conexion
    public Conexion(Context context) {
        //Primer parametro = Es el contexto
        //Segundo parametro= Es la base de datos
        //Tercer parametro es nulo
        //Cuarto Parametro = Es la version
        super(context,BDD,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //Por medio del metodo execSQL del paremetro db, se ejecuta la sentencia transact sql
       db.execSQL(Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Metodo onUpgrade, para actualizar la tabla
        //si existe la tabla Persona, se elimina la tabla
        db.execSQL("Drop table if exists Persona");
        //Se crea la tabla Persona
        db.execSQL(Sql);
    }
}
