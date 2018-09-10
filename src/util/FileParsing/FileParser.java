package util.FileParsing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    List<String> entriesList;
    private String fileName;
    private int fields;

    public FileParser(String fileName, int fileds) {
        this.fileName = fileName;
        this.fields = fileds;
    }

    public void processFileAndGenerateEntriesList(String fileName) {
        entriesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                String[] lineEntries = line.split("::");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getEntriesList() {
        return entriesList;
    }
}
