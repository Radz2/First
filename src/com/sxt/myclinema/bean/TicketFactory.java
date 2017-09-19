package com.sxt.myclinema.bean;
/**
 * Ʊ����
*@author Ryan
*@version ����ʱ��:2017��7��6��
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
