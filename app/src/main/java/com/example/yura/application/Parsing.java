package com.example.yura.application;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Parsing {
    private static ArrayList<AllCurrency> finalList;
// this list is ready to use
    public synchronized static ArrayList<AllCurrency> getFinalList() {
        return finalList;
    }

    public void parser (String URL) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            URL url = new URL(URL);
            InputStream stream = url.openStream();
            SAXParser saxParser = saxParserFactory.newSAXParser(); // creating a SAX parser
            Handler handler = new Handler();
            saxParser.parse(stream,handler); // here we parse our resource
            finalList = handler.getListOfAllCurrencies();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
