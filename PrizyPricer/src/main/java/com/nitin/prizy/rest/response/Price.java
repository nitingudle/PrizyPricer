package com.nitin.prizy.rest.response;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Price")
public class Price extends RestResponse
{
    private String     productName;
    private String     productCode;
    private String     productDesc;
    private BigDecimal averagePrice;
    private BigDecimal lowestPrice;
    private BigDecimal highestPrice;
    private BigDecimal idealPrice;
    private int        total;

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
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
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
    public BigDecimal getAveragePrice()
    {
        return averagePrice;
    }
    public void setAveragePrice(BigDecimal averagePrice)
    {
        this.averagePrice = averagePrice;
    }
    @XmlAttribute
    public BigDecimal getLowestPrice()
    {
        return lowestPrice;
    }
    public void setLowestPrice(BigDecimal lowestPrice)
    {
        this.lowestPrice = lowestPrice;
    }
    @XmlAttribute
    public BigDecimal getHighestPrice()
    {
        return highestPrice;
    }
    public void setHighestPrice(BigDecimal highestPrice)
    {
        this.highestPrice = highestPrice;
    }
    @XmlAttribute
    public BigDecimal getIdealPrice()
    {
        return idealPrice;
    }
    @XmlAttribute
    public void setIdealPrice(BigDecimal idealPrice)
    {
        this.idealPrice = idealPrice;
    }
    @XmlAttribute
    public int getTotal()
    {
        return total;
    }
    public void setTotal(int total)
    {
        this.total = total;
    }
}
