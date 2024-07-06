package com.example.workmannager.model.DTO;

import com.example.workmannager.model.Annotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")

public class TaskDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String category;
    private String priority;
    private String situation;
    private String description;
    private List<String> annotations;
    private Date limitDate;
    private Integer owner;
}
