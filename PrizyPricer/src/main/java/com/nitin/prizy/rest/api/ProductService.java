package com.nitin.prizy.rest.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nitin.prizy.entities.Product;
import com.nitin.prizy.rest.response.Price;
import com.nitin.prizy.rest.response.ProductResponse;
import com.nitin.prizy.rest.utils.APIConstant;
import com.nitin.prizy.rest.utils.Utility;

@Controller
@Scope(value = "prototype")
public class ProductService extends CommonResource
{
    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @RequestMapping(value = APIConstant.PRODUCTS, method = RequestMethod.POST, produces = { "application/json", "application/xml" })
    public @ResponseBody ResponseEntity<ProductResponse> create(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException
    {
        ProductResponse productResponse = new ProductResponse();
        try
        {
            // Initialize
            init(request);
            if (validateProduct(productResponse))
            {
                Product product = new Product(Utility.getUniqueKey(Product.class), productName, storeName, productCode, productDesc, productPrice);
                productDAO.create(product);
                productResponse.responseCode = HttpURLConnection.HTTP_NO_CONTENT;
            }
        }
        catch (Exception e)
        {
            String msg = e.getMessage();
            productResponse.setError(msg == null ? e.toString() : msg);
            productResponse.responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.valueOf(productResponse.responseCode));
    }

    @RequestMapping(value = APIConstant.PRODUCTS, method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public @ResponseBody ResponseEntity<ProductResponse> getProducts(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException
    {
        ProductResponse productResponse = new ProductResponse();
        try
        {
            // Initialize
            init(request);
            if (validateLimit(productResponse))
            {
                List<Product> list = productDAO.get(Integer.parseInt(startIndex), Integer.parseInt(limit));
                if (!list.isEmpty())
                {
                    List<com.nitin.prizy.rest.response.Product> products = new ArrayList<>();
                    for (Product p : list)
                    {
                        com.nitin.prizy.rest.response.Product product = new com.nitin.prizy.rest.response.Product();
                        product.setProductDesc(p.getProductDesc());
                        product.setProductCode(p.getProductCode());
                        product.setProductName(p.getProductName());
                        products.add(product);
                    }
                    productResponse.setSize(productDAO.get());
                    productResponse.setProducts(products);
                }
            }
        }
        catch (Exception e)
        {
            productResponse.setError(e.getMessage());
            productResponse.responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.valueOf(productResponse.responseCode));
    }

    @RequestMapping(value = APIConstant.PRODUCT, method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public @ResponseBody ResponseEntity<Price> getProduct(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathVariable("productCode") String productCode) throws IOException
    {
        Price price = new Price();
        try
        {
            // Initialize
            init(request);
            super.productCode = productCode;
            if (validateCode(price))
                if (!productDAO.get(productCode).isEmpty())
                {
                    List<Product> list = productDAO.get(productCode);
                    int total = list.size();
                    price = priceMaker.idealPrice(list);
                    price.setTotal(total);
                }
        }
        catch (Exception e)
        {
            price.setError(e.getMessage());
            price.responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<Price>(price, HttpStatus.valueOf(price.responseCode));
    }

}
