package com.example.assessment.pokeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.assessment.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;
    private Pokemon p;

    public ListaPokemonAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPokemonAdapter.ViewHolder holder, int position) {
        p = dataset.get(position);
        holder.nomePokemon.setText(p.getName());
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"+p.getNumber()+".png")
                .centerCrop().
                crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoPokemon);
    }

    @Override
    public int getItemCount() {
        return dataset.size();

    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon){
            dataset.addAll(listaPokemon);
            notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoPokemon;
        private TextView nomePokemon;
        private CardView cardPokemon;

        public ViewHolder(View itemView){
            super(itemView);

            fotoPokemon = itemView.findViewById(R.id.pokemonFoto);
            nomePokemon = itemView.findViewById(R.id.nomePokemon);
            cardPokemon = itemView.findViewById(R.id.cardPokemon);

        }
    }
}
