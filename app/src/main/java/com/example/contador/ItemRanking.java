package com.example.contador;

public class ItemRanking {
    private final String name;
    private final int img;

    public ItemRanking(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public ItemRanking(String name){
        this.name = name;
        this.img = R.drawable.img_no_premio;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
}
