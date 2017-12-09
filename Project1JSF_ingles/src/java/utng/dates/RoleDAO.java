package utng.dates;

import org.hibernate.HibernateException;
import utng.models.Role;


public class RoleDAO extends DAO<Role> {
     public RoleDAO() {
        super(new Role());
    }

    
    public Role getOneById(Role role) throws HibernateException {
        return super.getOneById(role.getIdRole()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
