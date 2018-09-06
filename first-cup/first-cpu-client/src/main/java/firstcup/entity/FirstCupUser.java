package firstcup.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "first_cup_user")
@NamedQuery(name = "findAverageAgeDifference",
        query = "SELECT AVG(u.ageDifference) FROM FirstCupUser u")
public class FirstCupUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Calendar birthday;

    @Column(nullable = false)
    protected int ageDifference;

    // Default constructor must be provided
    public FirstCupUser() {
    }

    public FirstCupUser(Calendar birthday, int difference) {
        this.birthday = birthday;
        this.ageDifference = difference;
    }

    public FirstCupUser(Date date, int difference) {
        Calendar birthday = new GregorianCalendar();
        birthday.setTime(date);
        this.birthday = birthday;
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
