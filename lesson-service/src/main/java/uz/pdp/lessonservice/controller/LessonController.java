package uz.pdp.lessonservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lessonservice.service.LessonService;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @GetMapping("/module/{id}")
    public HttpEntity<?> getLessonsByModuleId(
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "10") Integer size,
//            @RequestParam(defaultValue = "") String search,
            @PathVariable Long id){
        return ResponseEntity.ok(lessonService.getLessonsByModuleId(id));
    }
}
