package lt.bta.java.jpa;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;


@WebListener
public class PersistenceListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(PersistenceListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersistenceUtil.getEntityManagerFactory();
        logger.info("EntityManagerFactory initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PersistenceUtil.getEntityManagerFactory().close();
        logger.info("EntityManagerFactory closed");
    }
}

