package com.developex.testtask.scanning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchSession {

    private String startUrl;
    private String searchText;
    private int threadsNumber;
    private int maxUrls;
}



