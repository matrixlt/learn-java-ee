package jpasimplemodel;

import javax.persistence.*;
import java.util.List;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;
    
    public static void main(String[] args) {
        Main.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        TypedQuery<Todo> q = em.createQuery("select t from Todo t", Todo.class);
        List<Todo> todoList = q.getResultList();
        todoList.forEach(System.out::println);
        System.out.println("Size: " + todoList.size());

        // create new
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
