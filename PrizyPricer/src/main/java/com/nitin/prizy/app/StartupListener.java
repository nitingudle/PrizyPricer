package com.nitin.prizy.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.nitin.prizy.rest.api.ProductService;

@Component
public class StartupListener implements ServletContextListener
{
    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        LOGGER.info("Application is destroyed .................");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        LOGGER.info("Application context is initialized.....");
    }

}
