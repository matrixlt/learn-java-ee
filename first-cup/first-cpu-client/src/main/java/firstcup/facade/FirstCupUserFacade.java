package firstcup.facade;

import firstcup.entity.FirstCupUser;
import java.util.List;

public interface FirstCupUserFacade {

    public void create(FirstCupUser entity);

    public void edit(FirstCupUser entity);

    public void remove(FirstCupUser entity);

    public FirstCupUser find(Object id);

    public List<FirstCupUser> findAll();

    public List<FirstCupUser> findRange(int[] range);
    
    public double findAverageAgeDifference();

    public int count();
}
