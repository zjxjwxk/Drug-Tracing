package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xinkang Wu
 * @date 2020/8/12 10:30 下午
 */
@Api(tags = "药品监管部门接口")
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @ApiOperation("药品溯源（根据药品小包编号，获得药品溯源信息）")
    @GetMapping("/trace")
    @ResponseBody
    public ServerResponse trace(@ApiParam("药品小包编号") String packageID) {
        return null;
    }

    @ApiOperation("获得用户反馈")
    @GetMapping("/getFeedBack")
    @ResponseBody
    public ServerResponse getFeedBack() {
        return null;
    }
}
