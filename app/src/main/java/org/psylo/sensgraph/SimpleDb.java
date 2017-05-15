package org.psylo.sensgraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple db which uses manually set unique IDs
 * Single id can have limited number of fields (numberOfFields var)
 * Created by psylo on 17.3.23.
 */

class SimpleDb {
    final static String TAG = "SimpleDb";
    List ids;
    private int numberOfFields;
    private int currEntryIdx;
    private List entries;
    private Entry currEntry;

    SimpleDb(int size) {
        this.ids = new ArrayList<>(); //widget id
        this.entries = new ArrayList<>(); //entries for widget
        this.numberOfFields = size; //
    }

    Boolean getCurrEntry(int id) {
        int idx = ids.indexOf(id);
        if (idx > -1) {
            currEntryIdx = idx;
            currEntry = (Entry) entries.get(idx);
            return true;
        } else {
            return false;
        }
    }

    Boolean createEntry(int id) {
        if (getCurrEntry(id)) {
            return false;
        }
        ids.add(id);
        entries.add(new Entry());
        getCurrEntry(id);
        return true;
    }

    Boolean deleteEntry(int id) {
        if (getCurrEntry(id)) {
            ids.remove(currEntryIdx);
            entries.remove(currEntryIdx);
            currEntryIdx = -1;
            return true;
        } else {
            return false;
        }
    }

    Boolean deleteCurrEntry() {
        try {
            ids.remove(currEntryIdx);
            entries.remove(currEntryIdx);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    Object getField(int fieldIdx) {
        if (fieldIdx < numberOfFields) {
            return currEntry.fields[fieldIdx];
        } else {
            return null;
        }
    }

    Boolean setField(int fieldIdx, Object valueToSet) {
        if (fieldIdx < numberOfFields) {
            currEntry.fields[fieldIdx] = valueToSet;
            return true;
        }
        return false;
    }

    class Entry {
        private Object[] fields;

        Entry() {
            this.fields = new Object[numberOfFields];
        }
    }
}
