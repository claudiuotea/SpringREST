package application.data.Repository;

import application.data.CursaRepo;
import application.data.RepositoryException;
import model.Cursa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class CursaRepository implements CursaRepo {
    private final String findAllQUERY = "select * from Cursa";
    private final String findOneQUERRY = "select * from Cursa where idCursa = ?";
    private final String findParticipanti = "select count(*) as TOTAL from Participanti p where p.idCursa == ?";
    private final String addQUERRY = "insert into Cursa(capCilindrica,numarParticipanti) values (?,?)";
    private final String updateQuerry = "UPDATE Cursa SET capCilindrica = ?, numarParticipanti = ? WHERE idCursa = ?";
    private final String deleteQuerry = "DELETE FROM Cursa WHERE idCursa = ?";
    DatabaseUtils databaseUtils;
    private static final Logger LOGGER = LogManager.getLogger();

    public CursaRepository(DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void save(Cursa entity) {
        Connection connection = databaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addQUERRY);
            //preparedStatement.setInt(1,entity.getId());
            preparedStatement.setString(1,entity.getCapCilindrica());
            preparedStatement.setInt(2,entity.getNumarParticipanti());
            preparedStatement.executeUpdate();
            LOGGER.info("Done, race saved!");
        } catch (SQLException e) {
            LOGGER.error("Error in saving the new race!",e);
        }
    }

    @Override
    public void update(Integer integer, Cursa entity) {
        Connection connection = databaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuerry);
            //preparedStatement.setInt(1,entity.getId());
            preparedStatement.setString(1,entity.getCapCilindrica());
            preparedStatement.setInt(2,entity.getNumarParticipanti());
            preparedStatement.setInt(3,integer);
            preparedStatement.executeUpdate();
            LOGGER.info("Done, update made!");
        } catch (SQLException e) {
            LOGGER.error("Error in updating the new race!",e);
        }
    }

    @Override
    public void delete(Integer integer) throws RepositoryException {
        Connection connection = databaseUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuerry);
            //preparedStatement.setInt(1,entity.getId());
            preparedStatement.setInt(1,integer);
            preparedStatement.executeUpdate();
            LOGGER.info("Done, race removed!");
        } catch (SQLException e) {
            LOGGER.error("Error in removing the race!",e);
            throw new RepositoryException("Doesn't exist!");
        }
    }

    @Override
    public Cursa findOne(Integer integer) {
        LOGGER.info("Finding one race--");
        Connection connection = databaseUtils.getConnection();
        Cursa cursa = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findOneQUERRY);
            preparedStatement.setInt(1,integer);
            ResultSet rs = preparedStatement.executeQuery();

            String capCilindrica = rs.getString("capCilindrica");
            Integer idCursa = rs.getInt("idCursa");
            Integer numarParticipanti =rs.getInt("numarParticipanti");
            cursa = new Cursa(capCilindrica,idCursa,numarParticipanti);
            LOGGER.info("Race found--");
            }
         catch (SQLException e) {
            LOGGER.error("Error on finding one race",e);
        }
        return cursa;

    }

    @Override
    public Cursa[] findAll() {
        LOGGER.info("Finding all races--");
        Connection connection = databaseUtils.getConnection();
        ArrayList<Cursa> curse = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAllQUERY);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String capCilindrica = rs.getString("capCilindrica");
                Integer idCursa = rs.getInt("idCursa");
                Integer numarParticipanti =rs.getInt("numarParticipanti");
                Cursa cursa = new Cursa(capCilindrica,idCursa,numarParticipanti);
                curse.add(cursa);
                LOGGER.info("Found all races, returning them--");

            }
        } catch (SQLException e) {
            LOGGER.info("Error returning all races",e);
        }
        Cursa[] races = new Cursa[curse.size()];
        curse.toArray(races);
        return races;
    }


    @Override
    public Cursa returnCursaByCapacitate(String capacitate) {
        Cursa forReturn = null;
        for(Cursa c: findAll())
            if(c.getCapCilindrica().equals(capacitate))
                forReturn=c;
        return forReturn;
    }

    @Override
    public int getParticipanti(Integer idCursa) {
        Connection connection = databaseUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int numberOfParticipanti = 0;
        try {
            preparedStatement = connection.prepareStatement(findParticipanti);
            preparedStatement.setInt(1,idCursa);
            ResultSet rs = preparedStatement.executeQuery();
            numberOfParticipanti = rs.getInt("TOTAL");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfParticipanti;
    }
}
