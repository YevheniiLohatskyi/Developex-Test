package com.developex.testtask.controller;

import com.developex.testtask.model.Page;
import com.developex.testtask.scanning.SearchSession;
import com.developex.testtask.service.PageService;
import com.developex.testtask.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainController {

    private final SearchService searchService;
    private final PageService pageService;

    @GetMapping(path = "/")
    public String getSearchSessionForm(Model model) {
        pageService.deleteAll();
        model.addAttribute("searchSession", new SearchSession());
        return "search";
    }

    @PostMapping(path = "/search")
    public ModelAndView submitSearchSessionForm(@ModelAttribute SearchSession searchSession) {
        searchService.setSearchSession(searchSession);

        ModelAndView modelAndView = new ModelAndView("result");
        modelAndView.addObject("searchSession", searchSession);

        return modelAndView;
    }

    @RequestMapping(path = "/scan_pages")
    public void scanPages() {
        searchService.scanPages();
    }

    @GetMapping(path = "/url_status")
    public ModelAndView getUrlStatus() {
        ModelAndView modelAndView = new ModelAndView("url_status");

        List<Page> pages = pageService.findAll();
        modelAndView.addObject("pages", pages);

        return modelAndView;
    }
}
