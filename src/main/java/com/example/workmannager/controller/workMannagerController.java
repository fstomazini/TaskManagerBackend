package com.example.workmannager.controller;

import com.example.workmannager.model.Annotation;
import com.example.workmannager.model.Category;
import com.example.workmannager.model.DTO.TaskDTO;
import com.example.workmannager.model.Task;
import com.example.workmannager.model.enums.Priority;
import com.example.workmannager.model.enums.Situation;
import com.example.workmannager.services.AnnotationService;
import com.example.workmannager.services.CategoryService;
import com.example.workmannager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/")
public class workMannagerController {
    @Autowired
    TaskService taskService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AnnotationService annotationService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("newtask")
    ResponseEntity<?> newTask(@RequestBody TaskDTO body)  {
        Task task = new Task();
        task.setTitle(body.getTitle());
        task.setOwner(body.getOwner());
        //task.setAnnotations(body.getAnnotations());
        List<Annotation> aux = new ArrayList<>();
        if(body.getAnnotations() != null){
            for(String element :body.getAnnotations()){
                Annotation annotation = new Annotation();
                annotation.setTaskId(Integer.valueOf(element.split(",")[0].split("-")[1]));
                annotation.setAnnotation(element.split(",")[1].split("-")[1]);
                System.out.println(annotation);
                aux.add(annotation);
            }
        }

        task.setAnnotations(aux);
        task.setCategory(body.getCategory());
        task.setLimitDate(body.getLimitDate());
        task.setDescription(body.getDescription());
        task.setPriority(Priority.valueOfString(body.getPriority()));
        task.setSituation(Situation.valueOfString(body.getSituation()));
        return taskService.newTask(task);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("findalltasks")
    ResponseEntity<?> findAll(){
        System.out.println("Entrou");
        return taskService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findtasksbysituation")
    ResponseEntity<?> findtaskbysituation(@RequestBody Map<String, String> body){
        String situation = body.get("situation");
        situation = situation.replace(" ", "_");
        situation = situation.toUpperCase();
        return taskService.findTasksBySituation(situation);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findtaskbyid")
    ResponseEntity<?> findTaskById(@RequestBody Map<String, String> body){
        Integer id = Integer.valueOf(body.get("id"));
        return taskService.findTaskById(id);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findtaskbycategory")
    ResponseEntity<?> findTasksByCategory(@RequestBody Map<String, String> body){
        String category = body.get("category");
        return taskService.findTasksByCategory(category);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findtaskbypriority")
    ResponseEntity<?> findTaskByPriority(@RequestBody Map<String, String> body){
        String priority = body.get("priority");
        priority = priority.replace(" ", "_");
        priority = priority.toUpperCase();
        return taskService.findTasksByPriority(priority);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findtasksbyowner")
    ResponseEntity<?> findTasksByOwner(@RequestBody Map<String, String> body){
        Integer owner = Integer.valueOf(body.get("owner"));
        return taskService.findTaskByOwner(owner);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("edittaskbyid")
    ResponseEntity<?> editTaskById(@RequestBody TaskDTO body){
        return taskService.edit(body);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("getcategorys")
    ResponseEntity<?> getAllCategorys(){
        return categoryService.getAllCategorys();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("deletecategory")
    ResponseEntity<?> deleteCategoryById(@RequestBody Map<String, String> body){
        Integer id = Integer.valueOf(body.get("id"));
        return categoryService.deleteCategoryById(id);
    }

    @CrossOrigin(origins = "http://localhost:8080/")
    @PostMapping("newcategory")
    ResponseEntity<?> newCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("newannotation")
    ResponseEntity<?> newAnnotation(@RequestBody Annotation annotation){
        return annotationService.newAnnotation(annotation);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("findannotationsbytaskid")
    ResponseEntity<?> findAnnotationsByTaskId(@RequestBody Map<String, String> body){
        Integer id = Integer.valueOf(body.get("id"));
        return annotationService.findAnnotationsByTaskId(id);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("deleteannotationbyid")
    ResponseEntity<?> deleteAnnotationById(@RequestBody Annotation annotation){
        return annotationService.deleteAnnotationById(annotation);
    }


}
