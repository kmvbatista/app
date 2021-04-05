package com.example.myapplication;

import androidx.annotation.NonNull;

public class Nota {

    private int idNota;
    private String titulo;
    private String descricao;

    public Nota(int idNota, String titulo, String descricao) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {
        return this.titulo;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
