package io.pivotal.pal.tracker;

import sun.awt.SunHints;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long,TimeEntry> storedMap = new HashMap<Long,TimeEntry>();
  public  long id=0L;
//  List<TimeEntry> timeList = new ArrayList<TimeEntry>();
    public TimeEntry create (TimeEntry entry)
    {
            long timeEntryId=++id;
            entry.setId(timeEntryId);
            storedMap.put(timeEntryId, entry);


          return entry;
    }
    public TimeEntry find(long id)
    {
        TimeEntry responseEntry=storedMap.get(id);
        return responseEntry;
    }
    public List<TimeEntry> list(){
        List<TimeEntry> timeEntryList=new ArrayList<TimeEntry>();
        for(Map.Entry<Long, TimeEntry> Smap:storedMap.entrySet())
        {
            timeEntryList.add(Smap.getValue());

        }
        return  timeEntryList;
    }

    public TimeEntry update (long id, TimeEntry timeEntry)
    {
        timeEntry.setId(id);
         storedMap.replace(id,timeEntry);
        return storedMap.get(id);
    }
    public void delete (long id)
    {
        TimeEntry entry=storedMap.get(id);
        if(entry!=null)
        {
            storedMap.remove(id);
        }
    }
}
