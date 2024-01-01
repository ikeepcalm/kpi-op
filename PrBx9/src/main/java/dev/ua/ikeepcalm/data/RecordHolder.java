package dev.ua.ikeepcalm.data;

import dev.ua.ikeepcalm.data.source.RecordWrapper;
import dev.ua.ikeepcalm.data.source.SortOrder;
import dev.ua.ikeepcalm.data.source.SortType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordHolder {

    private static final RecordHolder INSTANCE = new RecordHolder();
    private String filePath;
    private SortOrder sortOrder;
    private SortType sortType;

    public List<RecordWrapper> getLoadedRecordWrappers() throws IOException {
        return loadRecordsFromFile();
    }

    public List<RecordWrapper> loadRecordsFromFile() throws IllegalArgumentException, IOException {
        List<RecordWrapper> loadedRecordWrappers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    RecordWrapper recordWrapper = RecordWrapper.fromString(line);
                    loadedRecordWrappers.add(recordWrapper);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException();
                }
            }
            return loadedRecordWrappers;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public boolean saveRecordsToFile(List<RecordWrapper> loadedRecordWrappers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (RecordWrapper recordWrapper : loadedRecordWrappers) {
                writer.write(recordWrapper.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isFileLoaded() {
        return filePath != null;
    }

    public static RecordHolder getInstance() {
        return INSTANCE;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
