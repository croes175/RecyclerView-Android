package com.androiddesdecero.reciclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.M;


public class PesoAdaptador extends RecyclerView.Adapter<PesoAdaptador.PesoViewHolder>{



    private List<Peso> pesos;

    public PesoAdaptador(List<Peso> pesos){
        this.pesos = pesos;
    }

    @Override
    public PesoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        /*switch (viewType){
            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_primero,parent,false);
                break;
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                break;
        }*/
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new PesoViewHolder(v,viewType);
    }

    @Override
    public void onBindViewHolder(PesoViewHolder pesoViewHolder, int position) {
        Peso peso = pesos.get(position);

        pesoViewHolder.tvFecha.setText(peso.getmFecha());
        pesoViewHolder.tvPeso.setText(Integer.toString(position+1));



    }

    @Override
    public int getItemViewType(int position){
        int viewType = 1;

        return viewType;
    }

    @Override
    public int getItemCount() {
        return pesos.size();
    }

    public class PesoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFecha;
        private TextView tvPeso;



        public PesoViewHolder(View itemView, int p){
            super(itemView);

            tvFecha=(TextView) itemView.findViewById(R.id.tvFecha);
            tvPeso=(TextView) itemView.findViewById(R.id.tvPeso);

        }



    }
}
