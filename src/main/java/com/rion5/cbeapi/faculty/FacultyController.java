package com.rion5.cbeapi.faculty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacultyController {
    @Autowired
    private FacultyDao facultyDao;

    @GetMapping(value = "faculty")
    public ResponseEntity<List<Faculty>> getFacultyList(){
        return ResponseEntity.ok(facultyDao.getFacultyList());
    }

}
