package utng.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utng.dates.RoleDAO;
import utng.models.Role;

@ManagedBean
@SessionScoped
public class RoleBean implements Serializable{
    private List<Role>roles;
    private Role role;
    public RoleBean(){}

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
    public String list(){
        RoleDAO dao = new RoleDAO();
        try {
            roles=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Roles";
    }
    
    public String delete(){
         RoleDAO dao = new RoleDAO();
        try {
            dao.delete(role);
            roles=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete";
    }
    
    public String go(){
        role= new Role();
        return "go";
    }
    
    public String save(){
        RoleDAO dao = new RoleDAO();
        try {
            if(role.getIdRole()!= 0){
                dao.update(role);
            }else {
                dao.insert(role);
            }
            roles=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "save";
    }
    
    public String cancel(){
    return "cancel";
    }
    
    public String edit(Role role){
        this.role=role;
        return "edit";
    }
    
}
