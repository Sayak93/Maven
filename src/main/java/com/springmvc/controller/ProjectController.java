package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.Entity.FacebookEntity;

import dao.FacebookDao;



@Controller
public class ProjectController {
	
	@RequestMapping("/register.htm")
	public ModelAndView registerUser(@RequestParam("name") String name,@RequestParam("pass") String pass,@RequestParam("em") String email,@RequestParam("adr") String adr, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		/*mv.addObject("nm",name);
		mv.addObject("pw",pass);
		mv.setViewName("result.jsp");*/
		HttpSession s=request.getSession();
		s.setAttribute("em", email);
		s.setAttribute("nm", name);
		FacebookEntity fu = new FacebookEntity();
		fu.setName(name);
		fu.setPassword(pass);
		fu.setEmail(email);
		fu.setAddress(adr);
		
		FacebookDao di = new FacebookDao();
		int i=0;
		try {
			i = di.createProfile(fu);
			if(i>0){
				mv.addObject("nm",s.getAttribute(name));
				mv.setViewName("Home.html");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping("/view.htm")
	public @ResponseBody 
	String viewprof( HttpServletRequest request){
		//ModelAndView mv = new ModelAndView();
		
		HttpSession s=request.getSession(true);
		String email=s.getAttribute("em").toString();
		FacebookEntity fu = new FacebookEntity();
		fu.setEmail(email);
		FacebookDao di = new FacebookDao();
		String l = di.viewProfile(fu);
		/*for(FacebookEntity fe:l) {
			mv.addObject("nm",fe.getName());
			mv.addObject("em",fe.getEmail());
			mv.addObject("pass",fe.getPassword());
			mv.addObject("adr",fe.getAddress());
			mv.setViewName("result.jsp");
		}*/
		return l;
	}
	@RequestMapping("/login.htm")
	public ModelAndView login(@RequestParam("email") String email,@RequestParam("pass") String pass,HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		//HttpSession s=request.getSession();
		//String nm = null;
		//nm =(String) s.getAttribute(nm);
		FacebookEntity fu = new FacebookEntity();
		fu.setPassword(pass);
		fu.setEmail(email);
		FacebookDao di = new FacebookDao();
		int i = di.login(fu, request);
		if(i==1) {
			//mv.addObject("nm",nm);
			mv.setViewName("Home.html");
		}
		else {
			mv.setViewName("login.html");
		}
		return mv;
	}

}
