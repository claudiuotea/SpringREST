package rest.client;

import application.services.rest.ServiceException;
import model.Cursa;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RacesClient {
    public static final String URL ="http://localhost:8080/app/races";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Cursa[] getAll(){return execute(() -> restTemplate.getForObject(URL,Cursa[].class));}

    public Cursa getById(int id){
        return execute(()->restTemplate.getForObject(String.format("%s/%s",URL,Integer.toString(id)),Cursa.class));
    }

    public Cursa create(Cursa cursa) {return execute(()->restTemplate.postForObject(URL,cursa,Cursa.class));}

    public void update(Cursa cursa){
        execute(()->{
            restTemplate.put(String.format("%s/%s",URL,cursa.getId().toString()),cursa);
            return null;
        });
    }

    public void delete(int id){
        execute(()->{
            restTemplate.delete(String.format("%s/%s",URL,id));
            return null;
        });
    }
}
