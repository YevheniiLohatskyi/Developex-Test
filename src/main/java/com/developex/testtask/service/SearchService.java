package com.developex.testtask.service;

import com.developex.testtask.scanning.PageScanManager;
import com.developex.testtask.scanning.SearchSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchService {

    private final PageScanManager scanManager;
    private SearchSession searchSession;

    public void scanPages() {
        scanManager.setSearchSession(searchSession);
        scanManager.scanPages();
    }
}
