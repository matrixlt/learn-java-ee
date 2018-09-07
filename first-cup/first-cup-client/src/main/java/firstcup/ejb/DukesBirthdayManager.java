package firstcup.ejb;

import firstcup.entity.FirstCupUser;

import java.util.Date;
import java.util.List;

public interface DukesBirthdayManager {

    int getDukesAge();

    void saveUserBirthday(Date brithday);

    int getAgeDifferenceToDuke(Date birthday);

    double getAverageAgeDifference();

    List<FirstCupUser> getAllUser();

    List<FirstCupUser> getUserRange(int from, int to);
}
