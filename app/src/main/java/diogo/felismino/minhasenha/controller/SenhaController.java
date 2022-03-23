package diogo.felismino.minhasenha.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import diogo.felismino.minhasenha.datamodel.SenhaDataModel;
import diogo.felismino.minhasenha.datasource.AppDataBase;
import diogo.felismino.minhasenha.model.Senha;

public class SenhaController extends AppDataBase implements ICrud{


    ContentValues dados;
    // Senha senha;

    public SenhaController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Senha obj) {

        dados = new ContentValues();

        dados.put(SenhaDataModel.NOME_SENHA, obj.getNome());
        dados.put(SenhaDataModel.SENHA, obj.getSenha());



        return insert(SenhaDataModel.NOME_TABELA, dados);
    }

    @Override
    public boolean alterar(Senha obj) {

        dados.put(SenhaDataModel.ID, obj.getId());
        dados.put(SenhaDataModel.NOME_SENHA, obj.getNome());
        dados.put(SenhaDataModel.SENHA, obj.getSenha());

        return update(SenhaDataModel.NOME_TABELA, dados);
    }

    @Override
    public boolean delatar(Senha obj) {
        return deleteById(SenhaDataModel.NOME_TABELA, obj.getId());
    }

    @Override
    public List<Senha> listar() {

        return getAllSenhas(SenhaDataModel.NOME_TABELA);
    }

    public List<String> geraListaIdTitulo(){

        List<String> senhas = new ArrayList<>();

        for (Senha obj: listar()) {
            senhas.add(obj.getNome() + "  |  " + obj.getSenha());
        }

        return senhas;
    }

   /* public List<String> geraListaSenha() {

        List<String> senhas = new ArrayList<>();

        for (Senha obj : listar()) {
            senhas.add(obj.getSenha());
        }

        return senhas;

    }*/

    public String gerarSenha(Senha obj){

       // senha = new Senha();

        return Senha.gerar(obj);


    }
}
