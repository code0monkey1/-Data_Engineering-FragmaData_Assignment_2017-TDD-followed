package util.FileParsing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private List<List<String>> rawEntriesList;
    private final String fileName;
    private final String parseToken;

    public FileParser(String fileName, String parseToken) {
        this.rawEntriesList = returnEntriesList(fileName, parseToken);
        this.fileName = fileName;
        this.parseToken = parseToken;
    }

    private List<List<String>> returnEntriesList(String fileName, String parseToken) {
        List<List<String>> tempRawEntriesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                String[] entry = line.split(parseToken);

                List<String> entryList = new ArrayList<>();

                for (String element : entry) {
                    entryList.add(element.trim());
                }
                tempRawEntriesList.add(entryList);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempRawEntriesList;
    }


    protected List<List<String>> getRawEntriesList() {
        return rawEntriesList;
    }
}
