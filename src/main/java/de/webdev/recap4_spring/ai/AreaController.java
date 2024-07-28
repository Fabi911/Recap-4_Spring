package de.webdev.recap4_spring.ai;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {

        private final OpenAiService openAiService;

        @PostMapping
        public String createTenToDos(@RequestBody String area) {
           return openAiService.createArea(area);
        }

}
