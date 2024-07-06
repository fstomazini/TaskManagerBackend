package com.example.workmannager.services;

import com.example.workmannager.model.DTO.TaskDTO;
import com.example.workmannager.model.Task;
import com.example.workmannager.model.enums.Situation;
import com.example.workmannager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepo;



    public ResponseEntity<HttpStatus> newTask(Task body){
        taskRepo.saveTask(body);
            return new ResponseEntity("Sucess", HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> findTasksByCategory(String category){
        List<TaskDTO> tasks = taskRepo.findTasksByCategory(category).stream().toList();
        return new ResponseEntity(tasks, HttpStatus.OK );
    }
    public ResponseEntity<HttpStatus> findTasksBySituation(String situation){
        List<TaskDTO> tasks = taskRepo.findTaskBySituation(situation).stream().toList();
        return new ResponseEntity(tasks, HttpStatus.OK );
    }

    public ResponseEntity<HttpStatus> findTasksByPriority(String priority){

        List<TaskDTO> tasks = taskRepo.findTasksByPriority(priority).stream().toList();
        return new ResponseEntity(tasks, HttpStatus.OK );
    }

    public ResponseEntity<HttpStatus> findTaskByOwner(Integer owner){
        List<TaskDTO> tasks = taskRepo.findTaskByOwner(owner).stream().toList();
        return new ResponseEntity(tasks, HttpStatus.OK );
    }

    public ResponseEntity<HttpStatus> findTaskById(Integer id){
      Task tasks = taskRepo.findTaskById(id);
        return new ResponseEntity(tasks, HttpStatus.OK );
    }

    public ResponseEntity<HttpStatus> findAll(){
        List<TaskDTO> tasks = taskRepo.findAll();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> edit(TaskDTO task){
        Integer tasks = taskRepo.edit(task);
        if(tasks >= 1 ){
            return new ResponseEntity(tasks, HttpStatus.OK);
        }
        return new ResponseEntity(tasks, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
