package com.valid.pokeapp.utils;

public class ViewModelUtils {

    public static int getPokemonNumberFromUrl(String url) {
        int numberIndex = 0;
        for (int i=url.length()-2; url.charAt(i)!='/'; i--) {
            numberIndex = i;
        }
        return Integer.valueOf(url.substring(numberIndex, url.length()-1));
    }

}
