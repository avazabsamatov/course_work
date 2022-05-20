package uz.pdp.lessonservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.lessonservice.entitiy.Lesson;
import uz.pdp.lessonservice.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getLessonsByModuleId(Long id) {
//        Pageable pageable = PageRequest.of(page,size);
        return lessonRepository.getLessonsByModuleId(id);
    }
}
