package com.agileengine;

import com.agileengine.exceptions.ElementNotFound;
import com.agileengine.exceptions.InvalidSearchCriteria;
import com.agileengine.filehandlers.HtmlFileHandler;
import com.agileengine.process.HtmlFileVerificatorProcess;
import com.agileengine.utils.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class HtmlFileAnalyzerProcess {
    private static Logger LOGGER = LoggerFactory.getLogger(HtmlFileAnalyzerProcess.class);

    public static void main(String[] args) throws IOException, InvalidSearchCriteria, ElementNotFound {
        if(args.length != 4 && args.length != 2) showUsage();
        String criteria = "id";
        String criteriaValue = "make-everything-ok-button";
        if(args.length == 4) {
            criteria = args[2];
            criteriaValue = args[3];
        }

        LOGGER.info(String.format("Starting validation with:\n\tOriginal File: %s\n\tTarget File: %s\n\tCriteria: %s\n\tCriteria Value: %s", args[0], args[1], criteria, criteriaValue));
        File originalFile = FileHandler.openFile(args[0]);
        File diffCaseFile = FileHandler.openFile(args[1]);

        HtmlFileVerificatorProcess verificationProcess = new HtmlFileVerificatorProcess(
                new HtmlFileHandler(originalFile),
                new HtmlFileHandler(diffCaseFile),
                criteria,
                criteriaValue);

        String result = verificationProcess.locateTargetElement();
        LOGGER.info(String.format("Element found at: %s", result));
    }

    private static void showUsage() {
        System.out.println("\nUsage: ");
        System.out.println("\njava com.agileengine.HtmlFileAnalyzerProcess ORIGIN_FILE TARGET_FILE [SEARCH_CRITERIA] [SEARCH_CRITERIA_VALUE]\n");
        System.out.println("\tORIGIN_FILE: original html file with attribute to find");
        System.out.println("\tTARGET_FILE: modified html file with modifications");
        System.out.println("\tSEARCH_CRITERIA: can be 'id' or 'query', default value 'id'");
        System.out.println("\tSEARCH_CRITERIA_VALUE: can be any value, default value 'make-everything-ok-button'");
        System.exit(1);
    }
}
