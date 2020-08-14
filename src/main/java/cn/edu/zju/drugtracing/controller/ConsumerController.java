package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:27 下午
 */
@Api(tags = "消费者接口")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @ApiOperation("药品溯源（根据药品小包编号，获得药品溯源信息）")
    @PostMapping("/trace")
    @ResponseBody
    public ServerResponse trace(@ApiParam("药品小包编号") String packageID) {
        return null;
    }

    @ApiOperation("药品反馈（根据药品小包编号、消费者地址、反馈时间、反馈信息，对药品进行反馈）")
    @PostMapping("/feedBack")
    @ResponseBody
    public ServerResponse feedBack(@ApiParam("药品小包编号") String packageID,
                                   @ApiParam("消费者地址") String consumerAddr,
                                   @ApiParam("反馈时间") String time,
                                   @ApiParam("反馈信息") String information) {
        return null;
    }
}