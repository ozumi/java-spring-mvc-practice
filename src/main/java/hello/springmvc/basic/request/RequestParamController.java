package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void RequestParamV1(HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    //@RequestBody
    @RequestMapping("/request-param-v2")
    public String RequestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //변수 명이 같으면 생략 가능
    @RequestMapping("/request-param-v3")
    public String RequestParamV3(@RequestParam String username,
                                 @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //단순 타입은 @RequestParam도 생략 가능
    @RequestMapping("/request-param-v4")
    public String RequestParamV4(String username,
                                 int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //빈 문자는 null로 처리된다
    @RequestMapping("/request-param-required")
    public String RequestParamRequired(@RequestParam(required = true) String username,
                                 @RequestParam(required = false) Integer age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //defaultValue를 지정하면 required=false 설정한것과 같다
    //빈 문자는 값이 안 들어온 것으로 간주한다
    @RequestMapping("/request-param-default")
    public String RequestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //만약 한 파라미터 키의 값이 여러개라면 map 대신 multiValueMap을 사용하자
    @RequestMapping("/request-param-map")
    public String RequestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("helloData = {}", helloData);
        return "ok";
    }

    //어노테이션 생략 가능 (단순타입 생략시에는 @RequestParam 어노테이션을 사용)
    //나머지는 @ModelAttribute 어노테이션이 생략되었다고 간주
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("helloData = {}", helloData);
        return "ok";
    }
}
