package com.example.workmannager.repository;

import com.example.workmannager.model.Annotation;
import com.example.workmannager.model.DTO.TaskDTO;
import com.example.workmannager.model.Task;
import com.example.workmannager.model.enums.Priority;
import com.example.workmannager.model.enums.Situation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {

    @Autowired
    @Qualifier("H2")
    NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    TaskRespository taskRespo;
    @Autowired
    AnnotationRepository annotationRepo;


    public Integer saveTask(Task task){
        StringBuilder reqSql = new StringBuilder("");
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter
                .addValue("title", task.getTitle())
                .addValue("category", task.getCategory())
                .addValue("priority", task.getPriority().toString())
                .addValue("situation", task.getSituation().toString())
                .addValue("description",task.getDescription())
                .addValue("limitDate", task.getLimitDate())
                .addValue("owner", task.getOwner());
        reqSql.append(" INSERT INTO tasks(title, category, priority, situation, description, limit_date, owner )");
        reqSql.append(" VALUES (:title , :category , :priority, :situation, :description, :limitDate, :owner)" );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Integer rowsAfected  = jdbcTemplate.update(reqSql.toString(), namedParameter, keyHolder );
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id_task", keyHolder.getKey());
        String baseReq = "INSERT INTO ANNOTATIONS (id_task , annotation) values (:id_task , ";
        if(!task.getAnnotations().isEmpty()){
            for(Annotation annotation : task.getAnnotations()){
               annotationRepo.save(annotation);
            }
        }
        return rowsAfected;
    }
    public Integer edit(TaskDTO task){
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter
                .addValue("id", task.getId())
                .addValue("title", task.getTitle())
                .addValue("category", task.getCategory())
                .addValue("priority", task.getPriority())
                .addValue("situation", task.getSituation())
                .addValue("description",task.getDescription())
                .addValue("limitDate", task.getLimitDate())
                .addValue("owner", task.getOwner());
        String query = "update tasks set title = :title, category = :category, priority = :priority, situation = :situation, description = :description, limit_date = :limitDate, owner = :owner where id = :id";
        Integer rowsAfected = jdbcTemplate.update(query, namedParameter);
        return rowsAfected;
    }
    public List<TaskDTO> findTasksByCategory(String category){
        List<TaskDTO> tasksDTO = taskRespo.findTaskByCategory(category).stream().toList();
        return tasksDTO;
    }
    public List<TaskDTO> findTasksByPriority(String priority){
        List<TaskDTO> tasksDTO = taskRespo.findTaskByPriority(priority).stream().toList();
       // List<Task> tasks  =  populateAnnotations(tasksDTO);
        return tasksDTO;
    }
    public List<TaskDTO> findTaskByOwner(Integer owner){
        List<TaskDTO> tasksDTO = taskRespo.findTaskByOwner(owner).stream().toList();
        return tasksDTO;
    }
    public Task findTaskById(Integer id){
        List<TaskDTO> taskDTO = taskRespo.findTaskById(id).stream().toList();
        List<Task> task = populateAnnotations(taskDTO);
        return task.get(0);
    }
    public List<TaskDTO> findAll(){
        List<TaskDTO> tasksDTO = taskRespo.findAll().stream().toList();
        return tasksDTO;
    }
    public List<TaskDTO> findTaskBySituation(String situation){
        String query = "select * from tasks where situation = :situation";
        Map<String, String> argMap = new HashMap<>();
        argMap.put("situation", situation);
        List<TaskDTO> tasksDTO = taskRespo.findTaskBySituation(situation).stream().toList();
        return tasksDTO;
    }
    // funcao Auxiliar para popular os comentarios
    private List<Task> populateAnnotations(List<TaskDTO> tasksDTO){
        List<Task> response = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();


            for(TaskDTO taskDTO : tasksDTO){
                Task aux = new Task();
                aux.setId(taskDTO.getId());
                aux.setTitle(taskDTO.getTitle());
                aux.setDescription(taskDTO.getDescription());
                aux.setCategory(taskDTO.getCategory());
                aux.setPriority(Priority.valueOfString(taskDTO.getPriority()));

                aux.setSituation(Situation.valueOfString(taskDTO.getSituation()));
                aux.setLimitDate(taskDTO.getLimitDate());
                tasks.add(aux);
            }
            for (Task task : tasks){
                task.setAnnotations(annotationRepo.findAnnotationByTaskId(task.getId()).stream().toList());
                response.add(task);
            }


        return  response;
    }


}

