package net.isetjb.hibernatetutorial3;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Application class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class Application
{
    /**
     * Attribute declaration for factory to share between methods.
     */
    private static SessionFactory factory;

    public static void main(String[] args)
    {
        System.out.println("JavaSE + Maven + Hibernate + MySQL : Annotations");

        // Open connection  pool
        factory = HibernateUtil.getSessionFactory();

        // CRUD calls examples
        addProduct("y", 1);
        //addProduct("uu", 999);
        //listProducts();
        updateProduct(1, "nn", 333);
        //updateProduct(35, "cc", 222);
        //deleteProduct(4);
        //deleteProduct(9);
        listProducts();

        // Cleaning up connection pool
        factory.close();

    }

    /**
     * Method to save Product in database.
     *
     * @param name
     * @param price
     */
    public static void addProduct(String name, int price)
    {
        Session session = factory.openSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            // insert new product
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            int inserted_id = (Integer) session.save(product);
            System.err.println("Inserted ID : " + inserted_id);

            transaction.commit();

        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally
        {
            session.close();
        }
    }

    /**
     * Method to get list of all Products.
     */
    public static void listProducts()
    {
        Session session = factory.openSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            // Get products by executing HQL Query
            List products = session.createQuery("FROM Product").list();

            for (Iterator iterator = products.iterator(); iterator.hasNext();)
            {
                Product product = (Product) iterator.next();
                System.out.print("ID: " + product.getId());
                System.out.print(" ===> NAME: " + product.getName());
                System.out.println(" ===> PRICE: " + product.getPrice());
            }

            transaction.commit();

        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally
        {
            session.close();
        }
    }

    /**
     * Method to update one Product.
     *
     * @param id
     * @param name
     * @param price
     */
    public static void updateProduct(int id, String name, int price)
    {
        Session session = factory.openSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            // get the product by id
            Product product = (Product) session.get(Product.class, id);

            product.setName(name);
            product.setPrice(price);

            session.update(product);
            transaction.commit();

        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally
        {
            session.close();
        }
    }

    /**
     * Method to delete one Product.
     *
     * @param id
     */
    public static void deleteProduct(int id)
    {
        Session session = factory.openSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            // get the product by id
            Product product = (Product) session.get(Product.class, id);

            session.delete(product);
            transaction.commit();

        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally
        {
            session.close();
        }
    }
}
