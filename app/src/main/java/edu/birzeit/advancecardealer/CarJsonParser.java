package edu.birzeit.advancecardealer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class CarJsonParser {

    public static List<Car> cars = new ArrayList<>();

    public static List<Car> getObjectFromJson(String json) {

        try {

            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Car car = new Car();
                car.setId(jsonObject.getInt("ID"));
                car.setFactoryName(jsonObject.getString("FACTORY_NAME"));
                car.setType(jsonObject.getString("TYPE"));
                car.setPrice(jsonObject.getLong("PRICE"));
                car.setModel(jsonObject.getString("MODEL"));
                car.setName(jsonObject.getString("NAME"));
                car.setOffer(jsonObject.getLong("OFFER"));
                car.setYear(jsonObject.getString("YEAR"));
                car.setFuelType(jsonObject.getString("FUEL_TYPE"));
                car.setRating(jsonObject.getLong("RATING"));
                car.setAccident(jsonObject.getString("ACCIDENT"));
                car.setDoorsCount(jsonObject.getInt("DOORS"));
                car.setHasAspare(jsonObject.getString("SPARE"));
                car.setColor(jsonObject.getString("COLOR"));
                car.setCompany(jsonObject.getString("COMPANY"));
                car.setImage(jsonObject.getString("IMAGE"));


                System.out.println("-----------------------------------"+jsonObject.getString("IMAGE"));
                cars.add(car);

            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
}
