package com.sxt.myclinema.bean;

/**
 * 学生票类
 * 
 * @author Ryan
 * @version 创建时间:2017年6月25日
 */
public class StudentTicket extends Ticket {
	private int discount;

	public StudentTicket() {
		super();
		discount = 10;
	}

	public StudentTicket(ScheduleItem scheduleItem, Seat seat, int discount) {
		super(scheduleItem, seat);
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount;
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
		StudentTicket other = (StudentTicket) obj;
		if (discount != other.discount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("**************************\r\n");
		sb.append("\t超人气影院(学生)\r\n");
		sb.append("--------------------------\r\n");
		sb.append("电影名:" + scheduleItem.getMovie().getMovieName() + "\r\n");
		sb.append("时间:" + scheduleItem.getTime() + "\r\n");
		sb.append("座位号:" + seat.getSeatNum() + "\r\n");
		sb.append("价格:" + getPrice() + "\r\n");// StudentTicket
		sb.append("---------------------------\r\n");
		return sb.toString();
	}
	/**
	 * 计算票价
	 */
	public void calPrice() {
		price = getScheduleItem().getMovie().getPrice() * discount / 10;
	}

}
