package Hiber;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import com.journaldev.hibernate.model.Book;
//import com.journaldev.hibernate.model.Page;
//import com.journaldev.hibernate.util.HibernateUtil;



public class App {
    public static void main(String[] args) {
        Book book1 = new Book();
        book1.setName("first book");

        Page page1 = new Page(1, "Source", book1);
        Page page2 = new Page(2, "Re-source", book1);



        Set<Page> itemsSet = new HashSet<Page>();
        itemsSet.add(page1);
        itemsSet.add(page2);

        book1.setPages(itemsSet);

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            //Get Session
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            System.out.println("Session created");
            //start transaction
            tx = session.beginTransaction();

            //Save the Model objects
            session.save(book1);
            session.save(page1);
            session.save(page2);

            //Commit transaction
            tx.commit();
            System.out.println("Book ID="+book1.getId());

        }catch(Exception e){
            System.out.println("Exception occured. "+e.getMessage());
            e.printStackTrace();
        }finally{
            if(!sessionFactory.isClosed()){
                System.out.println("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }
}
