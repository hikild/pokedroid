package com.example.assessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.assessment.pokeapi.ListaPokemonAdapter;
import com.example.assessment.pokeapi.Pokemon;
import com.example.assessment.pokeapi.PokemonResposta;
import com.example.assessment.pokeapi.PokemonService;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaPrincipal extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG="POKÉDEX";

    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;
    private boolean carregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);

        recyclerView = findViewById(R.id.principal_tela);
        listaPokemonAdapter = new  ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    int item = gridLayoutManager.getChildCount();
                    int totalItem = gridLayoutManager.getItemCount();
                    int itemVisivel = gridLayoutManager.findFirstVisibleItemPosition();

                    if (carregar){
                        if ((item + itemVisivel) >=totalItem){
                            Log.i(TAG, "Fim da lista.");
                            carregar = false;
                            offset+=20;
                            obterDados(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        carregar = true;
        offset = 0;
        obterDados(offset);
    }

    private void obterDados(int offset){
        PokemonService service = retrofit.create(PokemonService.class);
        Call<PokemonResposta> pokemonRespostaCall = service.obterListaPokemon(20, offset);

        pokemonRespostaCall.enqueue(new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                carregar = true;
                if (response.isSuccessful()){
                    PokemonResposta pokemonResposta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonResposta.getResult();
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);
                    recyclerView.setAdapter(listaPokemonAdapter);
                } else{
                    Log.e(TAG, " on response " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {
                carregar = true;
                Log.e(TAG, " on failure " + t.getMessage());

            }
        });
    }
}
