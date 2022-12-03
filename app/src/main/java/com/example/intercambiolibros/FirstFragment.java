package com.example.intercambiolibros;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

public class FirstFragment extends Fragment {

    private FirebaseListAdapter<ChatMessage> adapter;
    private FloatingActionButton fab;

    View vieww;

    public FirstFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


      vieww = inflater.inflate(R.layout.fragment_first, container, false);
        fab = (FloatingActionButton) vieww.findViewById(R.id.fab);

        displayChatMessages();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // Toast.makeText(getActivity().getApplicationContext(), "paso 1", Toast.LENGTH_LONG).show();
                EditText input = (EditText) vieww.findViewById(R.id.input);



                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        . child("Chats")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );

                // Clear the input
                input.setText("");
            }
        });

        final TextView textview = (TextView) vieww.findViewById(R.id.textView );

        //Instancia a la base de datos
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //apuntamos al nodo que queremos leer
        DatabaseReference myRef = fdb.getReference("Chats");

        //Agregamos un ValueEventListener para que los cambios que se hagan en la base de datos
        //se reflejen en la aplicacion
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                //leeremos un objeto de tipo Estudiante
                GenericTypeIndicator<ChatMessage > t = new GenericTypeIndicator<ChatMessage>() {};
                ChatMessage  estudiante = dataSnapshot.getValue(t);

                //formamos el resultado en un string
               String resultado = "Como objeto java:\n\n";
//                resultado += estudiante + "\n";
//                resultado += "Propiedad Estudiante:\nNombre completo: " +estudiante.getMessageText() ;

                //Tambien podemos leer los datos como un string
                resultado += "\n\n-----------------------------\n\n";
                resultado += "Como JSON:\n\n";
                resultado += dataSnapshot.getValue().toString();


               // resultado += dataSnapshot.child("messageText").toString()+  "\n";

//                //leemos un nodo hijo del nodo estudiante
//                resultado += "\n Key: " + dataSnapshot.child("nombre_completo").getKey()+"\n";
//                resultado += "\n Valor: " + dataSnapshot.child("nombre_completo").getValue(String.class);

                //mostramos en el textview
                textview.setText(resultado);
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });



      //  return inflater.inflate(R.layout.fragment_first, container, false);
        return vieww;
    }

    private void displayChatMessages() {

       ListView listOfMessages = (ListView) vieww.findViewById(R.id.list_of_messages);

        DatabaseReference  query = FirebaseDatabase.getInstance()
                .getReference()
               .child("Chats");
         Toast.makeText(getActivity().getApplicationContext(), query.getParent().toString() , Toast.LENGTH_LONG).show();

        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
                .setLayout(R.layout.message )
                .setQuery(query, ChatMessage.class)
                .build();

        adapter = new FirebaseListAdapter<ChatMessage>(options) {


            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView) v.findViewById(R.id.message_text);
                TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

}