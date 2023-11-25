import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();
        Map<String, Integer> courses = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

//        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> queryPL = criteriaBuilder.createQuery(PurchaseList.class);
        CriteriaQuery<Student> queryS = criteriaBuilder.createQuery(Student.class);
        CriteriaQuery<Course> queryC = criteriaBuilder.createQuery(Course.class);

        Root<PurchaseList> rootPL = queryPL.from(PurchaseList.class);
        Root<Student> rootS = queryS.from(Student.class);
        Root<Course> rootC = queryC.from(Course.class);

        queryPL.select(rootPL);
        queryS.select(rootS);
        queryC.select(rootC);

        List<Student> listS = session.createQuery(queryS).getResultList();

        for (Student s : listS) {
            students.put(s.getName(), s.getId());
        }

        List<Course> listC = session.createQuery(queryC).getResultList();
        for (Course c : listC) {
            courses.put(c.getName(), c.getId());
        }

        List<PurchaseList> list = session.createQuery(queryPL).getResultList();

        for (PurchaseList l : list) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

            linkedPurchaseList.setId(new KeyLPL(students.get(l.getStudentName()), courses.get(l.getCourseName())));

            session.save(linkedPurchaseList);
        }


        transaction.commit();
        sessionFactory.close();

    }

}
