package com.nitin.prizy.model;

import java.util.List;
import com.nitin.prizy.entities.Product;

public interface ProductDAO
{
    public void create(Product product);
    public List<Product> get(int startIndex, int limit);
    public int get();
    public List<Product> get(String productCode);
}
