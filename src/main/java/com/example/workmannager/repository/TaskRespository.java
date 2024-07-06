package com.example.workmannager.repository;

import com.example.workmannager.model.DTO.TaskDTO;
import com.example.workmannager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TaskRespository extends JpaRepository<TaskDTO, Long> {

    Collection<TaskDTO> findTaskByCategory(String category);
    Collection<TaskDTO> findTaskByPriority(String priority);
    Collection<TaskDTO>findTaskById(Integer id);
    Collection<TaskDTO> findTaskByOwner(Integer owner);
    Collection<TaskDTO> findTaskBySituation(String situation);



}
