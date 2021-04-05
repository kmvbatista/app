package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityExibirNota extends AppCompatActivity {

    NotaController controller;
    EditText editTextTitulo, editTextDescricao;
    String acao;
    Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_nota);
        controller = new NotaController(getApplicationContext());
        editTextTitulo = findViewById(R.id.editText);
        editTextDescricao = findViewById(R.id.editText2);
        int idNota = Integer.valueOf(getIntent().getExtras().get("notaId").toString());
        lidarComEdicao();
    }

    private void lidarComEdicao() {
        acao = this.getIntent().getExtras().get("acao").toString();
        if(acao.equalsIgnoreCase("edit")) {
            int idNota = Integer.valueOf(getIntent().getExtras().get("notaId").toString());
            nota = controller.obterNota(idNota);
            editTextTitulo.setText(nota.getTitulo());
            editTextDescricao.setText(nota.getDescricao());
        }
        else {
            Button botaoDeletar = findViewById(R.id.button2);
            botaoDeletar.setEnabled(false);
        }
    }

    public void deletarNota(View v) {
        controller.excluirNota(nota.getIdNota());
        finish();
    }

    public void salvarNota(View v) {
        String titulo = editTextTitulo.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        if(acao.equalsIgnoreCase("edit")) {
            nota.setDescricao(descricao);
            nota.setTitulo(titulo);
            controller.atualizarNota(nota);
        }
        else
            salvarNovaNota(titulo, descricao);
        finish();
    }

    private void salvarNovaNota(String titulo, String descricao) {
        Nota notaParaSalvar = new Nota(titulo, descricao);
        controller.cadastrarNovaNota(notaParaSalvar);
    }
}
