package com.lars.www.web;

import com.lars.www.response.Message;
import com.lars.www.service.QrtzJobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2019-07-30
 */
@RestController
public class QrtzJobDetailsController {

    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Message save() {
        return  qrtzJobDetailsService.saveVehicle(new Random().nextInt(1000)+1000);
    }

}
