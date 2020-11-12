package com.developex.testtask.scanning;

import com.developex.testtask.exception.UrlConnectFailedException;
import com.developex.testtask.model.Page;
import com.developex.testtask.service.PageService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class PageScanManager {

    private static final String DOWNLOADING = "downloading";
    private static final String FOUND = "found";
    private static final String NOT_FOUND = "not found";
    private static final String ERROR = "error";

    private List<String> urlsToScan;
    private List<Boolean> scanned;
    private SearchSession searchSession;
    private final PageService pageService;

    public void scanPages() {
        urlsToScan = new CopyOnWriteArrayList<>();
        scanned = new CopyOnWriteArrayList<>();
        urlsToScan.add(searchSession.getStartUrl());

        Thread[] threads = new Thread[searchSession.getThreadsNumber()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                PageScanner scanner = null;

                while (scanned.size() < searchSession.getMaxUrls()) {
                    Page page = null;

                    synchronized (this) {
                        if (urlsToScan.size() > scanned.size()) {
                            String url = urlsToScan.get(scanned.size());
                            scanner = new PageScanner(url);
                            page = pageService.create(new Page().setUrl(url).setStatus(DOWNLOADING));
                            scanned.add(true);
                        }
                    }

                    if (page == null) {
                        continue;
                    }

                    try {
                        scanner.downloadHtmlBody();
                    } catch (UrlConnectFailedException e) {
                        page.setStatus(ERROR).setErrorMessage(e.getMessage());
                        pageService.update(page);
                        continue;
                    }

                    page.setStatus(scanner.searchForText(searchSession.getSearchText()) ? FOUND : NOT_FOUND);
                    pageService.update(page);

                    List<String> nextUrls = scanner.getNextUrls().stream()
                        .limit(searchSession.getMaxUrls() - scanned.size())
                        .collect(Collectors.toList());

                    for (String url : nextUrls) {
                        if (urlsToScan.size() < searchSession.getMaxUrls() && !urlsToScan.contains(url)) {
                            urlsToScan.add(url);
                        }
                    }

                    scanner = null;
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
