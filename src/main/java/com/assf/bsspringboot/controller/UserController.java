package com.assf.bsspringboot.controller;

import com.assf.bsspringboot.dao.UserRepository;
import com.assf.bsspringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value="/login")
    public String login() throws Exception{
        return "login";
    }

	@RequestMapping(value="/loginUser", method= RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
		User user = userRepository.findByNameAndPassword(request.getParameter("name"), request.getParameter("password"));
		if (user == null){
			request.setAttribute("message", "账户或密码错误！");
            modelAndView.setViewName("login");
		}else{
            modelAndView.addObject("user", user);
            switch (user.getRole()) {
                case "0":
                    modelAndView.setViewName("shiro/admin");
                    break;
                case "1":
                    modelAndView.setViewName("shiro/user");
                    break;
            }
		}
        return modelAndView;
	}
}
