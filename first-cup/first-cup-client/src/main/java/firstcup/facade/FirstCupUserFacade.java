package firstcup.facade;

import firstcup.entity.FirstCupUser;

import java.util.List;

@SuppressWarnings("unused")
public interface FirstCupUserFacade {

    void create(FirstCupUser entity);

    void edit(FirstCupUser entity);

    void remove(FirstCupUser entity);

    FirstCupUser find(Object id);

    List<FirstCupUser> findAll();

    List<FirstCupUser> findRange(int[] range);

    double findAverageAgeDifference();

    int count();
}
