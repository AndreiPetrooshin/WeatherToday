package com.example.draqo.weathertoday;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by draqo on 16.05.2017.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyTask extends AsyncTask<Void, Void, Void> {
    private String todayNow;
    private String todayNigh;
    private String todayMorning;
    private String todayDay;

    private ImageView[] imageViews = new ImageView[]{MainActivity.imageStatusNow, MainActivity.imageStatusOne,
            MainActivity.imageStatusTwo, MainActivity.imageStatusThree};
    private ArrayList<String> listOrder = new ArrayList<>();
    private ArrayList<String> listWeatherStatus = new ArrayList<>();


    @Override
    protected Void doInBackground(Void... params) {

        Document doc = null;
        Elements elementsToday = null;//Здесь хранится будет разобранный html документ
        try {
            //подключение и высасывание инфы с сайта
            doc = Jsoup.connect("https://weather.rambler.ru/v-zhodino/").get();
            elementsToday = doc.getElementsByClass("weather-forecast weather-today__grid-forecast");
        } catch (IOException e) {
            //Если не получилось считать
            e.printStackTrace();
        }

        //Если всё считалось, то вытаскиваем из считанного html документа нужную нам инфу
        if (doc != null) {
            todayNow = "Сейчас: " + doc.getElementsByClass("weather-now__value").text() + "°С";
            if (elementsToday != null) {
                todayNigh = doc.getElementsByClass("weather-forecast__label").get(0).text() +
                        "\n" + elementsToday.get(0).getElementsByClass("weather-forecast__value").get(0).text() + "°С \n";
                todayMorning = doc.getElementsByClass("weather-forecast__label").get(1).text() +
                        "\n" + elementsToday.get(0).getElementsByClass("weather-forecast__value").get(1).text() + "°С \n";
                todayDay = doc.getElementsByClass("weather-forecast__label").get(2).text() +
                        "\n" + elementsToday.get(0).getElementsByClass("weather-forecast__value").get(2).text() + "°С \n";

                listOrder.add("Сейчас");
                listOrder.add(doc.getElementsByClass("weather-forecast__label").get(0).text());
                listOrder.add(doc.getElementsByClass("weather-forecast__label").get(1).text());
                listOrder.add(doc.getElementsByClass("weather-forecast__label").get(2).text());

                listWeatherStatus.add(doc.getElementsByClass("weather-now weather-today__grid-now").get(0).getElementsByTag("i")
                        .get(0).attr("title"));
                listWeatherStatus.add(elementsToday.get(0).getElementsByClass("weather-forecast weather-today__grid-forecast")
                        .get(0).getElementsByTag("i").get(0).attr("title"));
                listWeatherStatus.add(elementsToday.get(0).getElementsByClass("weather-forecast weather-today__grid-forecast")
                        .get(0).getElementsByTag("i").get(1).attr("title"));
                listWeatherStatus.add(elementsToday.get(0).getElementsByClass("weather-forecast weather-today__grid-forecast")
                        .get(0).getElementsByTag("i").get(2).attr("title"));
            } else {
                todayNigh = "-";
                todayMorning = "-";
                todayDay = "-";
            }
        } else {
            todayNow = "-";
        }


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        MainActivity.weather.append(" " + todayNow);
        MainActivity.weather1.setText(" " + todayNigh);
        MainActivity.weather2.setText(" " + todayMorning);
        MainActivity.weather3.setText(" " + todayDay);
        loadImages(listOrder, listWeatherStatus);


    }

    private void loadImages(ArrayList<String> order, ArrayList<String> weatherStatus) {

        if (order != null && weatherStatus != null){
            for (int i = 0; i < weatherStatus.size(); i++) {

                if (order.get(i).equals("Ночью")) {
                    switch (weatherStatus.get(i)) {
                        case "Ясно":
                            imageViews[i].setImageResource(R.drawable.suns);
                            break;
                        case "Переменная облачность":
                            imageViews[i].setImageResource(R.drawable.cloudlysunny);
                            break;
                        case "Облачно":
                            imageViews[i].setImageResource(R.drawable.cloudly);
                            break;
                        case "Дождь":
                            imageViews[i].setImageResource(R.drawable.rain);
                            break;
                        case "Снег":
                            imageViews[i].setImageResource(R.drawable.snow);
                            break;
                        case "Гроза":
                            imageViews[i].setImageResource(R.drawable.lightning);
                            break;
                        case "Слабый дождь":
                            imageViews[i].setImageResource(R.drawable.rainsunny);
                            break;
                        case "Временами дождь":
                            imageViews[i].setImageResource(R.drawable.rainsunny);
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (weatherStatus.get(i)) {
                        case "Ясно":
                            imageViews[i].setImageResource(R.drawable.suns);
                            break;
                        case "Переменная облачность":
                            imageViews[i].setImageResource(R.drawable.cloudlysunny);
                            break;
                        case "Облачно":
                            imageViews[i].setImageResource(R.drawable.cloudly);
                            break;
                        case "Дождь":
                            imageViews[i].setImageResource(R.drawable.rain);
                            break;
                        case "Снег":
                            imageViews[i].setImageResource(R.drawable.snow);
                            break;
                        case "Гроза":
                            imageViews[i].setImageResource(R.drawable.lightning);
                            break;
                        case "Слабый дождь":
                            imageViews[i].setImageResource(R.drawable.rainsunny);
                            break;
                        case "Временами дождь":
                            imageViews[i].setImageResource(R.drawable.rainsunny);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }
}
