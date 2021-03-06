package com.example.bee2.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bee2.entity.Post;
import com.example.bee2.entity.User;
import com.example.bee2.form.PostForm;
import com.example.bee2.service.MessageService;
import com.example.bee2.service.PostService;
import com.example.bee2.service.UserService;
import com.example.bee2.utility.RequestUtility;
import com.example.bee2.utility.UserUtility;

@Controller
public class TopController {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserUtility userUtility;
	@Autowired
	private RequestUtility requestUtility;
	
	@RequestMapping(value="/bee/top", method=RequestMethod.GET)
	public String topPage(@RequestHeader(value="User-Agent") String userAgent, Model model, Principal principal) {
	  User user = userUtility.pickupUser(principal);
	  List<Post> sortedPostList = getSortedPostList();
	  
	  model.addAttribute("user", user);
	  model.addAttribute("isAdmin", user.isAdmin());
	  model.addAttribute("postList", sortedPostList);
	  model.addAttribute("postForm", new PostForm());
	  model.addAttribute("messageList", messageService.findByReciever(user.getName()));
	  model.addAttribute("maybeFriends", userService.getMaybeFriends(user.getName()));
		
	  return requestUtility.isMobile(userAgent) ? "top-mobile" : "top";
	}
	
	private List<Post> getSortedPostList() {
		List<Post> postList = (List<Post>) postService.findAll();
		Collections.sort(postList, (p1, p2) -> p2.getDate().compareTo(p1.getDate()));
		return postList;
	}
}
