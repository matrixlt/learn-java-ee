package firstcup.web;

import firstcup.ejb.DukesBirthdayManager;
import firstcup.entity.FirstCupUser;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class DukesBirthday implements Serializable {

    private static final Logger logger = Logger.getLogger(DukesBirthday.class.getName());

    @EJB
    private DukesBirthdayManager birthdayManager;

    @NotNull
    private Date yourBirthday;

    private int ageDiff;
    private int ageDiffAbs;

    @PostConstruct
    public void init() {
        // For debug
        logger.log(Level.INFO, "DukesBirthday initialized");
    }

    public int getDukesAge() {
        return birthdayManager.getDukesAge();
    }

    public double getAverageAgeDifference() {
        return birthdayManager.getAverageAgeDifference();
    }

    public String processBirthday() {
        this.ageDiff = birthdayManager.getAgeDifferenceToDuke((Date) this.yourBirthday.clone());
        this.ageDiffAbs = Math.abs(this.ageDiff);
        birthdayManager.saveUserBirthday(this.yourBirthday);
        return "response";
    }

    public Date getYourBirthday() {
        return yourBirthday;
    }

    public void setYourBirthday(Date yourBirthday) {
        this.yourBirthday = yourBirthday;
    }

    public int getAgeDiffAbs() {
        return ageDiffAbs;
    }

    public void setAgeDiffAbs(int ageDiffAbs) {
        this.ageDiffAbs = ageDiffAbs;
    }

    public int getAgeDiff() {
        return ageDiff;
    }

    public void setAgeDiff(int ageDiff) {
        this.ageDiff = ageDiff;
    }
    
    public List<FirstCupUser> getAllUser() {
        return birthdayManager.getAllUser();
    }
}
