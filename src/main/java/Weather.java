import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    // 0aa0307d0b9da26493c701a237bba9d1
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message +"&units=metric&appid=e6c1297555a3f16499f1fa3de589b8d8");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray array = object.getJSONArray("weather");
        for(int i = 0;i<array.length();i++) {
            JSONObject weather = array.getJSONObject(i);
            model.setIcon(weather.getString("icon"));
            model.setMain(weather.getString("main"));
        }

        return "City: " + model.getName() +
                "\nTemperature: " + model.getTemperature() +
                "\nHumidity: " + model.getHumidity() + "%" +
                "\nDescription: " + model.getMain() + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
