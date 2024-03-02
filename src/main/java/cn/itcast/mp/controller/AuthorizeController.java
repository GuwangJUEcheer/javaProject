package cn.itcast.mp.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.mp.provider.AuthorizeProvider;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthorizeController {
//	    @Autowired
//	    private UserStrategyFactory userStrategyFactory;

	    @Autowired
	    private AuthorizeProvider authorizeProvider;

	    @Value("${github.client.id}")
	    private String clientId;

	    @Value("${github.client.secret}")
	    private String clientSecret;

	    @Value("${github.redirect.uri}")
	    private String redirectUri;

//	    @Autowired
//	    private UserService userService;

//	    @GetMapping("/callback/{type}")
//	    public String newCallback(@PathVariable(name = "type") String type,
//	                              @RequestParam(name = "code") String code,
//	                              @RequestParam(name = "state", required = false) String state,
//	                              HttpServletRequest request,
//	                              HttpServletResponse response) {
////	        UserStrategy userStrategy = userStrategyFactory.getStrategy(type);
////	        LoginUserInfo loginUserInfo = userStrategy.getUser(code, state);
//	        if (loginUserInfo != null && loginUserInfo.getId() != null) {
//	            User user = new User();
//	            String token = UUID.randomUUID().toString();
//	            user.setToken(token);
//	            user.setName(loginUserInfo.getName());
//	            user.setAccountId(String.valueOf(loginUserInfo.getId()));
//	            user.setType(type);
//	            user.setAvatarUrl(loginUserInfo.getAvatarUrl());
//	            userService.createOrUpdate(user);
//	            Cookie cookie = new Cookie("token", token);
//	            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
//	            cookie.setPath("/");
//	            response.addCookie(cookie);
//	            return "redirect:/";
//	        } else {
//	            log.error("callback get github error,{}", loginUserInfo);
//	            // 登录失败，重新登录
//	            return "redirect:/";
//	        }
//	    }

	    @GetMapping("/logout")
	    public String logout(HttpServletRequest request,
	                         HttpServletResponse response) {
	        request.getSession().invalidate();
	        Cookie cookie = new Cookie("token", null);
	        cookie.setMaxAge(0);
	        cookie.setPath("/");
	        response.addCookie(cookie);
	        return "redirect:/";
	    }
}
