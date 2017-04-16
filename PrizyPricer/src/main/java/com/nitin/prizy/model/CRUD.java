package com.nitin.prizy.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class CRUD
{

    static Logger           EMLOGGER = LoggerFactory.getLogger(CRUD.class);
    protected EntityManager entityManager;
    @Autowired
    JpaTransactionManager   transactionManager;

    @Required
    @PersistenceContext(unitName = "prizy_persistence_unit")
    public void setApplicationEntityManager(EntityManager applicationEntityManager)
    {
        entityManager = applicationEntityManager;
    }

    @Transactional
    public Object saveObject(Object object)
    {
        // System.out.println(object);
        TransactionStatus status = null;
        try
        {
            status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            entityManager.persist(object);
            entityManager.flush();
            transactionManager.commit(status);
        }
        catch (Exception e)
        {
            EMLOGGER.error(e.toString());
            transactionManager.rollback(status);
            throw e;
        }
        finally
        {
            try
            {
                entityManager.close();
            }
            catch (Exception e)
            {
                EMLOGGER.error(e.toString());
            }
        }
        return object;
    }
}
