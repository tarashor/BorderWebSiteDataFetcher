package com.tarashor.siteparser;

import com.tarashor.siteparser.models.BorderPassStatistic;
import com.tarashor.siteparser.models.DBConfig;
import com.tarashor.siteparser.parsers.Parser;
import com.tarashor.siteparser.parsers.SiteLODAMapParser;
import com.tarashor.siteparser.saver.DBSaver;
import com.tarashor.siteparser.saver.Saver;
import com.tarashor.siteparser.utils.PropertiesUtil;

/**
 * Created by Taras on 02.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        DBConfig config = PropertiesUtil.getConfig("projaws.properties");
        //System.out.println(config.getServer() + config.getUser() + config.getPassword());
        Saver saver = new DBSaver(config.getServer(), config.getUser(), config.getPassword());
        Parser parser = new SiteLODAMapParser();
        BorderPassStatistic statistic = parser.getStatistic();
        saver.save(statistic);
    }


}
