package com.example.tl01examen0882;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] items = {"Honduras", "Guatemala", "El Salvador","Nicaragua","Costa Rica"};
    AutoCompleteTextView attxt;
    ArrayAdapter<String> adapterItems;

    EditText nombre, telefono, notas;

    Button BtnSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Datos Seleccionar Pais
        attxt = findViewById(R.id.a_t_txt);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,items);

        attxt.setAdapter(adapterItems);
        attxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posistion, long id) {
                String item = parent.getItemAtPosition(posistion).toString();
                Toast.makeText(getApplicationContext(), "Item", Toast.LENGTH_SHORT).show();
            }
        });

        //Validacion de Datos
        nombre = (EditText) findViewById(R.id.TxtNombre);
        telefono =(EditText) findViewById(R.id.TxtTelefono);
        notas = (EditText) findViewById(R.id.TxtNotas);

        //Boton
        BtnSave = (Button) findViewById(R.id.BtnSave);
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agregar();
            }
        });
    }

    //Agregar Informacion
    private void Agregar()
    {
        if (Validar())
        {
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
        }
        try {
            ConexionSQLite conexion = new ConexionSQLite(this,
                    Transaccion.NameDatabase,
                    null,
                    1);

            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transaccion.nombres, nombre.getText().toString());
            valores.put(Transaccion.Telefono, telefono.getText().toString());
            valores.put(Transaccion.Notas, notas.getText().toString());
            valores.put(Transaccion.Pais, attxt.getText().toString());

            Long resultado = db.insert(Transaccion.tablaperson, Transaccion.nombres, valores);

            Toast.makeText(this, "Ingresado con exito", Toast.LENGTH_SHORT).show();

            CleanPantalla();
        }
        catch (Exception ex)
        {
            ex.toString();
        }

    }

    //Metodo Para Limpiar pantala
    private void CleanPantalla() {
        nombre.setText("");
        telefono.setText("");
        notas.setText("");
        attxt.setText("");
    }

    //Metodo para validar EditText si estan vacios o no
    public boolean Validar(){
        boolean retorno = true;
        String c1 = nombre.getText().toString();
        String c2 = telefono.getText().toString();
        String c3 = notas.getText().toString();
        String c4 = attxt.getText().toString();

        if(c1.isEmpty())
        {
            nombre.setError("Este Campo no puede quedar vacio");
            retorno =false;
        }
        if (c2.isEmpty())
        {
            telefono.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c3.isEmpty())
        {
            notas.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c4.isEmpty())
        {
            attxt.setError("Este Campo no puede quedar vacio");
            retorno=false;
        }
        return retorno;
    }
}