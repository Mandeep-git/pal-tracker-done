package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }

    @DeleteMapping ("/time-entries/{timeEntryId}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("timeEntryId") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>("Hello World!", HttpStatus.NO_CONTENT);

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(  timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable final long timeEntryId) {

        TimeEntry te = timeEntryRepository.find(timeEntryId);

        if (te==null){
            return new ResponseEntity<>(te, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(te, HttpStatus.OK);
        }
    }


    @PutMapping("/time-entries/{timeEntryId}")
    @ResponseBody
    public ResponseEntity update(@PathVariable final long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry te = timeEntryRepository.update(timeEntryId, expected);

        if (te==null) {
            return new ResponseEntity<>(te, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(te, HttpStatus.OK);
         }
    }

    @GetMapping("/time-entries")
    @ResponseBody
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }
}
