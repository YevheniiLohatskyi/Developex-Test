package com.developex.testtask.service;

import com.developex.testtask.model.Page;
import com.developex.testtask.repository.PageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PageService {

    private final PageRepository repository;

    public Page create(Page page) {
        return repository.saveAndFlush(page);
    }

    public void update(Page page) {
        repository.saveAndFlush(page);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Page> findAll() {
        return repository.findAll();
    }
}
