package utng.dates;


import org.hibernate.HibernateException;
import utng.models.SoccerPlayer;

/**
 *
 * @author gerardo
 */
public class SoccerPlayerDAO  extends DAO<SoccerPlayer>{

    public SoccerPlayerDAO() {
        super(new SoccerPlayer());
    }
    public SoccerPlayer getOneById(SoccerPlayer soccerPlayer) throws HibernateException {
        return super.getOneById(soccerPlayer.getIdSoccerPlayer()); 
    }
    
    
}