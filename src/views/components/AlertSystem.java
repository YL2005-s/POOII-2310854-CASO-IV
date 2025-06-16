package views.components;

import core.Observer;

import javax.swing.*;
import java.awt.*;

public class AlertSystem implements Observer {
    private final JLabel label;

    public AlertSystem(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(float temperature, float humidity, float pressure, float airQuality) {
        StringBuilder alerts = new StringBuilder();

        if (temperature > 35.0f) {
            alerts.append("⚠ Temperatura > 35°C. ");
        }
        if (humidity > 90.0f) {
            alerts.append("⚠ Humedad > 90%. ");
        }
        if (airQuality > 150.0f) {
            alerts.append("⚠ Calidad de aire mala (AQI > 150). ");
        }

        if (alerts.isEmpty()) {
            label.setText("Sin alertas");
            label.setForeground(Color.BLACK);
        } else {
            label.setText(alerts.toString());
            label.setForeground(Color.RED);
        }
    }
}