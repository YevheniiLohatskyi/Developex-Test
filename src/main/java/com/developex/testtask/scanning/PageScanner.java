package com.developex.testtask.scanning;

import com.developex.testtask.exception.UrlConnectFailedException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

@Data
@RequiredArgsConstructor
public class PageScanner {

    private static final String HREF = "href";
    private final String url;
    private Element htmlBody;

    public void downloadHtmlBody() throws UrlConnectFailedException {
        try {
            this.htmlBody = Jsoup.connect(url).get().body();
        } catch (Exception e) {
            throw new UrlConnectFailedException(url);
        }
    }

    public boolean searchForText(String text) {
        return htmlBody != null && htmlBody.text().contains(text);
    }

    public List<String> getNextUrls() {
        return htmlBody.getElementsByAttribute(HREF)
            .eachAttr(HREF)
            .stream()
            .filter(s -> s.startsWith("http"))
            .collect(Collectors.toList());
    }
}
