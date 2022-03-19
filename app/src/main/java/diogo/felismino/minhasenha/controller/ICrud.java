package diogo.felismino.minhasenha.controller;

import java.util.List;

import diogo.felismino.minhasenha.model.Senha;

public interface ICrud {

    boolean incluir(Senha obj);

    boolean alterar(Senha obj);

    boolean delatar(Senha obj);

    List<Senha> listar();


}
