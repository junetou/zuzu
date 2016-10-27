package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MapDetail;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.admin.security.AdminUserDetails;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.INeedMain;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/map")
public class MapController {

	@Resource
	ICommentMain commentmain;
	
	@Resource
	private IDetailmessageMain iDetailMain; 
	
	@Resource
	private INeedMain iNeedMain; 
	
	@Resource
	private UserHelper userHelper;
	
	@RequestMapping(value="/showmap")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView Map(HttpServletRequest request,ModelAndView model){
		
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		IUser userid=this.userHelper.findUserByUsername(userDetails.getUsername());
		int thingscount=this.iDetailMain.checkcount();
        int needscount=this.iNeedMain.checkcount();
        Integer newcountthings=Integer.valueOf(thingscount);
        Integer newcountneeds=Integer.valueOf(needscount);
        Integer con=1;
        List<MapDetail> message=new ArrayList<MapDetail>();        
        for(con=1;con<=newcountthings;con++){
        	IDetailmessage messagethings=this.iDetailMain.getmessage(con);
        	MapDetail detail=new MapDetail();
        	detail.setJudge(1);
        	detail.setThingsId(messagethings.getthingsId());
        	detail.setThingsdesc(messagethings.getThingsDesc());
        	detail.setThingskind(messagethings.getkind());
        	detail.setThingsname(messagethings.getName());
        	detail.setThingsoveranalyzed(messagethings.getoveranalyzed());
        	detail.setThingspicturename(messagethings.getpicname());
        	if(messagethings.getpicname().equals("")){
        		detail.setThingspicturename("morenpicture.jpg");
        	}
        	detail.setThingsprice(messagethings.getPrice().toString());
        	detail.setThingsaddr(messagethings.getaddr());
        	detail.setThingsphone(messagethings.getphone());
        	detail.setLat(messagethings.getthingsLat());
        	detail.setLng(messagethings.getthingsLng());
        	message.add(detail);
        }
        for(con=1;con<=newcountneeds;con++){
        	INeed messagethings=this.iNeedMain.Search(con);
        	MapDetail detail=new MapDetail();
        	detail.setJudge(0);
        	detail.setThingsId(messagethings.getNeed());
        	detail.setThingsdesc(messagethings.getDescs());
        	detail.setThingskind(messagethings.getKind());
        	detail.setThingsname(messagethings.getName());
        	detail.setThingsoveranalyzed(messagethings.getOveranalyzed());
        	detail.setThingspicturename(messagethings.getOnepicture());
        	if(messagethings.getOnepicture().equals("")){
        		detail.setThingspicturename("morenpicture.jpg");
        	}
        	detail.setThingsprice(messagethings.getPrice().toString());
        	detail.setThingsaddr(messagethings.getAddr());
        	detail.setThingsphone(messagethings.getPhone());
        	detail.setLat(messagethings.getLat());
        	detail.setLng(messagethings.getLng());
        	message.add(detail);
        } 
        request.setAttribute("picture", userid.getPicture());
		model.addObject("usemessage",message)
		     .setViewName("tiles/map");		
		return model;
	} 

	
	
}
