package com.agileengine.utils.searchcriteriasimpl;

import com.agileengine.interfaces.ISearchCriteria;
import com.agileengine.interfaces.filehandlers.IHtmlFileHandler;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchByIdCriteria implements ISearchCriteria {
    private IHtmlFileHandler fileHandler;
    private String criteriaValue;

    public SearchByIdCriteria(IHtmlFileHandler fileHandler, String criteria) {
        this.fileHandler = fileHandler;
        this.criteriaValue = criteria;
    }

    @Override
    public List<Optional<Element>> findElementsBy() {
        Optional<Element> element = fileHandler.findElementById(criteriaValue);
        List<Optional<Element>> list = new ArrayList<>();
        list.add(element);
        return list;
    }

    @Override
    public void destroy() {
        this.fileHandler = null;
    }
}
