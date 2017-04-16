package com.nitin.prizy.rest.utils;

import com.nitin.prizy.entities.Product;

public enum Utility
{
    instance;
    public static String getUniqueKey(Class clazz)
    {
        if (clazz != null && clazz.getName().equals("com.nitin.prizy.entities.Product"))
            return instance.generate("PRO");
        else
            return instance.generate(null);
    }

    private String generate(String prefix)
    {
        // randomly generate a number for the numberic part
        // and convert that to a long from double eliminating decimals
        double numericpart = Math.random() * 1000000000000L;
        long numericpartinlong = (long) numericpart;
        // randomly generate a 6 digit number for the alphabetical part
        // and convert that to a long from double eliminating decimals
        double alphapart = Math.random() * 1000000;
        long alphapartinlong = (long) alphapart;
        // get a guranteed alphabetical of 3 chars
        String alphapartinstring = Long.toString(alphapartinlong);
        while (alphapartinstring.length() != 6)
        {
            alphapart = Math.random() * 1000000;
            alphapartinlong = (long) alphapart;
            alphapartinstring = Long.toString(alphapartinlong);
        }
        // get a guranteed numeric part of 12 digits
        String numericpartinstring = Long.toString(numericpartinlong);
        while (numericpartinstring.length() != 12)
        {
            numericpart = Math.random() * 1000000000000L;
            numericpartinlong = (long) numericpart;
            numericpartinstring = Long.toString(numericpartinlong);
        }
        numericpartinstring = prefix != null && prefix.length() != 0 ? prefix + numericpartinstring : numericpartinstring;
        return numericpartinstring;
    }

    public static void main(String asdf[])
    {
        String s1 = Utility.instance.getUniqueKey(Product.class);
        String s2 = Utility.instance.getUniqueKey(null);
        System.out.println(s1);
        System.out.println(s2);
    }
}
