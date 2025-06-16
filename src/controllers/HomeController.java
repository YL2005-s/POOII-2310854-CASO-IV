package controllers;

import core.Controller;
import models.WeatherData;
import org.apache.commons.mail.EmailException;
import utils.MailUtils;
import views.HomeView;
import views.components.*;

public class HomeController extends Controller {
    private final HomeView homeView = new HomeView(this);
    private final WeatherData weatherData = new WeatherData();
    private static final String APP_PASSWORD = "ffcldnlocvkehisn";
    private static final String SENDER_EMAIL = "jpcm0820@gmail.com";
    private static final String RECIPIENT_EMAIL = "jpcm0820@gmail.com";

    private final CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
    private final StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
    private final ForecastDisplay forecastDisplay = new ForecastDisplay();
    private final AQIDisplay aqiDisplay = new AQIDisplay();

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

            String issue = "Informe Final del Programa - Mediciones Meteorológicas";
            String body = String.format(
                    "Hola,\n\n" +
                            "Este es el informe final de la ejecución del programa con las últimas mediciones ingresadas:\n" +
                            "Temperatura: %.2f °C\n" +
                            "Humedad: %.2f %%\n" +
                            "Presión: %.2f hPa\n" +
                            "Calidad del Aire (AQI): %.0f\n\n" +
                            "Saludos.", temp, hum, press, air);

            try {
                MailUtils.sendEmail(SENDER_EMAIL, APP_PASSWORD, RECIPIENT_EMAIL, issue, body);
            } catch (EmailException ex) {
                throw new RuntimeException(ex);
            }
        });

        homeView.getBtn_currentConditions().addActionListener(e -> {
            weatherData.registerObserver(currentConditionsDisplay);
        });

        homeView.getBtn_statistics().addActionListener(e -> {
            weatherData.registerObserver(statisticsDisplay);
        });

        homeView.getBtn_forecast().addActionListener(e -> {
            weatherData.registerObserver(forecastDisplay);
        });

        homeView.getBtn_airQuality().addActionListener(e -> {
            weatherData.registerObserver(aqiDisplay);
        });
    }

}
