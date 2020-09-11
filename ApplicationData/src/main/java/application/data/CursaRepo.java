package application.data;

import model.Cursa;

public interface CursaRepo extends CRUDRepository<Integer, Cursa> {
    Cursa returnCursaByCapacitate(String capacitate);
    int getParticipanti(Integer idCursa);
}
