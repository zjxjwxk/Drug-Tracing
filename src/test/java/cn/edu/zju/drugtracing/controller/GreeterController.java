package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.GreeterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xinkang Wu
 * @date 2020/8/14 9:43 下午
 */
@RestController
@RequestMapping("/greeter")
public class GreeterController {

    private final GreeterService greeterService;

    public GreeterController(GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @GetMapping("/greet")
    @ResponseBody
    public ServerResponse<String> greet() {
        return greeterService.greet();
    }

    @GetMapping("/newGreet")
    @ResponseBody
    public ServerResponse<String> newGreet(String newGreet) {
        return greeterService.newGreet(newGreet);
    }
}
