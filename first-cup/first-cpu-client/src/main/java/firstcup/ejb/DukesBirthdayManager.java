package firstcup.ejb;

import java.util.Date;
import javax.ws.rs.WebApplicationException;

public interface DukesBirthdayManager {

    public int getDukesAge();

    public void saveUserBirthday(Date brithday);

    public int getAgeDifferenceToDuke(Date birthday);

    public double getAverageAgeDifference();
}
