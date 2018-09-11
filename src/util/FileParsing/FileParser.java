package util.FileParsing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    List<List<String>> entriesList;
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

                List<String> entry = Arrays.asList(line.split(splitToken));
                entriesList.add(entry);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<List<String>> getEntriesList() {
        return entriesList;
    }
}
