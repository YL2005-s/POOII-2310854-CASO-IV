package views;

import controllers.HomeController;
import core.Observer;
import core.View;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel implements View {
    private final HomeController homeController;

    private JPanel rightPanel;
    private JPanel visualPanel;
    private JPanel displayArea;

    private JLabel lbl_alerts;

    private JButton btn_currentConditions;
    private JButton btn_statistics;
    private JButton btn_forecast;
    private JButton btn_airQuality;
    private JButton btn_update;

    private JSpinner spn_temperature;
    private JSpinner spn_humidity;
    private JSpinner spn_pressure;
    private JSpinner spn_airQuality;

    public HomeView(HomeController homeController) {
        this.homeController = homeController;

        make_frame();
        make_controlPanel();
        make_centerPanel();
        make_alertPanel();
    }

    private void make_frame() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void update(Object data) {
        if (data instanceof Observer && data instanceof JPanel) {
            displayArea.add((JPanel) data);
            displayArea.revalidate();
            displayArea.repaint();
        }
    }

    private void make_controlPanel() {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controles"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        SpinnerModel tempModel = new SpinnerNumberModel(20, -50, 50, 1);
        SpinnerModel humModel = new SpinnerNumberModel(65, 0, 100, 1);
        SpinnerModel pressModel = new SpinnerNumberModel(1013, 800, 1200, 1);
        SpinnerModel airModel = new SpinnerNumberModel(50, 0, 500, 1);

        Dimension spinnerSize = new Dimension(600, 25);

        // Temperatura
        gbc.gridx = 0; gbc.gridy = 0;
        controlPanel.add(new JLabel("Temperatura (°C):"), gbc);
        gbc.gridx = 1;
        spn_temperature = new JSpinner(tempModel);
        spn_temperature.setPreferredSize(spinnerSize);
        controlPanel.add(spn_temperature, gbc);

        // Humedad
        gbc.gridx = 0; gbc.gridy = 1;
        controlPanel.add(new JLabel("Humedad (%):"), gbc);
        gbc.gridx = 1;
        spn_humidity = new JSpinner(humModel);
        spn_humidity.setPreferredSize(spinnerSize);
        controlPanel.add(spn_humidity, gbc);

        // Presión
        gbc.gridx = 0; gbc.gridy = 2;
        controlPanel.add(new JLabel("Presión (hPa):"), gbc);
        gbc.gridx = 1;
        spn_pressure = new JSpinner(pressModel);
        spn_pressure.setPreferredSize(spinnerSize);
        controlPanel.add(spn_pressure, gbc);

        // Calidad de Aire
        gbc.gridx = 0; gbc.gridy = 3;
        controlPanel.add(new JLabel("Calidad Aire (AQI):"), gbc);
        gbc.gridx = 1;
        spn_airQuality = new JSpinner(airModel);
        spn_airQuality.setPreferredSize(spinnerSize);
        controlPanel.add(spn_airQuality, gbc);

        // Botón
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 50, 0, 25);
        btn_update = new JButton("Actualizar Datos");
        btn_update.setPreferredSize(new Dimension(140, 40));
        controlPanel.add(btn_update, gbc);

        add(controlPanel, BorderLayout.NORTH);
    }

    private void make_centerPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)), "Panel Central"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        make_visualPanel();
        make_rightPanel();

        rightPanel.setPreferredSize(new Dimension(220, 0));
        centerPanel.add(rightPanel, BorderLayout.EAST);

        visualPanel.setPreferredSize(new Dimension(500, 300));
        centerPanel.add(visualPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void make_rightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btn_currentConditions = new JButton("Agregar Condiciones Actuales");
        btn_statistics = new JButton("Agregar Estadísticas");
        btn_forecast = new JButton("Agregar Pronóstico");
        btn_airQuality = new JButton("Agregar Calidad Aire");

        Dimension btnSize = new Dimension(200, 35);
        for (JButton btn : new JButton[]{btn_currentConditions, btn_statistics, btn_forecast, btn_airQuality}) {
            btn.setMaximumSize(btnSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            rightPanel.add(btn);
            rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private void make_visualPanel() {
        visualPanel = new JPanel(new BorderLayout());
        visualPanel.setBorder(BorderFactory.createTitledBorder("Visualizaciones"));

        displayArea = new JPanel();
        displayArea.setLayout(new BoxLayout(displayArea, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        visualPanel.add(scrollPane, BorderLayout.CENTER);
    }


    private void make_alertPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Sistema de Alertas"));

        lbl_alerts = new JLabel("Sin alertas");
        lbl_alerts.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lbl_alerts, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getBtn_update() {
        return btn_update;
    }

    public JButton getBtn_currentConditions() {
        return btn_currentConditions;
    }

    public JButton getBtn_statistics() { return btn_statistics;
    }

    public JButton getBtn_forecast() {
        return btn_forecast;
    }

    public JButton getBtn_airQuality() {
        return btn_airQuality;
    }

    public JSpinner getSpn_temperature() {
        return spn_temperature;
    }

    public JSpinner getSpn_humidity() {
        return spn_humidity;
    }

    public JSpinner getSpn_pressure() {
        return spn_pressure;
    }

    public JSpinner getSpn_airQuality() {
        return spn_airQuality;
    }

    public JLabel getLbl_alerts() {
        return lbl_alerts;
    }
}
