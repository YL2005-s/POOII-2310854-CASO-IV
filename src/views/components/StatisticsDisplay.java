package views.components;

import core.Observer;

import javax.swing.*;
import java.awt.*;

public class StatisticsDisplay extends JPanel implements Observer {
    private final JTextArea area;

    private float sumaTemp = 0;
    private int lecturas = 0;
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;

    public StatisticsDisplay() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Estadísticas"));

        area = new JTextArea(2, 20);
        area.setEditable(false);
        area.setOpaque(false);
        add(area, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    }

    @Override
    public void update(float temp, float humidity, float pressure, float airQuality) {
        sumaTemp += temp;
        lecturas++;
        maxTemp = Math.max(maxTemp, temp);
        minTemp = Math.min(minTemp, temp);

        if (lecturas > 0) {
            float promedio = sumaTemp / lecturas;
            area.setText(String.format("Prom: %.1f°C Máx: %.1f°C Mín: %.1f°C", promedio, maxTemp, minTemp));
        } else {
            area.setText("No hay datos suficientes.");
        }
    }
}