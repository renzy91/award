package com.renzy.award.controller;


import com.renzy.award.model.database.User;
import com.renzy.award.service.UserService;
import com.renzy.common.model.BaseResponse;
import com.renzy.common.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author renzhiyong
 * @since 2018-07-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public BaseResponse<String> test() {
        User user = new User();
        user.setId(1);
        user.setName("zhang3");
        user.setAge(123);

        userService.insert(user);

        return Utility.getRightResponse("ok");
    }

}
