package com.example.yura.application;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class Handler extends DefaultHandler {
    private ArrayList<AllCurrency> listOfAllCurrencies=new ArrayList<>();
    private ArrayList<MyСurrency>  listOfMyСurrencies;
    private AllCurrency allCurrency;
    private String tag;
    private String bank="";
    private String currency;
    private String bay;
    private String sell;
    private boolean check=false;

    public ArrayList<AllCurrency> getListOfAllCurrencies() {
        return listOfAllCurrencies;
    }

    @Override
    public void startDocument() throws SAXException {
        Log.i("myLogs", "start parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        Log.i("myLogs", "end parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       tag=qName;
        if(tag.equals("title")){
            String [] str = attributes.getValue(0).split(" ");// in resource more that 1000 banks, more than 90% is the same(приватбанк ст, приватбанк ткр, приватбанк ткр)
            if(!bank.contains(str[0])) { // check if this bank is the same as a following
                bank = attributes.getValue(0);
                listOfMyСurrencies = new ArrayList<>();
                check=true;
            }
            else check=false;
        }
        else if(tag.equals("c")&&check){ //if check is true we get a currency of this bank, if check is false this bank is the same as previous
            currency= attributes.getValue(0);
            bay = attributes.getValue(1);
            sell = attributes.getValue(2);
            listOfMyСurrencies.add(new MyСurrency(currency,bay,sell));
        }
        if(tag.equals("organization")&&check){ //if it is the end of true bank we set all data in list
            allCurrency = new AllCurrency(bank, listOfMyСurrencies);
            listOfAllCurrencies.add(allCurrency);


        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {
    }
}
