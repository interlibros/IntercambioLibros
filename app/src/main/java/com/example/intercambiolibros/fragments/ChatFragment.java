package com.example.intercambiolibros.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intercambiolibros.R;
import com.example.intercambiolibros.clases.AdapterMensajes;
import com.example.intercambiolibros.clases.MensajeEnviar;
import com.example.intercambiolibros.clases.MensajeRecibir;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends Fragment {


    public ChatFragment() {
        // require a empty public constructor
    }


    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private ImageButton btnEnviarFoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle   savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        //write = (EditText)rootView.findViewById(R.id.editText1);
        //getView().setContentView(R.layout.fragment_first);

        fotoPerfil =  rootView.findViewById(R.id.fotoPerfil);
        nombre = rootView.findViewById(R.id.nombre);

        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String user = preferences.getString("email","");
        nombre.setText(user);

        rvMensajes = (RecyclerView) rootView.findViewById(R.id.rvMensajes);
        txtMensaje = (EditText) rootView.findViewById(R.id.txtMensaje);
        btnEnviar = (Button) rootView.findViewById(R.id.btnEnviar);

        fotoPerfilCadena = "";

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();

        adapter = new AdapterMensajes(getActivity(),user);
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);

        //


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new MensajeEnviar(txtMensaje.getText().toString(),nombre.getText().toString(),fotoPerfilCadena,"1", ServerValue.TIMESTAMP));
                txtMensaje.setText("");
            }
        });



        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;

    }


}