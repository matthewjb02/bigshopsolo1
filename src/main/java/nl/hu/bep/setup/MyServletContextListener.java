package nl.hu.bep.setup;

import nl.hu.bep.shopping.model.Product;
import nl.hu.bep.shopping.model.Shopper;
import nl.hu.bep.shopping.model.ShoppingList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing application");
        Shopper p = new Shopper("Dum-Dum", "geheim", "admin");
        ShoppingList il = new ShoppingList("initialList", p);
        ShoppingList al = new ShoppingList("anotherList", p);
        ShoppingList.addList(il);
        ShoppingList.addList(al);
        p.addList(il);
        p.addList(al);
        il.addItem(new Product("Cola Zero"), 4);
        il.addItem(new Product("Cola Zero"), 4);
        il.addItem(new Product("Toiletpapier 6stk"), 1);
        al.addItem(new Product("Tandpasta 3stk"), 3);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Terminating application");
    }

}
