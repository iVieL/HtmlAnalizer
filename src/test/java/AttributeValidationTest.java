import com.agileengine.exceptions.ElementNotFound;
import com.agileengine.exceptions.InvalidSearchCriteria;
import com.agileengine.filehandlers.HtmlFileHandler;
import com.agileengine.process.HtmlFileVerificatorProcess;
import com.agileengine.utils.FileHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AttributeValidationTest {
    private File originalFile;
    private final String CRITERIA = "id";
    private final String CRITERIA_VALUE = "make-everything-ok-button";

    @Before
    public void initAll() throws FileNotFoundException {
        String originalFileName = "resources/startbootstrap-sb-admin-2-examples/sample-0-origin.html";

        originalFile = FileHandler.openFile(originalFileName);
    }

    @Test
    public void TestFirstEscenario() throws IOException, ElementNotFound, InvalidSearchCriteria {
        String targetFileName = "resources/startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html";
        String expectedResult = "html > body > div > div > div > div > div > div > a";

        HtmlFileVerificatorProcess verificationProcess = new HtmlFileVerificatorProcess(
                new HtmlFileHandler(originalFile),
                new HtmlFileHandler(FileHandler.openFile(targetFileName)),
                CRITERIA,
                CRITERIA_VALUE);

        String result = verificationProcess.locateTargetElement();
        Assert.assertEquals(expectedResult, result);
    }
}
