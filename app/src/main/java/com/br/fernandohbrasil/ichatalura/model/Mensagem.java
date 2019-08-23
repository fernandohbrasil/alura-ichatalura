package com.br.fernandohbrasil.ichatalura.model;

import com.google.gson.annotations.SerializedName;

public class Mensagem {

    public Mensagem(int id, String texto) {
        this.texto = texto;
        this.id = id;
    }

    @SerializedName("text")
    private String texto;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
