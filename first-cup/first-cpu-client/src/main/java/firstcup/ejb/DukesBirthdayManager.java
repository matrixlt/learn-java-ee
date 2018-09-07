package firstcup.ejb;

import firstcup.entity.FirstCupUser;
import java.util.List;
import java.util.Date;

public interface DukesBirthdayManager {

    public int getDukesAge();

    public void saveUserBirthday(Date brithday);

    public int getAgeDifferenceToDuke(Date birthday);

    public double getAverageAgeDifference();
    
    public List<FirstCupUser> getAllUser();
    
    public List<FirstCupUser> getUserRange(int from, int to);
}
