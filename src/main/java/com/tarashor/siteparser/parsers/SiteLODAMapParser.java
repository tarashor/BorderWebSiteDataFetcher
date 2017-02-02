package com.tarashor.siteparser.parsers;

import com.tarashor.siteparser.models.BorderPassStatistic;
import com.tarashor.siteparser.models.BorderPassStatisticItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Taras on 02.02.2017.
 */
public class SiteLODAMapParser implements Parser {
    public static final String URL = "http://loda.gov.ua/karta_kordon/index.php";

    public BorderPassStatistic getStatistic() {
        BorderPassStatistic statistic = new BorderPassStatistic();
        try {
            Document doc = Jsoup.connect(URL).get();
            String dateAndHour = getDateAndHour(doc);
            statistic.setDate(getDateFromString(dateAndHour));
            Elements elements = doc.getElementsByClass("vn_ramka");
            for (Element element : elements) {
                BorderPassStatisticItem stat = getBorderPassStatFromElement(element);
                statistic.getStatisticItems().add(stat);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return statistic;
    }

    private BorderPassStatisticItem getBorderPassStatFromElement(Element element) {
        BorderPassStatisticItem borderPassStatisticItem = new BorderPassStatisticItem();
        borderPassStatisticItem.setName(getTitle(element));
        setCarsCount(borderPassStatisticItem, element);

        return borderPassStatisticItem;
    }

    private void setCarsCount(BorderPassStatisticItem stat, Element element) {
        if (element != null) {
            Elements rows = element.getElementsByTag("tr");
            if (rows != null && !rows.isEmpty()) {
                for (Element row : rows) {
                    if (isRowContainsCarsCountOnBorder(row)) {
                        stat.setCarsCountOnBorder(getCarsCountOnBorder(row));
                    }
                    if (isRowContainsCarsCountBeforeBorder(row)) {
                        stat.setCarsCountBeforeBorder(getCarsCountBeforeBorder(row));
                    }
                }
            }
        }
    }

    private int getCarsCountBeforeBorder(Element row) {
        int carsCountBeforeBorder = -1;
        if (row != null) {
            Elements numberElements = row.getElementsByClass("number");
            if (numberElements != null && !numberElements.isEmpty()) {
                Elements elementsByTag = numberElements.get(0).getElementsByTag("span");
                if (elementsByTag != null && !elementsByTag.isEmpty()) {
                    carsCountBeforeBorder = Integer.parseInt(elementsByTag.get(0).html());
                }
            }
        }
        return carsCountBeforeBorder;
    }

    private int getCarsCountOnBorder(Element row) {
        int carsCountBeforeBorder = -1;
        if (row != null) {
            Elements numberElements = row.getElementsByClass("number");
            if (numberElements != null && !numberElements.isEmpty()) {
                Elements elementsByTag = numberElements.get(0).getElementsByTag("span");
                if (elementsByTag != null && !elementsByTag.isEmpty()) {
                    carsCountBeforeBorder = Integer.parseInt(elementsByTag.get(0).html());
                }
            }
        }
        return carsCountBeforeBorder;
    }

    private boolean isRowContainsCarsCountBeforeBorder(Element row) {
        Elements cellsInRow = row.getElementsByTag("td");
        for (Element cell : cellsInRow) {
            String cellHtml = cell.html();
            if (cellHtml != null) {
                if (cellHtml.contains("перед пунктом пропуску")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRowContainsCarsCountOnBorder(Element row) {
        Elements cellsInRow = row.getElementsByTag("td");
        for (Element cell : cellsInRow) {
            String cellHtml = cell.html();
            if (cellHtml != null) {
                if (cellHtml.contains("у пункті пропуску")) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getTitle(Element element) {
        String title = "";
        if (element != null) {
            Elements titleElements = element.getElementsByClass("ramka_title");
            if (titleElements != null && !titleElements.isEmpty()) {
                title = titleElements.get(0).html();
            }
        }
        return title;
    }

    private String getDateAndHour(Document doc) {
        return doc.getElementById("godyna").html();
    }

    private Date getDateFromString(String text) {
        Date date = new Date();
        Matcher m = Pattern.compile("(0[1-9]|[1][0-9]|2[0-4]):([0-6][0-9])\\s(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d").matcher(text);
        if (m.find()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
            try {
                date = sdf.parse(m.group());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return date;
    }
}
