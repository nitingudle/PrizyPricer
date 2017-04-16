package com.nitin.prizy.rest.response;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
public class Product
{
    private String productName;
    private String productCode;
    private String productDesc;
    
    @XmlAttribute
    public String getProductName()
    {
        return productName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    @XmlAttribute
    public String getProductCode()
    {
        return productCode;
    }
    public void setProductCode(String storeCode)
    {
        this.productCode = storeCode;
    }
    @XmlAttribute
    public String getProductDesc()
    {
        return productDesc;
    }
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }
}
