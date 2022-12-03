package com.example.intercambiolibros;

import android.os.Bundle;
import android.text.format.DateFormat;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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


                Toast.makeText(getActivity().getApplicationContext(), "paso 1", Toast.LENGTH_LONG).show();
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



      //  return inflater.inflate(R.layout.fragment_first, container, false);
        return vieww;
    }

    private void displayChatMessages() {

       ListView listOfMessages = (ListView) vieww.findViewById(R.id.list_of_messages);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("chats")
                .limitToLast(50)
                ;

        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage.class)
                .setLayout(R.layout.fragment_first  )
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