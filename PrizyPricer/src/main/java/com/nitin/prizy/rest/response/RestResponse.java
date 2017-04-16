package com.nitin.prizy.rest.response;

import javax.xml.bind.annotation.XmlElement;

public abstract class RestResponse
{
    private String       error;
    private String       message;
    public transient int responseCode = 202;

    @XmlElement
    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    @XmlElement
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
