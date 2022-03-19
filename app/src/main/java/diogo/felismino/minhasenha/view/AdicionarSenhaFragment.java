package diogo.felismino.minhasenha.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.lang.annotation.Target;

import diogo.felismino.minhasenha.R;
import diogo.felismino.minhasenha.controller.SenhaController;
import diogo.felismino.minhasenha.model.Senha;

public class AdicionarSenhaFragment extends Fragment {

    View view;

    EditText editTituloSenha;
    EditText editSenha;
    Button btnSalvar;

    Senha novaSenha;
    SenhaController senhaController;

    public AdicionarSenhaFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.adicionar_senha_fragment, container, false);

        editTituloSenha = view.findViewById(R.id.editTituloSenha);
        editSenha = view.findViewById(R.id.editSenha);
        btnSalvar = view.findViewById(R.id.btnSALVAR);

        novaSenha = new Senha();
        senhaController = new SenhaController(getContext());

        eventButton();


        return view;
    }

    private void eventButton() {

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOk = true;

                if (TextUtils.isEmpty(editTituloSenha.getText())){
                    isDadosOk = false;
                    editTituloSenha.setError("Digite um titulo para sua senha...");
                    editTituloSenha.requestFocus();
                }
                if (TextUtils.isEmpty(editSenha.getText())){
                    isDadosOk = false;
                    editSenha.setError("Digite uma senha pfvr...");
                    editSenha.requestFocus();
                }


                if (isDadosOk){

                    novaSenha.setNome(editTituloSenha.getText().toString());
                    novaSenha.setSenha(editSenha.getText().toString());

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
