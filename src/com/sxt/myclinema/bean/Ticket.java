package com.sxt.myclinema.bean;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.sxt.myclinema.utils.DateHelper;

/**
 * 票类
 * 
 * @author Ryan
 * @version 创建时间:2017年6月25日
 */
public class Ticket implements IPrintable, Serializable {
	protected float price;
	protected ScheduleItem scheduleItem;
	protected Seat seat;

	public Ticket() {
		scheduleItem = new ScheduleItem();
		seat = new Seat();
		calPrice();
	}

	public Ticket(ScheduleItem scheduleItem, Seat seat) {
		super();
		this.scheduleItem = scheduleItem;
		this.seat = seat;
		calPrice();
	}

	public float getPrice() {
		calPrice();
		return price;
	}

	// public void setPrice(float price) {
	// this.price = price;
	// }
	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(ScheduleItem scheduleItem) {
		this.scheduleItem = scheduleItem;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((scheduleItem == null) ? 0 : scheduleItem.hashCode());
		result = prime * result + ((seat == null) ? 0 : seat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (scheduleItem == null) {
			if (other.scheduleItem != null)
				return false;
		} else if (!scheduleItem.equals(other.scheduleItem))
			return false;
		if (seat == null) {
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("**************************\r\n");
		sb.append("\t超人气影院\r\n");
		sb.append("--------------------------\r\n");
		sb.append("电影名:" + scheduleItem.getMovie().getMovieName() + "\r\n");
		sb.append("时间:" + scheduleItem.getTime() + "\r\n");
		sb.append("座位号:" + seat.getSeatNum() + "\r\n");
		sb.append("价格:" + getPrice() + "\r\n");// Ticket
		sb.append("---------------------------"+ "\r\n");
		return sb.toString();
	}

	@Override
	public void print() {
		String path = "file/" + DateHelper.strByNow() + "_" + scheduleItem.getMovie().getMovieName() + ".txt";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(path));
			bw.write(toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 计算票价
	 */
	public void calPrice() {
		price = scheduleItem.getMovie().getPrice();
	}

//	public static void main(String[] args) {
//		Schedule schedule = new Schedule();
//		ScheduleItem scheduleItem = schedule.getItems().get("16:00-17:00");
//		Seat seat = new Seat("5-4",Color.YELLOW);
//		Ticket t = new FreeTicket(scheduleItem, seat,"猪儿虫");
//		t.print();
//	}
}
