package user;

import java.util.List;

public interface Users_DAO {
    
    boolean insert(Users u);
    boolean update(Users u);
    boolean delete(int user_id);
    
    Users getUser(int user_id);
    List<Users> getUser();
    
    int getNextUserId();
}
