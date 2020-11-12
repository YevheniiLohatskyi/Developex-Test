package com.developex.testtask;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageScanner {
    private static final String HREF = "href";
    private String url;

    public void getInnerText() {
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("Exception caught while reading from url: " + url);
            return;
        }

        String text = doc.body().text();

        if (freePlaces > 0) {
            doc.body()
                .getElementsByAttribute(HREF)
                .eachAttr(HREF)
                .stream()
                .filter(s -> !s.equals("#"))
                .filter(s -> !s.startsWith("/"))
                .limit(freePlaces)
                .forEachOrdered(u -> {
                    if (nextUrls.offer(u)){
                        urlCount++;
                    }
                });
        }

        return text.contains(searchSession.getSearchText());
    }

    private List<String>
}
