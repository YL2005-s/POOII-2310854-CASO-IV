package views.components;

import core.DisplayElement;
import core.Observer;
import core.Subject;
import models.WeatherData;

import javax.swing.*;
import java.awt.*;

public class CurrentConditionsDisplay extends JPanel implements Observer {
    private final JTextArea area;

    public CurrentConditionsDisplay() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Condiciones Actuales"));

        area = new JTextArea(2, 20);
        area.setEditable(false);
        area.setOpaque(false);
        add(area, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    }

    @Override
    public void update(float temp, float humidity, float pressure, float airQuality) {
        area.setText(String.format("Temperatura: %.1f °C\nHumedad: %.1f %%", temp, humidity));
    }
}
