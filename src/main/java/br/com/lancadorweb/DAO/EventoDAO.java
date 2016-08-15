package br.com.lancadorweb.DAO;

import br.com.lancadorweb.config.ConfigConsumingRest;
import br.com.lancadorweb.entity.Evento;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paulo on 26/07/16.
 */
@Repository
public class EventoDAO {
    public List<Evento> buscaTodos(){
        List<Evento> eventoList = new ArrayList<>();
        final String uri = ConfigConsumingRest.URL + "/evento/all";
        try {
            RestTemplate restTemplate = new RestTemplate();
            Evento[] eventosArray = restTemplate.getForObject(uri, Evento[].class);
            eventoList = Arrays.asList(eventosArray);
        } catch (Exception e){
            e.printStackTrace();
        }
        return eventoList;
    }

    public Evento buscaEvento(Integer id){
        Evento evento = new Evento();
        final String uri = ConfigConsumingRest.URL + "/evento/" + id.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            evento = restTemplate.getForObject(uri, Evento.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return evento;
    }

    public Evento salvarEvento(Evento evento){
        Evento eventosalvo = null;
        final String uri = ConfigConsumingRest.URL + "/evento/save";
        try {
            RestTemplate restTemplate = new RestTemplate();
            eventosalvo = restTemplate.postForObject(uri, evento, Evento.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return eventosalvo;
    }

    public boolean deletarEvento(Integer id){
        boolean deletado = false;
        final String uri = ConfigConsumingRest.URL + "/evento/delete/" + id.toString();
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
