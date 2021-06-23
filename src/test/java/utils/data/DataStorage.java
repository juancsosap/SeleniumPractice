package utils.data;

import java.util.List;

public class DataStorage {

    private List<String> data;

    public DataStorage(List<String> data) {
        this.data = data;
    }

    public String get(int index, String defaultValue) {
        return !isEmpty() ? data.get(index) : defaultValue;
    }

    public String get(int index) {
        return get(index, "");
    }

    public boolean isEmpty() {
        return data == null || (data.size() == 0);
    }

    public int getInt(int index) {
        return Integer.parseInt(get(index));
    }

    public double getDouble(int index) {
        return Double.parseDouble(get(index));
    }

}
