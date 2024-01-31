package com.example.chatproject.models;

import com.google.gson.annotations.SerializedName;

public class MiObjeto {
    @SerializedName("nombre")
    private String nombre;

    @SerializedName("num")
    private Integer num;

    public MiObjeto(String nombre, Integer num) {
        this.nombre = nombre;
        this.num = num;
    }
}
