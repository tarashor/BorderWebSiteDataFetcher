package com.tarashor.siteparser.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Taras on 02.02.2017.
 */
public class BorderPassStatistic {
    private List<BorderPassStatisticItem> statisticItems;
    private Date date;

    public BorderPassStatistic(){
        statisticItems = new ArrayList<BorderPassStatisticItem>();
        date = new Date();
    }

    public List<BorderPassStatisticItem> getStatisticItems() {
        return statisticItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
