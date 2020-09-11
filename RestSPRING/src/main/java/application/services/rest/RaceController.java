package application.services.rest;

import application.data.Repository.CursaRepository;
import application.data.RepositoryException;
import model.Cursa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/app/races")
public class RaceController {
    @Autowired
    private CursaRepository cursaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Cursa[] getAll(){
        System.out.println("Get all races...");
        return cursaRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){
        System.out.println("Get by id .. " + id);
        Cursa found = cursaRepository.findOne(id);
        if(found == null)
            return new ResponseEntity<String>("user not found!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Cursa>(found,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Cursa create(@RequestBody Cursa cursa){
        System.out.println("Saving race ...");
        cursaRepository.save(cursa);
        return cursa;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Cursa update(@RequestBody Cursa cursa){
        System.out.println("updating race ... " + cursa.getId());
        cursaRepository.update(cursa.getId(),cursa);
        return cursa;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("remove user ... " + id);
        try{
            cursaRepository.delete(id);
            return new ResponseEntity<Cursa>(HttpStatus.OK);
        }
        catch(RepositoryException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error(RepositoryException e){return e.getMessage();}

}
