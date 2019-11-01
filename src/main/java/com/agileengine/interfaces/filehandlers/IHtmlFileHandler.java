package com.agileengine.interfaces.filehandlers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public interface IHtmlFileHandler {
    Optional<Element> findElementById(String elementId);

    Optional<Elements> findElementByCssQuery(String cssQuery);
}
