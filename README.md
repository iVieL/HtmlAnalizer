# Introduction


Project that search for modied html elements from original file and a modified file.

# Compile

`mvn clean compile pacakge`

# Usage

`java -cp target\html-attribute-locator-1.0-SNAPSHOT.jar com.agileengine.HtmlFileAnalyzerProcess ORIGIN_FILE TARGET_FILE [SEARCH_CRITERIA] [SEARCH_CRITERIA_VALUE]`

    ORIGIN_FILE: original html file with attribute to find
    TARGET_FILE: modified html file with modifications
    SEARCH_CRITERIA: can be 'id' or 'query', default value 'id'
    tSEARCH_CRITERIA_VALUE: can be any value, default value 'make-everything-ok-button'
    

