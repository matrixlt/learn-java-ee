package jpasimplemodel;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;
    
    public static void main(String[] args) {
        Main.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todoList = q.getResultList();
        todoList.forEach((todo) -> {
            System.out.println(todo);
        });
        System.out.println("Size: " + todoList.size());

        // create new todo
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Todo todo = new Todo();
        todo.setSummary("This is a test");
        todo.setDescription("This is a test");
        em.persist(todo);
        tx.commit();

        em.close();
    }
}
