package br.com.lancadorweb.DAO;

import br.com.lancadorweb.config.ConfigConsumingRest;
import br.com.lancadorweb.entity.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paulo on 23/07/16.
 */
@Repository
public class UsuarioDAO {

    public List<Usuario> buscaTodos(){
        List<Usuario> usuarioList = new ArrayList<>();
        final String uri = ConfigConsumingRest.URL + "/usuario/all";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Usuario[] usuariosArray = restTemplate.getForObject(uri, Usuario[].class);
            usuarioList = Arrays.asList(usuariosArray);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuarioList;
    }

    public Usuario buscaUsuario(Integer id){
        Usuario usuario = new Usuario();
        final String uri = ConfigConsumingRest.URL + "/usuario/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            usuario = restTemplate.getForObject(uri, Usuario.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario buscaUsuarioPorLoginESenha(String login, String senha){
        Usuario usuario = new Usuario();
        final String uri = ConfigConsumingRest.URL + "/usuario/" + login + "/" + senha;
        try {
            RestTemplate restTemplate = new RestTemplate();
            usuario = restTemplate.getForObject(uri, Usuario.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }

    public Usuario buscaUsuarioPorLogin(String login) {
        Usuario usuario = new Usuario();
        final String uri = ConfigConsumingRest.URL + "/usuario/login/" + login;
        try {
            RestTemplate restTemplate = new RestTemplate();
            usuario = restTemplate.getForObject(uri, Usuario.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }


    public Usuario salvarUsuario(Usuario usuario){
        Usuario usuariosalvo = null;
        final String uri = ConfigConsumingRest.URL + "/usuario/save";
        try {
            RestTemplate restTemplate = new RestTemplate();
            usuariosalvo = restTemplate.postForObject(uri, usuario, Usuario.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuariosalvo;
    }

    public boolean deletarUsuario(Integer id){
        boolean deletado = false;
        final String uri = ConfigConsumingRest.URL + "/usuario/delete/" + id.toString();
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
