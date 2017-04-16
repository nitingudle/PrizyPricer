package com.nitin.prizy.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable , Comparable<Product>
{
    private static final long serialVersionUID = 1L;

    @Column(name = "PRODUCT_PKUID")
    private String            pkuid;

    @Column(name = "PRODUCT_Name")
    private String            productName;

    @Id
    @Column(name = "STORE_NAME", nullable = false)
    private String            storeName;

    @Id
    @Column(name = "PRODUCT_CODE", nullable = false)
    private String            productCode;

    @Column(name = "PRODUCT_DESC")
    private String            productDesc;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal        productPrice;

    public Product()
    {
    }

    public Product(String pkuid, String productName, String storeName, String productCode, String productDesc, BigDecimal productPrice)
    {
        super();
        this.pkuid = pkuid;
        this.productName = productName;
        this.storeName = storeName;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
    }

    public String getPkuid()
    {
        return pkuid;
    }

    public void setPkuid(String pkuid)
    {
        this.pkuid = pkuid;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductDesc()
    {
        return productDesc;
    }

    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }

    public BigDecimal getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    @Override
    public int compareTo(Product o)
    {
        return this.productPrice.compareTo(o.productPrice);
    }

    @Override
    public String toString()
    {
        return "Product [pkuid=" + pkuid + ", productName=" + productName + ", storeName=" + storeName + ", productCode=" + productCode + ", productDesc=" + productDesc + ", productPrice=" + productPrice + "]";
    }

}
