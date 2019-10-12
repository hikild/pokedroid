package com.example.assessment.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("pokemon")
    Call<PokemonResposta> obterListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
