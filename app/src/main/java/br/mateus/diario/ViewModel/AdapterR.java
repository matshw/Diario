package br.mateus.diario.ViewModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.mateus.diario.Model.Diario;
import br.mateus.diario.R;

public class AdapterR extends RecyclerView.Adapter<AdapterR.MeuViewHolder> {
    List<Diario> dados;

    public AdapterR(List dados){this.dados=dados;}

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_recycle,parent,false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        Diario diario = dados.get(position);
        holder.titulo.setText(diario.getTitulo());
        holder.descricao.setText(diario.getDescricao());
        holder.humor.setText(diario.getHumor());
        holder.data.setText(diario.getData());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView descricao;
        TextView humor;
        TextView data;
        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.idTituloR);
            descricao = itemView.findViewById(R.id.idDescricaoR);
            humor = itemView.findViewById(R.id.idHumorR);
            data = itemView.findViewById(R.id.idDataR);
        }
    }
}