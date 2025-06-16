package controllers;

import core.Controller;
import models.WeatherData;
import views.HomeView;
import views.components.*;

public class HomeController extends Controller {
    private final HomeView homeView = new HomeView(this);
    private final WeatherData weatherData = new WeatherData();

    @Override
    public void run() {
        weatherData.attach(homeView);
        weatherData.registerObserver(new AlertSystem(homeView.getLbl_alerts()));

        addView("HomeView", homeView);

        mainFrame.setVisible(true);

        setupViewButtons();
    }

    private void setupViewButtons() {
        homeView.getBtn_update().addActionListener(e -> {
            float temp = (float) (int) homeView.getSpn_temperature().getValue();
            float hum = (float) (int) homeView.getSpn_humidity().getValue();
            float press = (float) (int) homeView.getSpn_pressure().getValue();
            float air = (float) (int) homeView.getSpn_airQuality().getValue();
            weatherData.setMeasurements(temp, hum, press, air);
        });

        homeView.getBtn_currentConditions().addActionListener(e -> {
            weatherData.registerObserver(new CurrentConditionsDisplay());
        });

        homeView.getBtn_statistics().addActionListener(e -> {
            weatherData.registerObserver(new StatisticsDisplay());
        });

        homeView.getBtn_forecast().addActionListener(e -> {
            weatherData.registerObserver(new ForecastDisplay());
        });

        homeView.getBtn_airQuality().addActionListener(e -> {
            weatherData.registerObserver(new AQIDisplay());
        });
    }

}
