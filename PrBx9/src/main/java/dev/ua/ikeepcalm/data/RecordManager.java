package dev.ua.ikeepcalm.data;

import dev.ua.ikeepcalm.data.source.RecordWrapper;
import dev.ua.ikeepcalm.data.source.SortOrder;
import dev.ua.ikeepcalm.data.source.SortType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordManager{

    private static final RecordManager INSTANCE = new RecordManager();

    public boolean initializeRecords(File file) {
        RecordHolder.getInstance().setSortOrder(SortOrder.UNSORTED);
        RecordHolder.getInstance().setSortType(SortType.UNSORTED);
        RecordHolder.getInstance().setFilePath(file.getAbsolutePath());
        try {
            RecordHolder.getInstance().loadRecordsFromFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void wipeRecords() {
        RecordHolder.getInstance().setFilePath(null);
        RecordHolder.getInstance().setSortOrder(SortOrder.UNSORTED);
        RecordHolder.getInstance().setSortType(SortType.UNSORTED);
    }

    public boolean addRecord(RecordWrapper recordWrapper) {
        if (hasDuplicateName(recordWrapper.getName())) {
            return false;
        }
        List<RecordWrapper> loadedRecords;
        try {
            loadedRecords = RecordHolder.getInstance().getLoadedRecordWrappers();
            loadedRecords.add(recordWrapper);
        } catch (IOException e) {
            return false;
        }
        return RecordHolder.getInstance().saveRecordsToFile(loadedRecords);
    }

    public boolean deleteRecord(String regionName) {
        List<RecordWrapper> recordWrappers;
        try {
            recordWrappers = RecordHolder.getInstance().getLoadedRecordWrappers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (RecordWrapper recordWrapper : new ArrayList<>(recordWrappers)) {
            if (recordWrapper.getName().equals(regionName)) {
                recordWrappers.remove(recordWrapper);
                return RecordHolder.getInstance().saveRecordsToFile(recordWrappers);
            }
        } return false;
    }

    public boolean updateRecord(RecordWrapper updatedRecord) {
        String updatedRegionName = updatedRecord.getName();

        if (hasDuplicateName(updatedRegionName)) {
            List<RecordWrapper> recordWrappers;
            try {
                recordWrappers = RecordHolder.getInstance().getLoadedRecordWrappers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (RecordWrapper existingRecord : recordWrappers) {
                if (existingRecord.getName().equals(updatedRegionName)) {
                    existingRecord.setArea(updatedRecord.getArea());
                    existingRecord.setPopulation(updatedRecord.getPopulation());
                    return RecordHolder.getInstance().saveRecordsToFile(recordWrappers);
                }
            }
        } return false;
    }


    public void sortRecords(SortType field, SortOrder order) {
        List<RecordWrapper> loadedRecords;
        try {
            loadedRecords = RecordHolder.getInstance().getLoadedRecordWrappers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loadedRecords.sort((record1, record2) -> {
            int result = switch (field) {
                case BY_AREA -> Double.compare(record1.getArea(), record2.getArea());
                case BY_POPULATION -> Integer.compare(record1.getPopulation(), record2.getPopulation());
                default -> throw new IllegalStateException("Unexpected value: " + field);
            };
            if (order == SortOrder.ASCENDING) {
                return result;
            } else if (order == SortOrder.DESCENDING) {
                return -result;
            } else {
                throw new IllegalStateException("Unexpected value: " + order);
            }
        });

        RecordHolder.getInstance().setSortOrder(order);
        RecordHolder.getInstance().setSortType(field);
        RecordHolder.getInstance().saveRecordsToFile(loadedRecords);
    }

    public void insertRecord(RecordWrapper recordWrapper) {
        List<RecordWrapper> loadedRecords;
        try {
            loadedRecords = RecordHolder.getInstance().getLoadedRecordWrappers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SortType sortType = RecordHolder.getInstance().getSortType();
        SortOrder sortOrder = RecordHolder.getInstance().getSortOrder();
        int insertIndex = findInsertIndex(loadedRecords, recordWrapper, sortType, sortOrder);
        loadedRecords.add(insertIndex, recordWrapper);
        RecordHolder.getInstance().saveRecordsToFile(loadedRecords);
    }

    public boolean isSorted() {
        return RecordHolder.getInstance().getSortOrder() != SortOrder.UNSORTED
                && RecordHolder.getInstance().getSortType() != SortType.UNSORTED;
    }

    public String getRecordsAsString() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (RecordWrapper recordWrapper : RecordHolder.getInstance().getLoadedRecordWrappers()) {
                stringBuilder.append(recordWrapper.toPrettyString()).append("\n");
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
        return stringBuilder.toString();
    }

    private boolean hasDuplicateName(String name) {
        try {
            for (RecordWrapper recordWrapper : RecordHolder.getInstance().getLoadedRecordWrappers()) {
                if (recordWrapper.getName().equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    private int findInsertIndex(List<RecordWrapper> records, RecordWrapper newRecord, SortType sortType, SortOrder sortOrder) {
        int index = 0;

        for (RecordWrapper record : records) {
            int comparisonResult;

            if (sortType == SortType.BY_AREA) {
                comparisonResult = Double.compare(record.getArea(), newRecord.getArea());
            } else if (sortType == SortType.BY_POPULATION) {
                comparisonResult = Integer.compare(record.getPopulation(), newRecord.getPopulation());
            } else {
                throw new IllegalStateException("Unexpected value: " + sortType);
            }

            if ((sortOrder == SortOrder.ASCENDING && comparisonResult > 0) ||
                    (sortOrder == SortOrder.DESCENDING && comparisonResult < 0)) {
                break;
            }

            index++;
        }

        return index;
    }
    
    public static RecordManager getInstance() {
        return INSTANCE;
    }

}