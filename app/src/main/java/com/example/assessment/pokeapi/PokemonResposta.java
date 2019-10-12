package com.example.assessment.pokeapi;

import java.util.ArrayList;

public class PokemonResposta {

    private ArrayList<Pokemon> results;
    public ArrayList<Pokemon> getResult(){
        return results;
    }

    public void setResultado(ArrayList<Pokemon>results){
        this.results = results;
    }
}
