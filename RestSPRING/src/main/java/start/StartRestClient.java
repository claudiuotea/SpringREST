package start;

import application.services.rest.ServiceException;
import model.Cursa;
import org.springframework.web.client.RestTemplate;
import rest.client.RacesClient;

public class StartRestClient {
    private final static RacesClient raceCLient = new RacesClient();

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Cursa c = new Cursa("5cmc",35);
        try{
            //show(()->{
                //raceCLient.update(new Cursa("4000cmc",6,20));
            //});
            show(()->{
                Cursa[] curse = raceCLient.getAll();
                for(Cursa x:curse)
                    System.out.println(x);
            });
            show(() -> System.out.println(raceCLient.create(c)));
            show(()-> raceCLient.delete(6));
            show(()-> System.out.println(raceCLient.getById(2)));

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static void show(Runnable task){
        try {
            task.run();
        }
        catch (ServiceException e){
            System.out.println("Service exception .. " + e);
        }
    }
}
