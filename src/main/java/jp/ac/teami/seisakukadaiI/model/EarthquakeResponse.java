package jp.ac.teami.seisakukadaiI.model;

import java.util.List;

public class EarthquakeResponse {

    private List<EarthquakeFeature> features;

    // features を返すメソッドを追加
    public List<EarthquakeFeature> getFeatures() {
        return features;
    }

    // features を設定するセッター
    public void setFeatures(List<EarthquakeFeature> features) {
        this.features = features;
    }

    // EarthquakeFeature クラスの定義
    public static class EarthquakeFeature {
        private EarthquakeProperties properties;

        public EarthquakeProperties getProperties() {
            return properties;
        }

        public void setProperties(EarthquakeProperties properties) {
            this.properties = properties;
        }
    }

    // EarthquakeProperties クラスの定義
    public static class EarthquakeProperties {
        private double mag;
        private String place;

        public double getMag() {
            return mag;
        }

        public void setMag(double mag) {
            this.mag = mag;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }
    }
}

