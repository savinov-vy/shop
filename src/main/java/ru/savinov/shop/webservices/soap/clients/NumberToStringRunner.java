package ru.savinov.shop.webservices.soap.clients;

import ru.savinov.shop.webservices.soap.clients.numberToString.NumberConversion;
import ru.savinov.shop.webservices.soap.clients.numberToString.NumberConversionSoapType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 *  wsimport -keep -p ru.savinov.shop.webservices.soap.clients.numberToString https://www.dataaccess.com/webservicesserver/NumberConversion.wso?WSDL
 */

public class NumberToStringRunner {
    public static void main(String[] args) throws MalformedURLException {

        final String endpoint = "https://www.dataaccess.com/webservicesserver/numberconversion.wso";

        final URL url = URI.create(endpoint).toURL();

        final NumberConversion service = new NumberConversion(url);

        final NumberConversionSoapType port = service.getPort(NumberConversionSoapType.class);

        System.out.println("$15.99 --> " + port.numberToDollars(BigDecimal.valueOf(15.99)));
        System.out.println("34  --> " + port.numberToWords(BigInteger.valueOf(34)));
    }
}