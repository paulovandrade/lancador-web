package br.com.lancadorweb.DAO;

import br.com.lancadorweb.config.ConfigConsumingRest;
import br.com.lancadorweb.entity.Permissao;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */
@Repository
public class PermissaoDAO {

    public List<Permissao> buscaTodas(){
        List<Permissao> permissaoList = new ArrayList<>();
        final String uri = ConfigConsumingRest.URL + "/permissao/all";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Permissao[] permissoesArray = restTemplate.getForObject(uri, Permissao[].class);
            permissaoList = Arrays.asList(permissoesArray);
        } catch (Exception e){
            e.printStackTrace();
        }
        return permissaoList;
    }

    public Permissao buscaPermissao(Integer id){
        Permissao permissao = new Permissao();
        final String uri = ConfigConsumingRest.URL + "/permissao/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            permissao = restTemplate.getForObject(uri, Permissao.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return permissao;
    }

    public List<Permissao> buscaPermissoesUsuario(Integer id){
        List<Permissao> permissaoList = new ArrayList<>();
        final String uri = ConfigConsumingRest.URL + "/permissao/usuario/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            Permissao[] permissoesArray = restTemplate.getForObject(uri, Permissao[].class);
            permissaoList = Arrays.asList(permissoesArray);
        } catch (Exception e){
            e.printStackTrace();
        }
        return permissaoList;
    }

    public List<Permissao> buscaPermissoesUsuarioPermiteSim(Integer id){
        List<Permissao> permissaoList = new ArrayList<>();
        final String uri = ConfigConsumingRest.URL + "/permissao/usuariopermite/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            Permissao[] permissoesArray = restTemplate.getForObject(uri, Permissao[].class);
            permissaoList = Arrays.asList(permissoesArray);
        } catch (Exception e){
            e.printStackTrace();
        }
        return permissaoList;
    }

    public Permissao salvarPermissao(Permissao permissao){
        Permissao permissaosalva = null;
        final String uri = ConfigConsumingRest.URL + "/permissao/save";
        try {
            RestTemplate restTemplate = new RestTemplate();
            permissaosalva = restTemplate.postForObject(uri, permissao, Permissao.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return permissaosalva;
    }

    public boolean deletarPermissao(Integer id){
        boolean deletado = false;
        final String uri = ConfigConsumingRest.URL + "/permissao/delete/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(uri, "", String.class);
            deletado = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return deletado;
    }

}
