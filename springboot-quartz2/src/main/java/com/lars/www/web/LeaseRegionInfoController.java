package com.lars.www.web;

import com.lars.www.entity.LeaseRegionInfo;
import com.lars.www.service.LeaseRegionInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2019-07-26
 */
@RestController
public class LeaseRegionInfoController {

    @Autowired
    private LeaseRegionInfoServiceImpl leaseRegionInfoService;

    @RequestMapping(value = "/save")
    public Integer save() throws Exception {
        return leaseRegionInfoService.saveRegion(new LeaseRegionInfo().setCaseId(1).setGmtCreate(new Date())
                .setOperatorId(1).setOperatorId(23).setRegionCountry("CN").setRegionName("12312312"));
    }

}
