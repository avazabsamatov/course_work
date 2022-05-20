package uz.pdp.moduleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.moduleservice.dto.ModuleDto;
import uz.pdp.moduleservice.entitiy.Modules;
import uz.pdp.moduleservice.repository.ModuleRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;
import java.util.Optional;


@Service
public class ModuleService {
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<Modules> findModuleByCourseId(Long id) {
//        PageRequest of = PageRequest.of(page, size);
        return moduleRepository.findByCourseId(id);
}

    @HystrixCommand(fallbackMethod = "moduleFallBackMethod")
    public ModuleDto getModuleWitLessons(Long moduleId) {
        Optional<Modules> moduleOptional = moduleRepository.findById(moduleId);
        if(moduleOptional.isPresent()){
            Modules module = moduleOptional.get();
            List moduleForObject = restTemplate.getForObject(
                    "http://localhost:9093/api/lesson/module/{moduleId}",
                    List.class,
                    module.getId()
            );
            ModuleDto moduleDto = new ModuleDto();
            moduleDto.setId(module.getId());
            moduleDto.setName(module.getName());
            moduleDto.setCourseId(module.getCourseId());
            moduleDto.setPrice(module.getPrice());
            moduleDto.setLessons(moduleForObject);
            return moduleDto;
        }
        return null;
    }
}
