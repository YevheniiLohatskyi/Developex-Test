package com.developex.testtask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "pages")
@Accessors(chain = true)
public class Page {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String url;

    @Column
    private String status;

    @Column(name = "error_message")
    private String errorMessage;
}
