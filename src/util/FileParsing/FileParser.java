package util.FileParsing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    List<String> entriesList;
    private String fileName;
    private String splitToken;

    public FileParser(String fileName, String splitToken) {
        this.fileName = fileName;

        this.splitToken = splitToken;
    }

    public void processFile(String fileName) {
        entriesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                String[] lineEntries = line.split(splitToken);
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
