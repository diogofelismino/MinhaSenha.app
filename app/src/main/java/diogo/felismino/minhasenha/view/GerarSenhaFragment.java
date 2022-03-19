package diogo.felismino.minhasenha.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import diogo.felismino.minhasenha.R;
import diogo.felismino.minhasenha.controller.SenhaController;
import diogo.felismino.minhasenha.model.Senha;

public class GerarSenhaFragment extends Fragment {

    View view;

    EditText editTituloSenha;
    EditText editSenhaGerada;
    EditText editTamanhoSenha;
    Button btnSalvar;
    Button btnGerar;

    CheckBox cbLetraMaiusculas;
    CheckBox cbLetraMinusculas;
    CheckBox cbNumeros;
    CheckBox cbEspeciais;

    Senha novaSenha;
    SenhaController senhaController;

    public GerarSenhaFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.gerar_senha_fragment, container, false);

        editTamanhoSenha = view.findViewById(R.id.editTamanhoSenha);
        editTituloSenha = view.findViewById(R.id.editTituloSenha);
        editSenhaGerada = view.findViewById(R.id.editSenha);
        editSenhaGerada.setEnabled(false);

        btnSalvar = view.findViewById(R.id.btnSALVAR);
        btnGerar = view.findViewById(R.id.btnGERAR);

        cbLetraMaiusculas = view.findViewById(R.id.cbLetraMaiusculas);
        cbLetraMinusculas = view.findViewById(R.id.cbLetraMinusculas);
        cbNumeros = view.findViewById(R.id.cbNumeros);
        cbEspeciais = view.findViewById(R.id.cbEspeciais);


        novaSenha = new Senha();
        senhaController = new SenhaController(getContext());

        eventButton();


        return view;
    }



    private void eventButton() {

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOk = true;

                if (TextUtils.isEmpty(editTamanhoSenha.getText())){
                    isDadosOk = false;
                    editTamanhoSenha.setError("Digite um tamanho para sua senha...");
                }

                if(isDadosOk) {
                    novaSenha.setTamanho(Integer.parseInt(editTamanhoSenha.getText().toString()));
                    novaSenha.setLetraMaiusculas(cbLetraMaiusculas.isChecked());
                    novaSenha.setLetraMinusculas(cbLetraMinusculas.isChecked());
                    novaSenha.setNumeros(cbNumeros.isChecked());
                    novaSenha.setEspeciais(cbEspeciais.isChecked());


                    editSenhaGerada.setText(senhaController.gerarSenha(novaSenha));
                }
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOk = true;

                if (TextUtils.isEmpty(editTituloSenha.getText())){
                    isDadosOk = false;
                    editTituloSenha.setError("Digite um titulo para sua senha...");
                    editTituloSenha.requestFocus();
                }
                if (TextUtils.isEmpty(editSenhaGerada.getText())){
                    isDadosOk = false;
                    editSenhaGerada.setError("Digite uma senha pfvr...");
                    editSenhaGerada.requestFocus();
                }


                if (isDadosOk){

                    novaSenha.setNome(editTituloSenha.getText().toString());
                    novaSenha.setSenha(editSenhaGerada.getText().toString());

                    senhaController.incluir(novaSenha);

                    Toast.makeText(getContext(), "Senha Salvada com Sucesso...", Toast.LENGTH_LONG).show();
                    Log.i("appTeste", "onClick: Dados corretos...");

                }
                else{
                    Log.i("appTeste", "onClick: Dados incorretos...");
                }

            }
        });

    }

}
