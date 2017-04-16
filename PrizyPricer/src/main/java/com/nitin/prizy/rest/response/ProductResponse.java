package com.nitin.prizy.rest.response;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductResponse")
public class ProductResponse extends RestResponse
{
    private List<Product> products;
    private int           size;

    @XmlElementWrapper(name = "products")
    @XmlAttribute(name = "product")
    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }

    @XmlAttribute(name = "size")
    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
