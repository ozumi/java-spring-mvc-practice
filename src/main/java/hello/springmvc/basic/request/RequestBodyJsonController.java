package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void RequestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody: {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("name: {}", helloData.getUsername());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String RequestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody: {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("name: {}", helloData.getUsername());

        return "ok";
    }

    //objectMapper 필요 없음.
    //httpMessageConvertor가 application/json을 보고 json포맷을 자동으로 변환 (JSON -> object)
    //@RequestBody는 생략할 수 없다. 생략하면 @ModelAttribute가 적용됨
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String RequestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("name: {}", helloData.getUsername());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String RequestBodyJsonV4(HttpEntity<HelloData> data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("name: {}", helloData.getUsername());
        return "ok";
    }

    //response에도 httpMessageConvertor가 적용된다 (object -> JSON)
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData RequestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {
        log.info("name: {}", helloData.getUsername());
        return helloData;
    }
}
