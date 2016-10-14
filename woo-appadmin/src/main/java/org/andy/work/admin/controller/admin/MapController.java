package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MapDetail;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.INeed;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.appserver.service.INeedMain;
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
	
	@RequestMapping(value="/showmap")
	@ResponseBody 
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView Map(ModelAndView model){
		
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
        	detail.setThingsdesc(messagethings.getthingsDesc());
        	detail.setThingskind(messagethings.getkind());
        	detail.setThingsname(messagethings.getName());
        	detail.setThingsoveranalyzed(messagethings.getoveranalyzed());
        	detail.setThingspicturename(messagethings.getpicname());
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
        	detail.setThingspicturename("");
        	detail.setThingsprice(messagethings.getPrice().toString());
        	detail.setThingsaddr(messagethings.getAddr());
        	detail.setThingsphone(messagethings.getPhone());
        	detail.setLat(messagethings.getLat());
        	detail.setLng(messagethings.getLng());
        	message.add(detail);
        } 
		model.addObject("usemessage",message)
		     .setViewName("tiles/map");		
		return model;
	} 

	
	
}
