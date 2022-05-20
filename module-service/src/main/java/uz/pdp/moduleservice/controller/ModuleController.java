package uz.pdp.moduleservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.moduleservice.service.ModuleService;

@RestController
@RequestMapping("/api/module")
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @GetMapping("/course/{id}")
    public HttpEntity<?> getModuleByCourseId(
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "10") Integer size,
//            @RequestParam(defaultValue = "") String search,
            @PathVariable Long id){
        return ResponseEntity.ok(moduleService.findModuleByCourseId(id));
    }
    @GetMapping("/module-with-lessons/{moduleId}")
    public HttpEntity<?> getModuleWithLesson(@PathVariable Long moduleId){
        return ResponseEntity.ok(moduleService.getModuleWitLessons(moduleId));
    }
}
