package com.example.proclastinator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proclastinator.R;
import com.example.proclastinator.model.Atividade;

import java.util.List;

public class RotinaAdapter extends RecyclerView.Adapter<RotinaAdapter.MyViewHolder> {
    private List<Atividade>listaa;

    public RotinaAdapter(List<Atividade> lista) {
        this.listaa = lista;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.rotina_adapter,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Atividade rotinas = listaa.get(position);
        holder.rotina.setText(""+rotinas.getNome_ativiade());
        holder.rotina2.setText("Horario: "+rotinas.getComeço());
        holder.rotina3.setText("Data: "+rotinas.getTermino());



    }

    @Override
    public int getItemCount() {
        return this.listaa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rotina;
        TextView rotina2;
        TextView rotina3;

        ImageView imagem3;
        ImageView imagem2;
        ImageView imagem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imagem1);
            imagem2 = itemView.findViewById(R.id.imagem2);
            imagem3 = itemView.findViewById(R.id.imagem3);

            rotina2 = itemView.findViewById(R.id.tarefinha2);
            rotina3 = itemView.findViewById(R.id.tarefinha);
            rotina = itemView.findViewById(R.id.tarefinha3);
        }
    }
}
