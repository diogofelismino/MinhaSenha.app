package diogo.felismino.minhasenha.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import diogo.felismino.minhasenha.datamodel.SenhaDataModel;
import diogo.felismino.minhasenha.model.Senha;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NOME = "MinhaSenhaDB.sqlite";
    public static final int DB_VERSAO = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context) {
        super(context, DB_NOME, null, DB_VERSAO);

        Log.i("appBanco", "AppDataBase: Criando banco de dados");

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("appBanco", "onCreate: Tentando criar o banco de dados");
        db.execSQL(SenhaDataModel.criarTabela());
        Log.i("appBanco", "onCreate: criado com sucesso");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String tabela, ContentValues dados){

        db = getWritableDatabase();
        return db.insert(tabela, null, dados) > 0;
    }

    public boolean deleteById(String tabela, int id){

        db = getWritableDatabase();
        return db.delete(tabela,"id = ?", new String[] {String.valueOf(id)}) > 0;
    }

    public boolean update(String tabela, ContentValues dados){
        db = getWritableDatabase();
        return db.update(tabela, dados, "id = ?", new String[] {String.valueOf(dados.get("id"))}) > 0;
    }

    public List<Senha> getAllSenhas(String tabela){

        db = getWritableDatabase();

        List<Senha> senhas = new ArrayList<>();
        Senha obj;

        String sql = "SELECT * FROM " + tabela;
        Cursor cursor;
        cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{

                obj = new Senha();

                obj.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SenhaDataModel.ID)));
                obj.setNome(cursor.getString(cursor.getColumnIndexOrThrow(SenhaDataModel.NOME_SENHA)));
                obj.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(SenhaDataModel.SENHA)));

                senhas.add(obj);

                Log.i("appBanco", "getAllSenhass: " + obj.getNome());

            }while(cursor.moveToNext());
        }

        return senhas;
    }
}
