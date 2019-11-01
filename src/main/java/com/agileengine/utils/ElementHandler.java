package com.agileengine.utils;

import org.jsoup.nodes.Element;

import java.util.Optional;

public class ElementHandler {
    public static String elementPathToString(Element element) {
        Optional<Element> parent = Optional.of(element.parent());
        return elementToString(parent)+" > "+element.tagName();
    }

    private static String elementToString(Optional<Element> element) {
        String separator = " > ";
        if(!element.isPresent()) {
            return "";
        }
        Optional<Element> parent = Optional.ofNullable(element.get().parent());
        if(parent.isPresent() && !parent.get().tagName().contains("#root"))
            return elementToString(parent)+separator+element.get().tagName();
        else
            return element.get().tagName();
    }

}
