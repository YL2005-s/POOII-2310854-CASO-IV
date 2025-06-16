package views.components;

import core.Observer;

import javax.swing.*;
import java.awt.*;

public class ForecastDisplay extends JPanel implements Observer {
    private final JLabel label;
    private float lastPressure = 1013.0f;

    public ForecastDisplay() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Pronóstico"));

        label = new JLabel("Pronóstico: Sin cambios");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
    }

    @Override
    public void update(float temp, float humidity, float pressure, float airQuality) {
        String pronostico = "Sin cambios";

        if (pressure > lastPressure) pronostico = "Mejorando el tiempo";
        else if (pressure < lastPressure) pronostico = "Empeorando el tiempo";

        label.setText("Pronóstico: " + pronostico);
        lastPressure = pressure;
    }
}