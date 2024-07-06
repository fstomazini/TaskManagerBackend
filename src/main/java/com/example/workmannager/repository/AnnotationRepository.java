package com.example.workmannager.repository;

import com.example.workmannager.model.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
    Collection<Annotation> findAnnotationByTaskId(Integer taskId);

}
