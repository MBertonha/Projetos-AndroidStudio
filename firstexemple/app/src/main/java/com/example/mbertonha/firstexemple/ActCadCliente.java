package com.example.mbertonha.firstexemple;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mbertonha.firstexemple.database.DadosOpenHelper;
import com.example.mbertonha.firstexemple.dominio.entidades.Cliente;
import com.example.mbertonha.firstexemple.dominio.repositorio.cliente_repositorio;

public class ActCadCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtNum;
    private ConstraintLayout layoutContentActCadCliente;

    private DadosOpenHelper dadosOpenHelper;
    private SQLiteDatabase conexao;

    private cliente_repositorio clienteRepositorio;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtNum = (EditText)findViewById(R.id.edtNum);

        layoutContentActCadCliente = (ConstraintLayout)findViewById(R.id.layoutContentActCadCliente);

        criarConexao();
    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(layoutContentActCadCliente, "Conexão feita com sucesso", Snackbar.LENGTH_SHORT).setAction("OK", null).show();
            Toast.makeText(this, R.string.lblConexao_sucessfull, Toast.LENGTH_LONG).show();

            clienteRepositorio = new cliente_repositorio(conexao);
        }
        catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.lblErro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.lblOk, null);
            dlg.show();
        }
    }

    private void confirmar(){

        cliente = new Cliente();

        if(validaCampos() == false){
            try{
                clienteRepositorio.inserir(cliente);
                finish();
            }
            catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.lblErro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.lblOk, null);
                dlg.show();
            }

        }


    }

    //valida se o campo foi digitado corretamente**************************************
    private boolean validaCampos(){

        boolean res     = false;
        String nome     = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email    = edtEmail.getText().toString();
        String telefone = edtNum.getText().toString();

        cliente.nome     = nome;
        cliente.endereco = endereco;
        cliente.email    = email;
        cliente.telefone = telefone;

        if (res = isCampoVazio(nome)){
            edtNome.requestFocus();
        }
        else if (res = isCampoVazio(endereco)){
            edtEndereco.requestFocus();
            res = true;
        }
        else if (res  = !isEmailValido(email)){
            edtEmail.requestFocus();
        }
        else if (res = isCampoVazio(telefone)){
            edtNum.requestFocus();
        }
        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_invalidos_brancos);
            dlg.setNeutralButton(R.string.lblOk, null);
            dlg.show();
        }

        return res;
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());  //verifica se a string é nula, não trata espaço
        return resultado;
    }

    private boolean isEmailValido(String email){ //valida o email se esta correto
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_ok:
                confirmar();
                //Toast.makeText(this, "Botão Ok Selecionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
                //Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
