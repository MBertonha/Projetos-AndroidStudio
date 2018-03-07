package com.example.mbertonha.firstexemple.database;

/**
 * Created by MBertonha on 18/10/2017.
 */

public class ScriptDLL {

    public static String getCreateTableClient(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS CLIENTE ( ");
        sql.append("    CODIGO INTEGER PRIMARYKEY AUTOINCREMENT NOT NULL, ");
        sql.append("    NOME VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    ENDERECO VARCHAR(255) NOT NULL DEFAULT(''), ");
        sql.append("    EMAIL VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("    TELEFONE VARCHAR(20) NOT NULL DEFAULT(''), ) ");
        return sql.toString();
    }

}
