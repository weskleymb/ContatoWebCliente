package br.senac.rn.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import br.senac.rn.model.Contato;
import br.senac.rn.util.AcessoWS;

public class ContatoDAO {

    private final String SERVIDOR = "http://www2.pm.rn.gov.br:8080/contatoweb/resources/contato";
    private final AcessoWS WEBSERVICE = new AcessoWS();

    private final String CADASTRAR = SERVIDOR + "/cadastrar/";
    private final String BUSCAR = SERVIDOR + "/buscar/";
    private final String ALTERAR = SERVIDOR + "/alterar/";
    private final String EXCLUIR = SERVIDOR + "/excluir/";

    public boolean cadastrar(Contato contato) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(contato);
            return WEBSERVICE.httpPost(CADASTRAR, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Contato> buscar() {
        try {
            Gson gson = new Gson();
            String json = WEBSERVICE.httpGet(BUSCAR);
            Type type = new TypeToken<List<Contato>>() {}.getType();
            List<Contato> contatos = gson.fromJson(json, type);
            return contatos;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Contato buscarId(int id) {
        try {
            Gson gson = new Gson();
            String json = WEBSERVICE.httpGet(BUSCAR + String.valueOf(id));
            Type type = new TypeToken<Contato>() {}.getType();
            Contato contato = gson.fromJson(json, type);
            return contato;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean alterar(Contato contato) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(contato);
            return WEBSERVICE.httpPut(ALTERAR, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluir(int id) {
        try {
            return WEBSERVICE.httpDelete(EXCLUIR + String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}