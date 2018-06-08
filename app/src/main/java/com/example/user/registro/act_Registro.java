package com.example.user.registro;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.registro.Modelo.Estudiante;
import com.example.user.registro.Modelo.Registro;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class act_Registro extends AppCompatActivity {

    EditText txtNombre, txtCarnet, txtCarrera;
    Button btnAgregar, btnModificar, btnEliminar, btnBuscar, btnLlamar;
    Estudiante estudiante;
    Registro registroE = new Registro();

    ArrayAdapter adapter;
    ListView listaE;

    Adapter adapterP;

    Uri fotoTemp;
    ImageView imgUser;

    private final int GALERIA_IMAGENES=1;
    private final int CAMARA=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtCarnet=(EditText)findViewById(R.id.txtCarnet);
        txtCarrera=(EditText)findViewById(R.id.txtCarrera);

        btnAgregar=findViewById(R.id.btnAgregar);
        btnBuscar=findViewById(R.id.btnBuscar);
        btnModificar=findViewById(R.id.btnModificar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnLlamar=findViewById(R.id.btnLlamar);

        listaE=findViewById(R.id.listaE);
        imgUser=findViewById(R.id.imgUser);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNombre.getText().toString().isEmpty()|| txtCarnet.getText().toString().isEmpty()
                        ||txtCarrera.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Por favor llene todos los espacios", Toast.LENGTH_SHORT).show();
                }else
                    if(registroE.buscarEstudiante(txtCarnet.getText().toString())==-1){
                    estudiante=new Estudiante(txtNombre.getText().toString(),
                            txtCarnet.getText().toString(),txtCarrera.getText().toString(),fotoTemp);

                    String mensaje= registroE.agregarEstudiante(estudiante);

                    Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                limpiar();
                //adapter=new ArrayAdapter(act_Registro.this,android.R.layout.simple_list_item_1,registroE.devolverLista());
                //listaE.setAdapter(adapter);
                        adapterP = new Adapter(act_Registro.this,registroE.devolverLista());
                        listaE.setAdapter(adapterP);
                }else{
                        Toast.makeText(getApplicationContext(),"El Transporte ya se encuentra agregado",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNombre.getText().toString().isEmpty()||
                 txtCarnet.getText().toString().isEmpty()||
                 txtCarrera.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Por favor " +
                            "llene todos los espacios",Toast.LENGTH_SHORT).show();
                }else{
                    int posicion=registroE.buscarEstudiante(txtCarnet.getText().toString());
                    if(posicion!=-1){
                        estudiante= new Estudiante(txtNombre.getText().toString(),
                                txtCarnet.getText().toString(),txtCarrera.getText().toString(),fotoTemp);
                        String mensaje=registroE.modificarEstudiante(estudiante,posicion);
                        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                        limpiar();
                    }else{
                        Toast.makeText(getApplicationContext(),"No existe el Transporte",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCarnet.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Por favor digite el carnet del" +
                            " estudiante a eliminar",Toast.LENGTH_SHORT).show();
                }else{
                    String carnet=txtCarnet.getText().toString();
                    if(registroE.buscarEstudiante(carnet)!=-1){
                        String mensaje=registroE.eliminarEstudiante(registroE.buscarEstudiante(carnet));
                        limpiar();
                        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"No existe el Transporte",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(act_Registro.this,act_lista.class);
                ArrayList<Estudiante> lista_Estudiante = registroE.devolverLista();
                // Se realiza un bundle asociado al intent
                intent.putParcelableArrayListExtra("Lista", lista_Estudiante);
                startActivity(intent);
                /*if (txtCarnet.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Por favor digitar el carnet" +
                            "del estudiante a buscar",Toast.LENGTH_SHORT).show();
                }else{
                    String carnet=txtCarnet.getText().toString();
                    if(registroE.buscarEstudiante(carnet)!=-1){
                        //String mensaje=registroE.getInfoEstudiante(registroE.buscarEstudiante(carnet));
                        //Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
                        estudiante=registroE.devolverEstudiante(registroE.buscarEstudiante(carnet));
                        txtNombre.setText(estudiante.getNombre());
                        txtCarrera.setText(estudiante.getCarrera());

                    }else{
                        Toast.makeText(act_Registro.this,"No se encontro el estudiante"
                                ,Toast.LENGTH_SHORT).show();
                    }
                }*/
            }
        });// fin buscar

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(act_Registro.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    int estadoPermiso = 1;
                    ActivityCompat.requestPermissions(act_Registro.this, new String[]{
                            Manifest.permission.CALL_PHONE},estadoPermiso);
                }else {
                    startActivity( new Intent(Intent.ACTION_CALL,Uri.parse
                            ("Tel: "+txtCarnet.getText().toString())) );
                }
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Este fragmento es para mostrar un mensaje de confirmación antes de realizar una acción
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(act_Registro.this);
                alertDialog.setMessage("¿Que desea Utilizar?");
                alertDialog.setTitle("Selección de la Imagen de Usuario");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(true);

                //Al boton positivo le doy la opcion de la  galeria y si los selecciona realizo un intent para obtener los datos
                alertDialog.setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        //Para recibir un resultado cuando se inicia un activity
                        // se llama al startActivityForResult
                        startActivityForResult(Intent.createChooser(intent,"Seleccionar foto"),
                                GALERIA_IMAGENES);

                    }
                });
                //Al boton negativo se le da la opcion de camara
                alertDialog.setNegativeButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,CAMARA);
                    }
                });
                alertDialog.show();
            }
        });
        listaE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estudiante = registroE.devolverEstudiante(position);
                txtNombre.setText(estudiante.getNombre());
                txtCarnet.setText(estudiante.getCarnet());
                txtCarrera.setText(estudiante.getCarrera());
                imgUser.setImageURI(estudiante.getImgUser());

            }
        });
    }//Fin del onCreate
    /*Este metodo recibe 3 argumentos
    El código de la solicitud que se envio por medio startActivityForResult
    Un codigo de resultado(Este puede ser Result_OK si la accion se ejcuto correctamente
    o Result_Canceled si el usuario cancelo la operacion)
    Un intent con la informacion
    */
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode==RESULT_OK){
            switch(reqCode){
                // Al fotoTemp se le asigna los datos que se encuentran el intent data
                //Al imageView de user se le carga la direccion de la imagen que acabo
                // de guardar en fotoTemp para que se haga visible en la aplicacion
                case GALERIA_IMAGENES:
                    fotoTemp = data.getData();
                    imgUser.setImageURI(fotoTemp);
                    Toast.makeText(getApplicationContext(), "Imagen cargada correctamente",
                            Toast.LENGTH_SHORT).show();
                    break;
                case CAMARA:
                    if (data!=null){
                        Bitmap thumbail = (Bitmap)data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        thumbail.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                        File destination = new File(Environment.getExternalStorageDirectory(),
                                System.currentTimeMillis()+".jpg");
                        FileOutputStream fo;
                        try{
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        imgUser.setImageBitmap(thumbail);
                        fotoTemp = data.getData();
                        Toast.makeText(getApplicationContext(),"Exito Camara",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }else{
            Toast.makeText(getApplicationContext(),"Ha ocurrido un error" +
                    " en la accion",Toast.LENGTH_SHORT).show();
        }
    }//Fin del metodo
    public void limpiar(){
        txtCarrera.setText("");
        txtNombre.setText("");
        txtCarnet.setText("");
    }
}//Fin de la clase
