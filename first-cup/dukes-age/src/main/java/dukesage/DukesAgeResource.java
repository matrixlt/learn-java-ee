package dukesage;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("dukesAge")
public class DukesAgeResource {

    private static final Calendar DUKES_BIRTHDAY = new GregorianCalendar(1995, Calendar.MAY, 23);

    @GET
    @Produces("text/plain")
    public String getText() {
        Calendar now = GregorianCalendar.getInstance();
        int age = now.get(Calendar.YEAR) - DUKES_BIRTHDAY.get(Calendar.YEAR);
        Calendar dukesBirthday = (Calendar) DUKES_BIRTHDAY.clone();
        dukesBirthday.add(Calendar.YEAR, age);
        if (now.before(dukesBirthday)) {
            age--;
        }
        return ((Integer) age).toString();
    }
}
