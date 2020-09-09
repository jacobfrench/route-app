package com.project.resourceserver.controller;

import com.project.resourceserver.model.Schedule;
import com.project.resourceserver.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/public")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping(value="/schedule/add")
    public ResponseEntity<Schedule> createNewSchedule(@RequestBody Schedule schedule) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Schedule newSchedule = this.scheduleRepository.save(schedule);

        if(newSchedule != null)
            return new ResponseEntity<>(newSchedule, httpHeaders, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/schedule/get/id/{scheduleId}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable String scheduleId) {
        Schedule schedule = this.scheduleRepository.findById(scheduleId).get();
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(schedule, httpHeaders, HttpStatus.OK);
    }
    
}