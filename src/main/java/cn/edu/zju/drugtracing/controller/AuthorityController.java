package cn.edu.zju.drugtracing.controller;

import cn.edu.zju.drugtracing.common.ServerResponse;
import cn.edu.zju.drugtracing.service.AuthorityService;
import cn.edu.zju.drugtracing.vo.TraceVO;
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

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @ApiOperation("药品溯源（根据药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID），获得药品溯源信息）")
    @GetMapping("/trace")
    @ResponseBody
    public ServerResponse<TraceVO> trace(@ApiParam("药品小包编号（3bytes药品ID+3bytes大包ID+3bytes小包ID）") @RequestParam String packageID) {
        return authorityService.trace(packageID);
    }
}
