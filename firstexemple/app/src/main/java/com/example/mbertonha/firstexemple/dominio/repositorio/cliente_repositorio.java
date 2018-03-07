package com.example.mbertonha.firstexemple.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mbertonha.firstexemple.dominio.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MBertonha on 20/10/2017.
 */

public class cliente_repositorio {

    private SQLiteDatabase conexao;

    public cliente_repositorio(SQLiteDatabase conexao){
        this.conexao = conexao;  //usa o this para referenciar o objeto declarado no cliente_repositorio
    }

    public void inserir(Cliente cliente){

        ContentValues contentValues = new ContentValues();   //passar os valores passado para o banco de dados
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("TELEFONE", cliente.telefone);

        conexao.insertOrThrow("CLIENTE", null, contentValues);
    }

    public void excluir(int codigo){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("CLIENTE", "CODIGO = ?", parametros);
    }

    public void alterar(Cliente cliente){

        ContentValues contentValues = new ContentValues();   //passar os valores passado para o banco de dados
        contentValues.put("NOME", cliente.nome);
        contentValues.put("ENDERECO", cliente.endereco);
        contentValues.put("EMAIL", cliente.email);
        contentValues.put("TELEFONE", cliente.telefone);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.codigo);

        conexao.update("CLIENTE", contentValues, "CODIGO = ?", parametros);

    }

    public List<Cliente> buscarTodos(){

        List<Cliente> clientes = new ArrayList<Cliente>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, ENDERECO, EMAIL, TELEFONE");
        sql.append(" FROM CLIENTE ");

        Cursor resultado =  conexao.rawQuery(sql.toString(), null);
        if(resultado.getCount() > 0){
            resultado.moveToFirst();  //ter certeza que estou percorrendo desde o 1 objeto

            do {                       //percorrendo os objetos

                Cliente cli = new Cliente();
                cli.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));  //é passado o nome da coluna que eu quero
                cli.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                cli.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
                cli.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                cli.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));

                clientes.add(cli);

            }while(resultado.moveToNext());
        }

        return clientes;
    }

    public Cliente buscarCliente(int codigo){

        Cliente cliente  = new Cliente();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, ENDERECO, EMAIL, TELEFONE");
        sql.append(" FROM CLIENTE ");
        sql.append(" WHERE CODIGO = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado =  conexao.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();  //ter certeza que estou percorrendo desde o 1 objeto

            cliente.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));  //é passado o nome da coluna que eu quero
            cliente.endereco = resultado.getString(resultado.getColumnIndexOrThrow("ENDERECO"));
            cliente.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
            cliente.telefone = resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE"));

            return cliente;
        }

        return null;
    }

}
