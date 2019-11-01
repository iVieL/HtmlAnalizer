package com.agileengine.interfaces;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Optional;

public interface ISearchCriteria {
    List<Optional<Element>> findElementsBy();
    void destroy();
}
