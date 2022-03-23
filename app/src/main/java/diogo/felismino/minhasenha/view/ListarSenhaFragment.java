package diogo.felismino.minhasenha.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    List<String> senhasTitulo;

    ArrayAdapter<String> tituloAdapter;

    SenhaController senhaController;
    Senha obj;

    public ListarSenhaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.listar_senha_fragment, container, false);

        senhaController = new SenhaController(getContext());
        obj = new Senha();

        lvSenha = (ListView) view.findViewById(R.id.lvSenha);

        editPesquisarTitulo = view.findViewById(R.id.editPesquisaTitulo);

        senhasTitulo = senhaController.geraListaIdTitulo();

        tituloAdapter = new ArrayAdapter<String>(getContext(), R.layout.listar_fragment_item, R.id.txtItemLista, senhasTitulo);

        lvSenha.setAdapter(tituloAdapter);

        editPesquisarTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int i, int i1, int i2) {

                ListarSenhaFragment.this.tituloAdapter.getFilter().filter(filtro);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        lvSenha.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int which_item = i;

                new AlertDialog.Builder(getContext()).setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                senhasTitulo.remove(which_item);
                                obj = new Senha();
                                obj.setId(which_item + 1);
                                senhaController.delatar(obj);
                                tituloAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

        return view;

    }

}
