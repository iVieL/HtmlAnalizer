package com.agileengine.process;

import com.agileengine.exceptions.ElementNotFound;
import com.agileengine.exceptions.InvalidSearchCriteria;
import com.agileengine.interfaces.ISearchCriteria;
import com.agileengine.interfaces.filehandlers.IHtmlFileHandler;
import com.agileengine.utils.ElementHandler;
import com.agileengine.utils.factories.SearchCriteriaBuilder;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HtmlFileVerificatorProcess implements Closeable {
    private IHtmlFileHandler diffCase;
    private ISearchCriteria searchCriteriaImpl;

    public HtmlFileVerificatorProcess(IHtmlFileHandler original, IHtmlFileHandler diffCase, String searchCriteria, String searchCriteriaValue) throws InvalidSearchCriteria {
        this.diffCase = diffCase;
        searchCriteriaImpl = SearchCriteriaBuilder.buildSearchCriteria(original, searchCriteria, searchCriteriaValue);
    }

    public String locateTargetElement() throws ElementNotFound {
        List<Optional<Element>> result = searchCriteriaImpl.findElementsBy();
        if(result.isEmpty()) throw new ElementNotFound("Element not found into original file.");

        Optional<Element> elementOptional = result.get(0);
        if(!elementOptional.isPresent()) throw new ElementNotFound("The element doesn't exist.");

        Element element = elementOptional.get();

        return locateElement(element);
    }

    private String locateElement(Element element) throws ElementNotFound {
        String tagName = element.tagName();

        Attributes attributes = element.attributes();
        List<Attribute> attributeList = attributes.asList();
        String criteria;
        ISearchCriteria searchByQuery = null;
        List<Optional<Element>> result;
        Optional<Element> elementFound = Optional.empty();

        for (Attribute attribute: attributeList) {
            try {
                criteria = buildCriteria(tagName, attribute);
                searchByQuery = SearchCriteriaBuilder.buildSearchCriteria(diffCase, "query", criteria);
                result = searchByQuery.findElementsBy();
                elementFound = findElementUsingOtherAttributes(attribute, result, attributeList);
                if(elementFound.isPresent()) {
                    break;
                }
            } catch (InvalidSearchCriteria e) {
                //doesn't matter exception at this point.
            }
            finally {
                if(searchByQuery != null) searchByQuery.destroy();
            }
        }

        if(elementFound.isPresent()) {
            return ElementHandler.elementPathToString(element);
        }
        throw new ElementNotFound("Any element was found on target file");
    }

    private Optional<Element> findElementUsingOtherAttributes(Attribute currentAttribute, List<Optional<Element>> elements, List<Attribute> originalAttributeList) {
        if(elements.isEmpty()) return Optional.empty();

        List<Element> list = elements.stream().filter(element -> {
            if(!element.isPresent()) return false;
            for(Attribute attr: element.get().attributes()) {
                if(!attr.getKey().equals(currentAttribute.getKey())) {
                    for(Attribute originalAttr: originalAttributeList) {
                        if(originalAttr.getKey().equals(attr.getKey()) && originalAttr.getValue().equals(attr.getValue())) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }).map(Optional::get).collect(Collectors.toCollection(ArrayList::new));
        if(list.isEmpty()) return Optional.empty();
        return Optional.of(list.get(0));
    }

    private String buildCriteria(String tagName, Attribute attribute) {
        return tagName+"["+attribute.getKey()+"=\""+attribute.getValue()+"\"]";
    }


    @Override
    public void close() {
        searchCriteriaImpl.destroy();
    }
}
