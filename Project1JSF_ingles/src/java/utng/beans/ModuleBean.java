package utng.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utng.dates.ModuleDAO;
import utng.models.Module;

@ManagedBean
@SessionScoped
public class ModuleBean implements Serializable {
    private List<Module>modules;
    private Module module;
    public ModuleBean(){}

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> Modules) {
        this.modules = Modules;
    }
    
    public String list(){
        ModuleDAO dao = new ModuleDAO();
        try {
            modules=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Modules";
    }
    
    public String delete(){
         ModuleDAO dao = new ModuleDAO();
        try {
            dao.delete(module);
            modules=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "delete";//no me queda claro si este es es lo que devuelve 
    }
    
    public String go(){
        module= new Module();
        return "go";
    }
    
    public String save(){
        ModuleDAO dao = new ModuleDAO();
        try {
            if(module.getIdModule()!= 0){
                dao.update(module);
            }else {
                dao.insert(module);
            }
            modules=dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "save";
    }
    
    public String cancel(){
    return "cancel";
    }
    
    public String edit(Module module){
        this.module=module;
        return "edit";
    }
        
}
