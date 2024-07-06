package com.example.workmannager.services;

import com.example.workmannager.model.Annotation;
import com.example.workmannager.repository.AnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnnotationService {
    @Autowired
    AnnotationRepository annotationRepository;

    public ResponseEntity<HttpStatus> newAnnotation(Annotation annotation){
        return new ResponseEntity(annotationRepository.save(annotation), HttpStatus.CREATED);
    }
    public ResponseEntity<HttpStatus> findAnnotationsByTaskId(Integer id){
        return new ResponseEntity(annotationRepository.findAnnotationByTaskId(id), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> deleteAnnotationById(Annotation annotation){
        annotationRepository.delete(annotation);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> editAnnotationById(Annotation annotation){
        annotationRepository.save(annotation);
        return new ResponseEntity("edited", HttpStatus.OK);
    }
}
