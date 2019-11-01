package com.agileengine.utils.searchcriteriasimpl;

import com.agileengine.interfaces.ISearchCriteria;
import com.agileengine.interfaces.filehandlers.IHtmlFileHandler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchByQueryCriteria implements ISearchCriteria {
    private IHtmlFileHandler fileHandler;
    private String query;

    public SearchByQueryCriteria(IHtmlFileHandler fileHandler, String query) {
        this.fileHandler = fileHandler;
        this.query = query;
    }

    @Override
    public List<Optional<Element>> findElementsBy() {
        List<Optional<Element>> list = new ArrayList<>();
        Optional<Elements> result = fileHandler.findElementByCssQuery(query);
        result.ifPresent(theResult -> {
            theResult.forEach(element -> {
                list.add(Optional.of(element));
            });
        });
        return list;
    }

    @Override
    public void destroy() {
        this.fileHandler = null;
    }

}
