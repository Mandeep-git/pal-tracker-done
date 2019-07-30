package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private Map <Long, TimeEntry> myMap;
    private long timeEntryId=0L;

    public InMemoryTimeEntryRepository() {
        this.myMap =  new HashMap<Long, TimeEntry>();
    }

    @Override
    public TimeEntry create(TimeEntry any) {

        TimeEntry te = new TimeEntry(++timeEntryId, any.getProjectId(), any.getUserId(), any.getDate(), any.getHours());
        myMap.put(te.getId(), te);
        return te;
    }

    public TimeEntry find(long timeEntryId) {

        return myMap.get(timeEntryId);
    }

    public void delete(long id) {
        myMap.remove(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<>();


        for (Map.Entry<Long,TimeEntry> entry : myMap.entrySet()){
            timeEntryList.add(entry.getValue());
        }
        return timeEntryList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        if(myMap.get(id)==null)
        {
            return null;
        }
        else {
            myMap.replace(id, timeEntry);
            return timeEntry;
        }
    }
}
