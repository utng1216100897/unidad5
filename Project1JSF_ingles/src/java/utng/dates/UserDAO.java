package utng.dates;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import utng.models.User;

public class UserDAO  extends DAO<User>{

    public UserDAO() {
        super(new User());
    }
    public User getOneById(User user) throws HibernateException {
        return super.getOneById(user.getIdUser()); 
    }
    public User login (User user){
        List<String> parameters= new ArrayList<String>();
        List<Object>values= new ArrayList<Object>();
        parameters.add("nameUser");
        parameters.add("password");
        values.add(user.getNameUser());
        values.add(user.getPassword());
        user = query("SELECT u FROM "
                +model.getClass().getName()
                +" u WHERE u.nameUser =:nameUser "
                +"AND u.password=:password",
                        parameters, values);
        return user;
    }
    
}