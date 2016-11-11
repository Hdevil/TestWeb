package com.jz.action;

import java.sql.Timestamp;

import java.util.Date;

import com.jz.bean.Black;
import com.jz.service.BlackService;
import com.jz.service.BlackServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class BlackOptAction extends ActionSupport {

	private BlackService blaserv ;
	private String bname;
	private String opt;

	
	
	public BlackService getBlaserv() {
		return blaserv;
	}

	public void setBlaserv(BlackService blaserv) {
		this.blaserv = blaserv;
	}

	@Override
	public String execute() throws Exception {

		Black black = blaserv.findByName(bname);

		if (black == null && opt.equals("in")) {

			black = new Black();
			black.setName(bname);
			black.setIncludeDate(new Timestamp((new Date()).getTime()));
			if (black.getName().equals("")) {

				return "back1";
			}

			blaserv.saveBlack(black);

		}
		if (black != null) {
			if (opt != null && opt.equals("in")) {

				black.setRemoved(0);
				black.setIncludeDate(new Timestamp((new Date()).getTime()));

			}
			if (opt != null && opt.equals("out")) {

				black.setRemoved(1);
				black.setIncludeDate(new Timestamp((new Date()).getTime()));

			}

			blaserv.updateBlack(black);
		}

		return "back1";
	}

	// public String in() {
	//
	// if (black == null) {
	// black.setName(bname);
	// black.setIncludeDate(DateUtil2.toDate(DateUtil2.getcrtime()));
	// if (black.getName().equals("")) {
	// return "back1";
	// }
	// blaserv.saveBlack(black);
	//
	// } else {
	// black.setRemoved(0);
	// black.setIncludeDate(DateUtil2.toDate(DateUtil2.getcrtime()));
	// blaserv.updateBlack(black);
	// }
	//
	// return "success";
	// }
	//
	// public String out() {
	//
	// if (black != null) {
	// black.setRemoved(1);
	// black.setIncludeDate(DateUtil2.toDate(DateUtil2.getcrtime()));
	// blaserv.updateBlack(black);
	// }
	//
	// return "success";
	// }

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

}
