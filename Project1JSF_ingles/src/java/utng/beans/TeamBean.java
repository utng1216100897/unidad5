package utng.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utng.dates.TeamDAO;
import utng.models.Team;

@ManagedBean
@SessionScoped
public class TeamBean implements Serializable{
    private List<Team>teams;
    private Team team;
    public TeamBean(){}

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    
    public String list(){
        TeamDAO dao = new TeamDAO();
        try {
            teams=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Teams";
    }
    
    public String delete(){
         TeamDAO dao = new TeamDAO();
        try {
            dao.delete(team);
            teams=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete";
    }
    
    public String go(){
        team= new Team();
        return "go";
    }
    
    public String save(){
        TeamDAO dao = new TeamDAO();
        try {
            if(team.getIdTeam()!= 0){
                dao.update(team);
            }else {
                dao.insert(team);
            }
            teams=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "save";
    }
    
    public String cancel(){
    return "cancel";
    }
    
    public String edit(Team team){
        this.team=team;
        return "edit";
    }
    
}