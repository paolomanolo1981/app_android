package com.android.proyectoandroid01.PkgDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    //se crea un método de ListadoPersona de tipo String
    public String ListadoPersona(){
        String[] columnas=new String[]{
                "codpersona", "patpersona"  ,"patpersona","matpersona","nompersona","sexopersona","direpersona","distpersona"
        };
        //se crea un objeto cursor de tipo clase Cursor
        //se utiliza el método query, para realizar el listado de datos de la tabla Persona
        //Primer parametro es el nombre de la tabla Persona
        //Segundo parametro es el nombre de las columanas en forma de array
        Cursor cursor=db.query("Persona",columnas,null, null, null,null,null);
        String resultado="";//se declara una variable d tipo String
        //se obtiene el indice d cada columna por medio del método getColumnIndex
        //el indice de la columna se almacena hacia una variable d tipo entero
        int indiceCodigo=cursor.getColumnIndex("codpersona");
        int indicePaterno=cursor.getColumnIndex("patpersona");
        int indiceMaterno=cursor.getColumnIndex("matpersona");
        int indiceNombre=cursor.getColumnIndex("nompersona");
        int indiceSexo=cursor.getColumnIndex("sexopersona");
        int indiceDireccion=cursor.getColumnIndex("direpersona");
        int indiceDistrito=cursor.getColumnIndex("distpersona");

        String raya="======================";
        //para el manejo de las filas, se utiliza la setencia de repeticion for
        //primer parametro => curso.moveToFirst()=> primera fila
        //segundo parametro => !cursor.isAfterLast()=> que no se ubigue despues de la ultima fila
        //tercer parametro es recorrer fila por fila e ir a la siguiente fila
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            resultado+= raya+"\n"+"CODIGO: " + cursor.getString(indiceCodigo)  +"\n"+"PATERNO: " + cursor.getString(indicePaterno) +"\n"+
                             "MATERNO: " + cursor.getString(indiceMaterno) +"\n"+  "NOMBRE: " + cursor.getString(indiceNombre) +"\n"+
                             "SEXO: " + cursor.getString(indiceSexo) +"\n"+ "DIRECCION: " + cursor.getString(indiceDireccion) +"\n"+ "DISTRITO: " + cursor.getString(indiceDistrito) +"\n" + raya + "\n";



        }
        return  resultado;
    }

}

