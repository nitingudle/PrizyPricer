package com.nitin.prizy.model;

import java.util.List;
import com.nitin.prizy.entities.Product;
import com.nitin.prizy.rest.response.Price;

public interface IPriceMaker
{
    public Price idealPrice(List<Product> list);
}
