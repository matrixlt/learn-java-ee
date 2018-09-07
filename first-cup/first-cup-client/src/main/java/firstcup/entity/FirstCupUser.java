package firstcup.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@NamedQuery(name = "findAverageAgeDifference",
        query = "SELECT AVG(u.ageDifference) FROM FirstCupUser u")
public class FirstCupUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthday;

    @Column(nullable = false)
    private int ageDifference;

    // Default constructor must be provided
    public FirstCupUser() {
    }

    public FirstCupUser(Date date, int difference) {
        Calendar inCalendar = new GregorianCalendar();
        inCalendar.setTime(date);
        this.birthday = inCalendar;
        this.ageDifference = difference;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getAgeDifference() {
        return ageDifference;
    }

    public void setAgeDifference(int ageDifference) {
        this.ageDifference = ageDifference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "firstcup.entity.FirstcupUser [id=" + id + "]";
    }
}
