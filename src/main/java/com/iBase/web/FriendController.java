package com.iBase.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iBase.domain.Friend;
import com.iBase.domain.UserInfo;
import com.iBase.service.db.UserInfoDAO;
import com.iBase.web.sharing.FriendGraph;

@Controller
public class FriendController {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	private FriendGraph friendGraph;
	
	public FriendController(){
		friendGraph = new FriendGraph();
	}
	 
	@RequestMapping(value="/friends", method = RequestMethod.GET)
	public String handleFriendsRequest(Model model){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		
			Friend friend = new Friend();
			model.addAttribute("friend", friend);
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			String userId = userDetail.getUsername();
			log.info("Returning friends to "+ userId);
			
			UserInfo user = userInfoDAO.findById(userId);
	        model.addAttribute("fName", user.getFirstName());
	        model.addAttribute("lName", user.getLastName());
			
	        //getFriends
	        List<Friend> friendObjects = getFriends(user);
			
			if(friendObjects.size()!=0){
				log.info(friendObjects);
				model.addAttribute("friendObjects", friendObjects);
			}else{
				model.addAttribute("friendsMessage", "Oops! you have no friends yet!");
			}
			return "friends";
		}
		return "403";
	}


	private List<Friend> getFriends(UserInfo user) {
		
		List<Friend> friends = new ArrayList<Friend>();
		List<String> friendIds = friendGraph.getAllFriends(user.getUserId());
		if(friendIds.size()!=0){
			for(String id: friendIds){
				UserInfo userInfo = userInfoDAO.findById(id);
				if(userInfo!=null){
					Friend friend = new Friend();
					friend.setUserId(id);
					friend.setEmail(id);
					friend.setfName(userInfo.getFirstName());
					friend.setlName(userInfo.getLastName());
					friends.add(friend);
				}
			}
		}
		return friends;
	}
	
	@RequestMapping(value="/friends", method = RequestMethod.POST)
	public String addFriend(@ModelAttribute(value="friend") Friend friend
			, Model model, BindingResult result){
		
		String userName = null;

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addAttribute("userName", userDetail.getUsername());
			userName = userDetail.getUsername();
			UserInfo user = userInfoDAO.findById(userName);
	        model.addAttribute("fName", user.getFirstName());
	        model.addAttribute("lName", user.getLastName());
		}
		if(!result.hasErrors()){
			
			String newFriendId = friend.getUserId();
			log.info("user:"+userName+"::friend:"+newFriendId);
			if(newFriendId.equals(userName)){
				model.addAttribute("friendError", "Hey! You can't add yourself!");
				return handleFriendsRequest(model);
			}
			int ret = friendGraph.addAsFriends(userName, newFriendId);
			switch(ret){
				case 1:
					model.addAttribute("friendError", "User does not exist!");
					return handleFriendsRequest(model);
				case 2:
					model.addAttribute("friendError", "You have a new friend!");
					return handleFriendsRequest(model);
				case 3:
					model.addAttribute("friendError", newFriendId + " is a friend!");
					return handleFriendsRequest(model);
			}
		}
		return "friends";
	}
}
