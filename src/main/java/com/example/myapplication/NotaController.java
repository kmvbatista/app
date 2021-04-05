package com.example.myapplication;

import android.content.Context;

import java.util.ArrayList;

public class NotaController {
    private NotaDAO dao;

    public  NotaController(final  Context context) {
        this.dao = new NotaDAO(context);
    }

    public Nota cadastrarNovaNota(Nota nota) {
        if(notaEstaValida(nota))
            return dao.insereNota(nota);
        return null;
    }

    public boolean atualizarNota(Nota nota) {
        if(notaEstaValida(nota))
            return dao.atualizarNota(nota);
        return false;
    }

    public boolean excluirNota(int idNota) {
        if(idNota != 0)
            return dao.deletarNotaPorId(idNota);
        return  false;
    }

    public Nota obterNota(int idNota) {
        return  dao.obterNotaPorId(idNota);
    }

    public ArrayList<Nota> getListaNotas( ) {
        return  dao.listarNotas();
    }

    public  ArrayList<String> getTitulosLista() {
        ArrayList<String> result = new ArrayList<String>();
        for (Nota nota:
             this.getListaNotas()) {
            result.add(nota.getTitulo());
        }
        return  result;
    }

    private  boolean notaEstaValida(Nota nota) {
        return nota != null && nota.getDescricao() != "" && nota.getTitulo() != "";
    }
}
