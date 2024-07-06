package com.example.workmannager.model;

import com.example.workmannager.model.enums.Priority;
import com.example.workmannager.model.enums.Situation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String category;
    private Priority priority;
    private Situation situation;
    private String description;
    private List<Annotation> annotations;
    private Date limitDate;
    private Integer owner;
}
