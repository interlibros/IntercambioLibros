package com.example.intercambiolibros.clases;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.intercambiolibros.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 04/09/2017. 04
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje> {

    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context c;
    private String user;
  //  SharedPreferences preferences = c.getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
   // SharedPreferences.Editor editor = preferences.edit();

    public AdapterMensajes(Context c,String user) {
        this.c = c;
        this.user = user;
    }

    public void addMensaje(MensajeRecibir m){
        listMensaje.add(m);
        notifyItemInserted(listMensaje.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);

        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {


        holder.getNombre().setText(listMensaje.get(position).getNombre());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje());

//        if(listMensaje.get(position).getType_mensaje().equals("1")){
//            holder.getFotoMensaje().setVisibility(View.GONE);
//            holder.getMensaje().setVisibility(View.VISIBLE);
//            //holder.itemView.setBackgroundResource(R.color.md_theme_dark_error) ;
//
//        }
            String n = listMensaje.get(position).getNombre().toString();

        if(n.equals(user)){
            //holder.itemView.setBackgroundResource(R.color.md_theme_dark_primary) ;
            holder.itemView.getRootView().setBackgroundColor(Color.parseColor("#5bb548") ) ;
            holder.getMensaje().setVisibility(View.VISIBLE);
        }
        else {
            holder.itemView.setBackgroundResource(R.color.md_theme_dark_outline) ;
            holder.getMensaje().setVisibility(View.VISIBLE);

        }

        Long codigoHora = listMensaje.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");//a pm o am
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

}
