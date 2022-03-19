package diogo.felismino.minhasenha.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Senha {

    private int id;
    private String nome;
    private String senha;
    private  int tamanho;
    private boolean letraMaiusculas;
    private boolean letraMinusculas;
    private boolean numeros;
    private boolean especiais;

    public Senha(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isLetraMaiusculas() {
        return letraMaiusculas;
    }

    public void setLetraMaiusculas(boolean letraMaiusculas) {
        this.letraMaiusculas = letraMaiusculas;
    }

    public boolean isLetraMinusculas() {
        return letraMinusculas;
    }

    public void setLetraMinusculas(boolean letraMinusculas) {
        this.letraMinusculas = letraMinusculas;
    }

    public boolean isNumeros() {
        return numeros;
    }

    public void setNumeros(boolean numeros) {
        this.numeros = numeros;
    }

    public boolean isEspeciais() {
        return especiais;
    }

    public void setEspeciais(boolean especiais) {
        this.especiais = especiais;
    }

    public static String gerar(Senha obj){
       /* String[] caracteres = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z","0","1", "2", "3", "4", "5", "7", "8", "9",
                "!", "@", "#", "$", "*", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z"};*/
        List<String> letraMai = Arrays.asList(
                "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z");

        List<String> numeros = Arrays.asList("0","1", "2", "3", "4", "5", "7", "8", "9");

        List<String> especiais = Arrays.asList("!", "@", "#", "$", "*");

        List<String> letraMin = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z");

        StringBuilder element = new StringBuilder();

        List<String> res = new ArrayList<>();

        if(obj.isLetraMaiusculas()) res.addAll(letraMai);
        if(obj.isLetraMinusculas()) res.addAll(letraMin);
        if(obj.isNumeros()) res.addAll(numeros);
        if(obj.isEspeciais()) res.addAll(especiais);;

        if(res.isEmpty()){

            return "";
        }


            for (int i=0; i< obj.getTamanho(); i++){
                int pos = (int) (Math.random() * res.size());
                element.append(res.get(pos)) ;
            }




            return element.toString();

    }

}
