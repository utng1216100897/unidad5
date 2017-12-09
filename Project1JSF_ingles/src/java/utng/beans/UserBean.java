package utng.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

//import net.sf.jasperreports.engine.JasperPrint;
import utng.dates.RoleDAO;
import utng.dates.UserDAO;
import utng.models.Role;
import utng.models.User;

@ManagedBean(name="userBean") 
@SessionScoped
public class UserBean implements Serializable{
    private List<User> users;
    private User user;
    private List<Role> roles;
    public UserBean(){
        user = new User();
        user.setRole(new Role());
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
     public String list(){
        UserDAO dao = new UserDAO();
        try {
            users=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Users";
    }
    
    public String delete(){
         UserDAO dao = new UserDAO();
        try {
            dao.delete(user);
            users=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete";//no me queda claro si este es es lo que devuelve 
    }
    
    public String go(){
        user= new User();
        user.setRole(new Role());
        try {
            roles= new RoleDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "go";
    }
    
    public String save(){
        UserDAO dao = new UserDAO();
        try {
            if(user.getIdUser()!= 0){
                dao.update(user);
            }else {
                dao.insert(user);
            }
            users=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "save";
    }
    
    public String cancel(){
    return "cancel";
    }
    
    public String edit(User user){
        this.user=user;
        try {
            roles = new RoleDAO().getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit";
    }
    
    public String login(){
        user = new UserDAO().login(this.user);
        if(user != null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",user);
            return "Correct";
        }else {
            user = new User();
            return "Incorrect";
        }
    }
    
    public String print(){
        
        UserDAO dao = new UserDAO();
      
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/master_report.jasper");
       
        try{
            JasperReport report = (JasperReport)JRLoader.loadObjectFromFile(reportPath);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap(), dao.getConnection());
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Context-dispostion", "attachment; filename= report.pdf");
            ServletOutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            FacesContext.getCurrentInstance().responseComplete();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "print";
    }
    
    public String logout(){
        if(user!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        }
        user = new User();
        return "out";
        
    }
    
    
}