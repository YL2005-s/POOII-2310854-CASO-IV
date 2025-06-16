package models;

import core.Observer;
import core.Subject;
import core.View;
import views.HomeView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private View view;

    private float temperature;
    private float humidity;
    private float pressure;
    private float airQuality;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);

        view.update(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure, airQuality);
        }
    }

    public void setMeasurements(float t, float h, float p, float aq) {
        this.temperature = t;
        this.humidity = h;
        this.pressure = p;
        this.airQuality = aq;
        notifyObservers();
    }
}