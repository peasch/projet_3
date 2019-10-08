package main.java.com.ocr.pierrealainpolymorph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

    public ReadPropertyFile() throws IOException {

        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("C:/Users/peasc/IdeaProjects/escape-polymorph/src/main/resources/config.properties");
        prop.load(ip);

    }
}
