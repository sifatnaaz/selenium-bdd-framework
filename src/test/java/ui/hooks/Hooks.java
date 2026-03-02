package ui.hooks;

import ui.context.TestDataContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private final TestDataContext context;

    public Hooks(TestDataContext context) {
        this.context = context;
    }

    @Before
    public void loadExcelConfigFromTags(Scenario scenario) {
        // defaults (optional)
        context.setDataFile(null);
        context.setSheetName(null);

        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@dataFile=")) {
                context.setDataFile(tag.substring("@dataFile=".length()));
            } else if (tag.startsWith("@sheet=")) {
                context.setSheetName(tag.substring("@sheet=".length()));
            }
        }

        if (context.getDataFile() == null || context.getSheetName() == null) {
            throw new RuntimeException("Missing @dataFile=... or @sheet=... tag in feature/scenario.");
        }
    }
}