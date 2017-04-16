package com.nitin.prizy.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.nitin.prizy.entities.Product;

@Repository
public class ProductDAOImpl extends CRUD implements ProductDAO
{
    private static Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public void create(Product product)
    {
        LOGGER.info("SAVE :" + product);
        super.saveObject(product);
    }

    @Override
    public List<Product> get(int startIndex, int limit)
    {
        List<Product> list = new ArrayList<>();
        LOGGER.info("Get Products");
        try
        {
            String sql = "from Product group by productCode";
            Query query = entityManager.createQuery(sql);
            query.setFirstResult(startIndex);
            query.setMaxResults(limit);
            list = query.getResultList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Override
    public int get()
    {
        Long size = 0l;
        LOGGER.info("Get Products");
        try
        {
            String sql = "select count(DISTINCT p.productCode) from Product as p";
            Query query = entityManager.createQuery(sql);
            size = (Long) query.getSingleResult();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return size.intValue();
    }

    @Override
    public List<Product> get(String productCode)
    {
        List<Product> list = new ArrayList<>();
        LOGGER.info("Get Products");
        try
        {
            String sql = "from Product where productCode=:productCode";
            Query query = entityManager.createQuery(sql);
            query.setParameter("productCode", productCode);
            list = query.getResultList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return list;
    }
}
