package com.developex.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SearchSession {

    private String startUrl;
    private String searchText;
    private int threadsNumber;
    private int maxUrls;
}



