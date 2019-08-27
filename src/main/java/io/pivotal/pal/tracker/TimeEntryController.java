package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    public TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {


        return new ResponseEntity<>(timeEntryRepository.create(timeEntry),HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id")long id) {
        TimeEntry entry = timeEntryRepository.find(id);
        if(entry!=null) {
            return new ResponseEntity<>(entry,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update (@PathVariable("id") long id,@RequestBody TimeEntry timeEntry) {
        TimeEntry resp = timeEntryRepository.update(id,timeEntry);
        if(resp!=null){
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        }


    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable("id") long id)
    {

            timeEntryRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}