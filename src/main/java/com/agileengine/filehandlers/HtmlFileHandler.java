package com.agileengine.filehandlers;

import com.agileengine.interfaces.filehandlers.IHtmlFileHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class HtmlFileHandler implements IHtmlFileHandler {

    private static String CHARSET_NAME = "utf8";

    private File htmlFile;

    private Document document;

    public HtmlFileHandler(File htmlFileName) throws IOException {
        this.htmlFile = htmlFileName;
        initialize();
    }

    private void initialize() throws IOException {
        document = Jsoup.parse(htmlFile, CHARSET_NAME, htmlFile.getAbsolutePath());
    }

    @Override
    public Optional<Element> findElementById(String elementId) {
        return Optional.of(document.getElementById(elementId));
    }

    @Override
    public Optional<Elements> findElementByCssQuery(String cssQuery) {
        return Optional.of(document.select(cssQuery));
    }
}
