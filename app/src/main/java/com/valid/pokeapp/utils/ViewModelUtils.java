package com.valid.pokeapp.utils;

public class ViewModelUtils {

    public static int getPokemonNumberFromUrl(String url) {
        int numberIndex = 0;
        for (int i = url.length() - 2; url.charAt(i) != '/'; i--) {
            numberIndex = i;
        }
        return Integer.valueOf(url.substring(numberIndex, url.length() - 1));
    }

    public static String formatName(String name) {
        StringBuilder properName = new StringBuilder();
        String[] subNames = name.split("-");
        for (String word : subNames) {
            properName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return properName.toString();
    }

}
