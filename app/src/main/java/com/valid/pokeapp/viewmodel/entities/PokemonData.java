
package com.valid.pokeapp.viewmodel.entities;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonData implements Serializable {

    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = null;
    @SerializedName("moves")
    @Expose
    private List<Move> moves = null;
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;
    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public List<Type> getTypes() {
        return types;
    }

}
