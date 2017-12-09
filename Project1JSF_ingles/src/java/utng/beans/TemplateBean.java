
package utng.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utng.models.User;


@ManagedBean
@SessionScoped
public class TemplateBean implements Serializable{
    public void checkLogin(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            User user = (User)context.getExternalContext().getSessionMap().get("user");
            if(user == null){
                context.getExternalContext().redirect("login.xhtml");
            }
        } catch (Exception e) {
        }
    }
}