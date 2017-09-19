package com.sxt.myclinema.bean;
/**
 * 票工厂
*@author Ryan
*@version 创建时间:2017年7月6日
*/
public class TicketFactory {
	public static Ticket CreatTicket(ScheduleItem scheduleItem,Seat seat,int discount,String customerName,String type){
		Ticket newTicket = null;
		switch(type){
		case "student":
			newTicket = new StudentTicket(scheduleItem, seat, discount);
			break;
		case "free":
			newTicket = new FreeTicket(scheduleItem, seat, customerName);
			break;
		case "":
			newTicket = new Ticket(scheduleItem, seat);
			break;
		}
		
		return newTicket;
	}
}
