package core;

public interface Observer {
    void update(float temp, float humidity, float pressure, float airQuality);
}
