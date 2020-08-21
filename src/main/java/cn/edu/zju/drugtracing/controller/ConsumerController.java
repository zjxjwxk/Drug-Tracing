package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.ConsumerService;
import cn.edu.zju.drugtracing.vo.TraceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:27 下午
 */
@Api(tags = "消费者接口")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @ApiOperation("上传消费者信息（根据消费者性别、年龄，上传消费者信息）")
    @PostMapping("/setConsumer")
    @ResponseBody
    public ServerResponse<String> setConsumer(@ApiParam(value = "消费者性别", example = "0") @RequestParam Integer gender,
                                                  @ApiParam(value = "消费者年龄", example = "0") @RequestParam Integer age) {
        return consumerService.setConsumer(gender, age);
    }

    @ApiOperation("药品溯源（根据药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID），获得药品溯源信息）")
    @GetMapping("/trace")
    @ResponseBody
    public ServerResponse<TraceVO> trace(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID) {
        return consumerService.trace(packageID);
    }

    @ApiOperation("药品反馈（根据药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）、反馈时间（输入0则为当前时间）、反馈信息，对药品进行反馈）")
    @PostMapping("/feedBack")
    @ResponseBody
    public ServerResponse<String> feedBack(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID,
                                   @ApiParam(value = "反馈时间（输入0则为当前时间）", example ="0") @RequestParam Integer time,
                                   @ApiParam("反馈信息") @RequestParam String information) {
        return consumerService.feedBack(packageID, time, information);
    }
}
