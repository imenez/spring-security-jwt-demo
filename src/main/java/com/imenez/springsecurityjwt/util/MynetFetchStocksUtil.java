package com.imenez.springsecurityjwt.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MynetFetchStocksUtil {

    private static final String mynetStocksUrl = "https://finans.mynet.com/borsa/hisseler/";
    private static final String USER_AGENT_HEADER =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:56.0) Gecko/20100101 Firefox/56.0";
    private static final int TIMEOUT = 120000000;

    private List<String> stocksList = new ArrayList<>();



    public List<String> getStocks() {
        try {

            final Document document = Jsoup.connect(mynetStocksUrl).userAgent(USER_AGENT_HEADER).timeout(TIMEOUT).get();

            for (Element table : document.getElementsByClass("tbody-type-default")) {
               //System.out.println(table);
                for (Element row : table.select("tr")) {
                    //System.out.println(row);
                    Elements tds = row.select("td");
                    String stock = tds.text().substring(0, tds.text().indexOf(" "));

                    stocksList.add(stock);

                }
            }
            System.out.println(stocksList);

            return stocksList;


        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }

        return null;


    }



}
