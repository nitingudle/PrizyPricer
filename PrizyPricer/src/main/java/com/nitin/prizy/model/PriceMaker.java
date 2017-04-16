package com.nitin.prizy.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.nitin.prizy.entities.Product;
import com.nitin.prizy.rest.response.Price;

@Component
public class PriceMaker implements IPriceMaker
{
    private static Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public Price idealPrice(List<Product> list)
    {
        LOGGER.info("PRICE MAKER");
        Price price = new Price();
        BigDecimal ideal = new BigDecimal(0);
        try
        {
            System.out.println(list);
            Collections.sort(list);
            System.out.println(list);
            if (list.size() >= 5)
            {
                twoMax(list);
                ideal = findIdeal(list, price);
                price.setIdealPrice(ideal);
            }
            else if (list.size() >= 3)
            {
                oneMax(list);
                ideal = findIdeal(list, price);
                price.setIdealPrice(ideal);
            }
            else if (list.size() >= 2)
            {
                ideal = list.get(1).getProductPrice();
                price.setLowestPrice(list.get(1).getProductPrice());
                price.setHighestPrice(list.get(0).getProductPrice());
                price.setIdealPrice(ideal);
                for (Product product : list)
                    ideal = ideal.add(product.getProductPrice());
                ideal = ideal.divide(new BigDecimal(list.size()), RoundingMode.HALF_UP);
                price.setAveragePrice(ideal);
            }
            else if (list.size() >= 1)
            {
                ideal = list.get(0).getProductPrice();
                price.setLowestPrice(ideal);
                price.setHighestPrice(ideal);
                price.setAveragePrice(ideal);
                price.setIdealPrice(ideal);
            }
            Product p = list.get(0);
            price.setProductCode(p.getProductCode());
            price.setProductDesc(p.getProductDesc());
            price.setProductName(p.getProductName());

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return price;
    }

    private void twoMax(List<Product> list)
    {
        list.remove(0);
        list.remove(0);
        list.remove(list.size() - 1);
        list.remove(list.size() - 1);
    }

    private void oneMax(List<Product> list)
    {
        list.remove(0);
        list.remove(list.size() - 1);
    }

    private BigDecimal findIdeal(List<Product> list, Price p)
    {
        BigDecimal ideal = new BigDecimal(0);
        p.setLowestPrice(list.get(0).getProductPrice());
        p.setHighestPrice(list.get(list.size() - 1).getProductPrice());
        for (Product product : list)
            ideal = ideal.add(product.getProductPrice());
        ideal = ideal.divide(new BigDecimal(list.size()), RoundingMode.HALF_UP);
        p.setAveragePrice(ideal);
        ideal = ideal.multiply(new BigDecimal(20));
        ideal = ideal.divide(new BigDecimal(100), RoundingMode.HALF_UP);
        return ideal;
    }
}
