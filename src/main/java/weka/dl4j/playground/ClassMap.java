package weka.dl4j.playground;

import weka.classifiers.functions.dl4j.Utils;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.OptionMetadata;
import weka.core.WekaPackageManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassMap implements Serializable, OptionHandler {
    // Built-in class maps for WDL4J
    public enum BuiltInClassMap { IMAGENET, VGGFACE, CUSTOM }

    protected BuiltInClassMap builtInClassMap = BuiltInClassMap.IMAGENET;

    protected File classMapFile = new File(WekaPackageManager.getPackageHome().toURI());

    private String getClassMapPath() throws Exception {
        // Return the custom file path if the user has specified it

        String classMapFolder = "src/main/resources/class-maps";
        String classMapPath = null;
        switch (this.builtInClassMap) {
            case CUSTOM:
                if (this.classMapFile != null) {
                    classMapPath = this.classMapFile.getPath();
                    break;
                }
            case IMAGENET:
                classMapPath = Paths.get(classMapFolder, "IMAGENET.txt").toString();
                break;
            case VGGFACE:
                classMapPath = Paths.get(classMapFolder, "VGGFACE.txt").toString();
                break;
        }

        if (classMapPath == null) {
            throw new Exception("No class map file found, either specify a file " +
                    "using setClassMapFile(File) or use a built-in class map with " +
                    "setBuiltInClassMap(BuiltInClassMap)");
        }

        return classMapPath;
    }

    public String[] getClasses() throws Exception {
        // TODO add support for arff files
        List<String> classes = new ArrayList<>();
        try (FileReader fr = new FileReader(getClassMapPath())) {
            try (BufferedReader br = new BufferedReader(fr)) {
                // Create a class for each line in the file
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    line = line.trim();
                    // Ignore empty lines
                    if (line.length() == 0)
                        continue;

                    classes.add(line);
                }
            }
        }
        return classes.toArray(String[]::new);
    }

    @OptionMetadata(
            displayName = "Built in class map",
            description = "A predefined class map based on a specific dataset (IMAGENET, VGGFACE). " +
                    "Useful when using a pretrained zoo model as these are often trained on IMAGENET.",
            commandLineParamName = "builtIn",
            commandLineParamSynopsis = "-builtIn {IMAGENET, VGGFACE, CUSTOM}"
    )
    public BuiltInClassMap getBuiltInClassMap() {
        return builtInClassMap;
    }

    public void setBuiltInClassMap(BuiltInClassMap classMap) {
        this.builtInClassMap = classMap;
    }

    @OptionMetadata(
            displayName = "Class map file",
            description = "File containing mappings from class IDs to human-readable names - can be .txt or .arff",
            commandLineParamName = "classMapFile",
            commandLineParamSynopsis = "-classMapFile <file location>"
    )
    public File getClassMapFile() {
        return classMapFile;
    }

    public void setClassMapFile(File f) {
        this.classMapFile = f;

        if (Utils.notDefaultFileLocation(f))
            // We're using a custom file, not a built-in type
            this.builtInClassMap = BuiltInClassMap.CUSTOM;
    }

    /**
     * Returns an enumeration describing the available options.
     *
     * @return an enumeration of all the available options.
     */
    @Override
    public Enumeration<Option> listOptions() {
        return Option.listOptionsForClass(this.getClass()).elements();
    }

    /**
     * Gets the current settings of the Classifier.
     *
     * @return an array of strings suitable for passing to setOptions
     */
    @Override
    public String[] getOptions() {
        return Option.getOptions(this, this.getClass());
    }

    /**
     * Parses a given list of options.
     *
     * @param options the list of options as an array of strings
     * @throws Exception if an option is not supported
     */
    public void setOptions(String[] options) throws Exception {
        Option.setOptions(options, this, this.getClass());
    }
}
