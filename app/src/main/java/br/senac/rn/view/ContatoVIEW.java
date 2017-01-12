package br.senac.rn.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.senac.rn.dao.ContatoDAO;
import br.senac.rn.model.Contato;

public class ContatoVIEW extends Activity {

    private EditText etId;
    private Button btBuscar;
    private TextView tvNome, tvFone, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato_view);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        etId = (EditText) findViewById(R.id.etId);
        btBuscar = (Button) findViewById(R.id.btBuscar);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFone = (TextView) findViewById(R.id.tvFone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
    }

    private void definirEventos() {
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Contato contato = buscar(Integer.parseInt(etId.getText().toString()));
                if (contato != null) {
                    tvNome.setText(contato.getNome());
                    tvFone.setText(contato.getFone());
                    tvEmail.setText(contato.getEmail());
                }
                limpar();
            }
        });
    }

    private Contato buscar(int id) {
        return new ContatoDAO().buscarId(id);
    }

    private void limpar() {
        etId.setText("");
        etId.requestFocus();
    }

}