package firstcup.ejb;

import firstcup.entity.FirstCupUser;
import firstcup.facade.FirstCupUserFacade;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class DefaultDukesBirthdayManagerBean implements DukesBirthdayManager {

    private static final Logger logger = Logger.getLogger(DefaultDukesBirthdayManagerBean.class.getName());

    private Integer dukesAge;

    @Inject
    private FirstCupUserFacade userFacade;

    private static int dateToAge(Date date) {
        Calendar birthday = new GregorianCalendar();
        birthday.setTime(date);
        Calendar now = GregorianCalendar.getInstance();

        int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        birthday.add(Calendar.YEAR, age);

        if (now.before(birthday)) {
            age--;
        }
        return age;
    }

    @Produces
    @PostConstruct
    public void init() {
        this.fetchDukesAge();
        logger.log(Level.INFO, "DefaultDukesBirthdayManager initialized");
    }

    private void fetchDukesAge() {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/dukes-age/webapi/dukesAge");
            String response = target.request().get(String.class);
            int age = Integer.parseInt(response);
            this.setDukesAge(age);
            logger.log(Level.INFO, "Duke''s age set to {0}", age);
        } catch (IllegalArgumentException | NullPointerException | WebApplicationException ex) {
            logger.log(Level.SEVERE, "processing of HTTP response failed", ex);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public void saveUserBirthday(Date birthday) {
        FirstCupUser user = new FirstCupUser(birthday, getAgeDifferenceToDuke(birthday));
        userFacade.create(user);
    }

    @Override
    public int getAgeDifferenceToDuke(Date birthday) {
        logger.log(Level.SEVERE, "user birthday is {0}", birthday);
        int age = dateToAge(birthday);
        logger.log(Level.SEVERE, "user age is {0}", age);
        return age - this.getDukesAge();
    }

    @Override
    public double getAverageAgeDifference() {
        double result = userFacade.findAverageAgeDifference();
        logger.log(Level.SEVERE, "average age difference is {0}", result);
        return result;
    }

    @Override
    public int getDukesAge() {
        if (this.dukesAge != null) {
            return this.dukesAge;
        } else {
            throw new WebApplicationException("Failed to fetch Duke's age");
        }
    }

    private void setDukesAge(Integer age) {
        this.dukesAge = age;
    }

    @Override
    public List<FirstCupUser> getAllUser() {
        return userFacade.findAll();
    }

    @Override
    public List<FirstCupUser> getUserRange(int from, int to) {
        return userFacade.findRange(new int[]{from, to});
    }
}
