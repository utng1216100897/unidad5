package utng.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utng.dates.TeamDAO;
import utng.dates.SoccerPlayerDAO;
import utng.models.Team;
import utng.models.SoccerPlayer;

/**
 *
 * @author gerardo
 */
@ManagedBean(name="soccerPlayerBean") 
@SessionScoped
public class SoccerPlayerBean implements Serializable{
    private List<SoccerPlayer> soccerPlayers;
    private SoccerPlayer soccerPlayer;
    private List<Team> teams;
    
    public SoccerPlayerBean(){
        soccerPlayer = new SoccerPlayer();
        soccerPlayer.setTeam(new Team());
    }

    public List<SoccerPlayer> getSoccerPlayers() {
        return soccerPlayers;
    }
    public void setSoccerPlayers(List<SoccerPlayer> soccerPlayers) {
        this.soccerPlayers = soccerPlayers;
    }
    public SoccerPlayer getSoccerPlayer() {
        return soccerPlayer;
    }
    public void setSoccerPlayer(SoccerPlayer soccerPlayer) {
        this.soccerPlayer = soccerPlayer;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
     public String list(){
        SoccerPlayerDAO dao = new SoccerPlayerDAO();
        try {
            soccerPlayers=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SoccerPlayers";
    }
    
    public String delete(){
         SoccerPlayerDAO dao = new SoccerPlayerDAO();
        try {
            dao.delete(soccerPlayer);
            soccerPlayers=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete";
    }
    
    public String go(){
        soccerPlayer= new SoccerPlayer();
        soccerPlayer.setTeam(new Team());
        try {
            teams= new TeamDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "go";
    }
    
    public String save(){
        SoccerPlayerDAO dao = new SoccerPlayerDAO();
        try {
            if(soccerPlayer.getIdSoccerPlayer()!= 0){
                dao.update(soccerPlayer);
            }else {
                dao.insert(soccerPlayer);
            }
            soccerPlayers=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "save";
    }
    
    public String cancel(){
    return "cancel";
    }
    
    public String edit(SoccerPlayer soccerPlayer){
        this.soccerPlayer=soccerPlayer;
        try {
            teams = new TeamDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit";
    }
    
  
    
    
}
