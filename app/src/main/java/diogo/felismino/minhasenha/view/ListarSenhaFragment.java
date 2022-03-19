package diogo.felismino.minhasenha.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import diogo.felismino.minhasenha.R;
import diogo.felismino.minhasenha.controller.SenhaController;
import diogo.felismino.minhasenha.model.Senha;

public class ListarSenhaFragment extends Fragment {

    View view;

    ListView lvSenha;
    EditText editPesquisarTitulo;


    List<String> senhas;

    ArrayAdapter<String> senhaAdapter;



    Senha novaSenha;
    SenhaController senhaController;

    public ListarSenhaFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.listar_senha_fragment, container, false);

        senhaController = new SenhaController(getContext());

        lvSenha = (ListView) view.findViewById(R.id.lvSenha);

        editPesquisarTitulo = view.findViewById(R.id.editPesquisaTitulo);

        senhas = senhaController.geraLista();

        senhaAdapter = new ArrayAdapter<>(getContext(), R.layout.listar_fragment_item, R.id.txtItemLista, senhas);


        lvSenha.setAdapter(senhaAdapter);

        editPesquisarTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int i, int i1, int i2) {
                ListarSenhaFragment.this.senhaAdapter.getFilter().filter(filtro);
                Log.i("appTeste", "beforeTextChanged: "+filtro);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        return view;
    }




}
