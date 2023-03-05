package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "juyoung";
        log.trace("name is {}", name);
        log.debug("name is {}", name);
        log.info("name is {}", name);
        log.warn("name is {}", name);
        log.error("name is {}", name);
        return "ok";
    }
}
