package com.example.mbertonha.firstexemple;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mbertonha.firstexemple.database.DadosOpenHelper;
import com.example.mbertonha.firstexemple.dominio.entidades.Cliente;
import com.example.mbertonha.firstexemple.dominio.repositorio.cliente_repositorio;

import java.util.List;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private SQLiteDatabase conexao;

    private DadosOpenHelper dadosOpenHelper;

    private cliente_repositorio clienteRepositorio;

    private ConstraintLayout layoutContentMain;

    private ClienteAdapter clienteAdapter;

    @Override  //sempre vai ser chamado quando o act for chamado
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);  //relacionando o act com o layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  //permite add um menu a parte superior
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);  //icone redondinho
        lstDados = (RecyclerView)findViewById(R.id.lstDados);

        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);

        criarConexao();

        lstDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);  //rsponsavel por dizer como sera visualizado
        lstDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new cliente_repositorio(conexao);
        List<Cliente> dados =  clienteRepositorio.buscarTodos();

        clienteAdapter =  new ClienteAdapter(dados);

        lstDados.setAdapter(clienteAdapter);

    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(layoutContentMain, "Conexão feita com sucesso", Snackbar.LENGTH_SHORT).setAction("OK", null).show();
            Toast.makeText(this, R.string.lblConexao_sucessfull, Toast.LENGTH_LONG).show();
        }
        catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.lblErro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.lblOk, null);
            dlg.show();
        }
    }

    public void cadastrar(View view){
        Intent it = new Intent(ActMain.this, ActCadCliente.class);
        startActivity(it);
    }

}
