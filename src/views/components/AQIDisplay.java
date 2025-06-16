package views.components;

import core.Observer;

import javax.swing.*;
import java.awt.*;

public class AQIDisplay extends JPanel implements Observer {
    private final JLabel label;

    public AQIDisplay() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Calidad del Aire"));

        label = new JLabel("AQI: -");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
    }

    @Override
    public void update(float temp, float humidity, float pressure, float airQuality) {
        String estado;
        Color color;

        if (airQuality <= 50) {
            estado = "Buena";
            color = new Color(0x00A000);
        } else if (airQuality <= 100) {
            estado = "Moderada";
            color = Color.YELLOW;
        } else if (airQuality <= 150) {
            estado = "Poco saludable";
            color = Color.ORANGE;
        } else if (airQuality <= 200) {
            estado = "Insalubre";
            color = Color.RED;
        } else if (airQuality <= 300) {
            estado = "Muy insalubre";
            color = new Color(128, 0, 128);
        } else {
            estado = "Peligrosa";
            color = new Color(139, 0, 0);
        }

        label.setText(String.format("AQI: %.0f (%s)", airQuality, estado));
        label.setForeground(color);
    }
}