package diogo.felismino.minhasenha.datamodel;

public class SenhaDataModel {

    public static final String NOME_TABELA = "Senhas";

    public static final String ID = "id";
    public static final String NOME_SENHA = "nome";
    public static final String SENHA = "senha";

    public static  String query = "";

    public static String criarTabela(){

        query += "CREATE TABLE "+NOME_TABELA+ " (";
        query += ID+" integer primary key autoincrement, ";
        query += NOME_SENHA+" text, ";
        query += SENHA+" text ";
        query += ")";

        return query;
    }
}
