package com.agileengine.utils.factories;

import com.agileengine.exceptions.InvalidSearchCriteria;
import com.agileengine.interfaces.ISearchCriteria;
import com.agileengine.interfaces.filehandlers.IHtmlFileHandler;
import com.agileengine.utils.catalogs.SearchCriterias;
import com.agileengine.utils.searchcriteriasimpl.SearchByIdCriteria;
import com.agileengine.utils.searchcriteriasimpl.SearchByQueryCriteria;

import static com.agileengine.utils.catalogs.SearchCriterias.*;

public class SearchCriteriaBuilder {

    public static ISearchCriteria buildSearchCriteria(IHtmlFileHandler fileHandler, String criteria, String value) throws InvalidSearchCriteria {
        switch (identifyCriteria(criteria)) {
            case ID: return new SearchByIdCriteria(fileHandler, value);
            case CSS_QUERY: return new SearchByQueryCriteria(fileHandler, value);
            default: return null;
        }
    }

    private static SearchCriterias identifyCriteria(String criteria) throws InvalidSearchCriteria {
        switch (criteria.toLowerCase()) {
            case "id": return ID;
            case "query": return CSS_QUERY;
            default: throw new InvalidSearchCriteria("The search criteria "+criteria+" is not supported.");
        }
    }
}
