package com.nitin.prizy.rest.api;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nitin.prizy.model.IPriceMaker;
import com.nitin.prizy.model.ProductDAO;
import com.nitin.prizy.rest.response.RestResponse;
import com.nitin.prizy.rest.utils.APIConstant;

@Component
public class CommonResource implements APIConstant
{
    private static Logger LOGGER = LoggerFactory.getLogger(CommonResource.class);

    @Autowired
    protected ProductDAO  productDAO;

    @Autowired
    protected IPriceMaker priceMaker;

    protected String      storeName;
    protected String      productName;
    protected String      productCode;
    protected String      productDesc;
    protected BigDecimal  productPrice;
    protected String      startIndex;
    protected String      limit;
    private String        acceptType;
    private String        contentType;

    protected void init(HttpServletRequest request)
    {
        if (request != null)
        {
            acceptType = request.getHeader("Accept");
            contentType = request.getHeader("Content-Type");
            LOGGER.info("Accept" + "=" + acceptType);
            LOGGER.info("Content-Type" + "=" + contentType);
            @SuppressWarnings("unchecked")
            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements())
            {
                String param = names.nextElement();
                LOGGER.info(param + "=" + request.getParameter(param));
            }
            storeName = request.getParameter("store");
            productName = request.getParameter("productName");
            productCode = request.getParameter("productCode");
            productDesc = request.getParameter("notes");
            startIndex = request.getParameter("startIndex");
            limit = request.getParameter("limit");
            if (request.getParameter("price") != null && request.getParameter("price").length() != 0)
                productPrice = new BigDecimal(request.getParameter("price"));
        }
    }

    protected boolean validateProduct(RestResponse obj)
    {
        boolean valid = true;
        if (storeName == null || storeName.length() == 0)
        {
            valid = false;
            obj.setError(INVALID_STORE);
        }
        else if (productCode == null || productCode.length() == 0)
        {
            valid = false;
            obj.setError(INVALID_PRODUCT);
        }
        else if (productPrice == null)
        {
            valid = false;
            obj.setError(INVALID_PRICE);
        }
        if (!valid)
            obj.responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        return valid;
    }

    protected boolean validateCode(RestResponse obj)
    {
        boolean valid = true;
        if (productCode == null || productCode.length() == 0)
        {
            valid = false;
            obj.setError(INVALID_PRODUCT);
            obj.responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        }
        return valid;
    }

    protected boolean validateLimit(RestResponse obj)
    {
        boolean valid = true;
        if ((startIndex == null || startIndex.length() == 0) && (limit == null || limit.length() == 0))
        {
            valid = false;
            obj.setError(INVALID_LIMIT);
            obj.responseCode = HttpURLConnection.HTTP_BAD_REQUEST;
        }
        return valid;
    }
}
