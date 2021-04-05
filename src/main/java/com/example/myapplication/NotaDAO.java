package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotaDAO {
    SQLiteDatabase banco;
    Context context;
    final String nomeTabela = "Notas";
    final  String queryBasica ="SELECT idNota, titulo, descricao FROM " +nomeTabela;

    public  NotaDAO(Context context) {
        this.context = context;
        banco = this.context.openOrCreateDatabase(nomeTabela,
                    this.context.MODE_PRIVATE,null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS Notas (idNota INTEGER PRIMARY KEY AUTOINCREMENT,titulo TEXT, descricao TEXT )" );
    }

    public Nota insereNota(Nota nota) {
        ContentValues contentValues = getContentValuesFromModel(nota);
        int id = (int) banco.insert(nomeTabela, null, contentValues);
        nota.setIdNota(id);
        return nota;
    }

    public boolean atualizarNota(Nota nota) {
        ContentValues contentValues = getContentValuesFromModel(nota);
        try {
            banco.update(nomeTabela, contentValues, "idNota = "+ Integer.toString(nota.getIdNota()), null);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public boolean deletarNotaPorId(Integer id) {
        try {
            banco.delete(nomeTabela, "idNota = "+ id.toString(), null);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public Nota obterNotaPorId(int id) {
        String query = queryBasica+ " where idNota = "+ id;
        Cursor cursor = banco.rawQuery(query, null);
        cursor.moveToFirst();
        return  getNotaFromCursor(cursor);
    }

    public ArrayList<Nota> listarNotas( ) {
        ArrayList<Nota> notas = new ArrayList<Nota>();
        String query = queryBasica;
        Cursor cursor = banco.rawQuery(query, null);
        while (cursor.moveToNext()) {
            notas.add(getNotaFromCursor(cursor));
        }
        return  notas;
    }

    private ContentValues getContentValuesFromModel(Nota nota) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", nota.getTitulo());
        contentValues.put("descricao", nota.getDescricao());
        return contentValues;
    }

    private  Nota getNotaFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("idNota"));
        String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
        String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
        return  new Nota(id, titulo, descricao);
    }
}
