package the.best;

import the.best.service.GetPlacePositionServiceImpl;
import the.best.web.data.LatLong;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;

public class Main {
    static public void main(String[] args) throws UnsupportedEncodingException {
        GetPlacePositionServiceImpl getPlacePositionService = new GetPlacePositionServiceImpl();
        LatLong latLong = getPlacePositionService.getPosition("ChIJQ3wCj8PI1EARtCRUwFbxYYQ");
        System.out.println(latLong.getLatitude() + " " + latLong.getLongitude());
    }


    static public void print(Locale locale) throws UnsupportedEncodingException {
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        String newString = new String(messages.getString("greeting").getBytes(), "UTF8");
        System.out.println(newString);

        Double currency = new Double(525600.10);
        Currency currentCurrency = Currency.getInstance(locale);
        NumberFormat currencyFormatter =
                NumberFormat.getCurrencyInstance(locale);
        System.out.println(
                currentCurrency.getDisplayName() + ": " +
                        currencyFormatter.format(currency));

        DateFormat dateFormat = DateFormat.getDateInstance(
                DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());
        System.out.println(date);
    }
}
