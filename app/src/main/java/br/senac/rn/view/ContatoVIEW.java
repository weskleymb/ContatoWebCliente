package br.senac.rn.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.senac.rn.dao.ContatoDAO;
import br.senac.rn.model.Contato;

public class ContatoVIEW extends Activity {

    private EditText etId;
    private Button btBuscar;
    private TextView tvNome, tvFone, tvEmail;
    private ProgressDialog load;

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
                buscar(Integer.parseInt(etId.getText().toString()));
            }
        });
    }

    private void buscar(int id) {
        new TarefaDownload().execute(id);
    }

    private void limpar() {
        etId.setText("");
        etId.requestFocus();
    }

    private class TarefaDownload extends AsyncTask<Integer, Integer, Contato> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(ContatoVIEW.this, "Por favor aguarde...", "Buscando dados...");
        }

        @Override
        protected Contato doInBackground(Integer... ints) {
            return new ContatoDAO().buscarId(ints[0]);
        }

        @Override
        protected void onPostExecute(Contato contato) {
            load.dismiss();
            if (contato != null) {
                tvNome.setText(contato.getNome());
                tvFone.setText(contato.getFone());
                tvEmail.setText(contato.getEmail());
            } else {
                tvNome.setText("");
                tvFone.setText("");
                tvEmail.setText("");

                Context contexto = getApplicationContext();
                String texto = "Contato não encontrado";
                int duracao = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(contexto, texto, duracao);
                toast.show();
            }
            limpar();
        }

    }

}