package utng.dates;

import org.hibernate.HibernateException;
import utng.dates.DAO;
import utng.models.Module;


public class ModuleDAO extends DAO<Module> {

    public ModuleDAO() {
        super(new Module());
    }

    
    public Module getOneById(Module module) throws HibernateException {
        return super.getOneById(module.getIdModule()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
