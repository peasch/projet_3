package main.java.com.ocr.pierrealainpolymorph;

        import java.io.FileInputStream;
        import java.io.IOException;
        import java.util.Properties;

public class ReadPropertyFile {
    private static  Properties prop=null;

    private ReadPropertyFile() {
    }

    public static Properties extractProperties() throws IOException {

        if (prop == null) {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C:/Users/peasc/IdeaProjects/escape-polymorph/src/main/resources/config.properties");
            prop.load(ip);
        }

        return prop;
    }
}
