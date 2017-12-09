package utng.dates;

/**
 *
 * @author gerardo
 */
import org.hibernate.HibernateException;
import utng.models.Team;


public class TeamDAO extends DAO<Team> {
     public TeamDAO() {
        super(new Team());
    }

    
    public Team getOneById(Team team) throws HibernateException {
        return super.getOneById(team.getIdTeam()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}