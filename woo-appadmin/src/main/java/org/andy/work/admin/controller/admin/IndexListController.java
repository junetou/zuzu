package org.andy.work.admin.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.admin.detail.MessageDetail;
import org.andy.work.admin.helper.UserHelper;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.service.IDetailmessageMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/portal/list")
public class IndexListController {

	@Resource
	private UserHelper userHelper;
	
	@Resource
	private IDetailmessageMain detailmessagemain;

	@RequestMapping(value="/showlist")
	@ResponseBody
	@AuthOperation(roleType=RoleType.THINGS, operationType=AuthOperationConfiguration.THINGS_VIEW)
	public ModelAndView search(ModelAndView model, HttpServletRequest request, AcctUserSearchCriteria search,@RequestParam(required=false) String keyWord){
		GridData<MessageDetail> grid = new GridData<MessageDetail>();
		search.setKeyWork(keyWord);
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		SearchResponse<IDetailmessage> searchResp = this.detailmessagemain.searchUser(new SearchRequest<AcctUserSearchCriteria>(search, pgm));
		if (searchResp.getTotalRecords() > 0) {
			List<MessageDetail> displays = new ArrayList<MessageDetail>();
			List<IDetailmessage> userGroups = searchResp.getResults();
			for (int i = 0; i < userGroups.size(); i++) {
				IDetailmessage user = userGroups.get(i);
				MessageDetail display = new MessageDetail();
				display.setThingsId(user.getthingsId());
				display.setThingsname(user.getName());
				display.setThingsdesc(user.getthingsDesc());
				display.setThingsprice(user.getPrice().toString());
				display.setThingsdate(user.getDate());
				display.setThingskind(user.getkind());
				display.setThingsoveranalyzed(user.getoveranalyzed());
			    display.setThingspicturename(user.getpicname());
				displays.add(display);
			}
			grid.setDatas(displays);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		model.addObject("grid", grid).setViewName("tiles/list-message");
		return model;
	}
	
	
	
	
	
}
