package wrappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class RawEntries implements Iterable {

    private final List<List<String>> rawList;


    public RawEntries() {
        rawList = new ArrayList<>();
    }

    @Override
    public Iterator iterator() {
        return this.rawList.iterator();
    }

    public void add(List<String> list) {
        this.rawList.add(list);
    }

    public List<List<String>> getListOfLists() {
        return this.rawList;
    }

    @Override
    public String toString() {
        return "RawEntries{" +
                "rawList=" + rawList +
                '}';
    }

    public static void main(String[] args) {
        RawEntries rawEntries = new RawEntries();
        List list = Arrays.asList(2, 3, 4, 2);
        rawEntries.add(list);
        List list2 = Arrays.asList(54, 23, 54, 21);
        rawEntries.add(list2);



    }
}
