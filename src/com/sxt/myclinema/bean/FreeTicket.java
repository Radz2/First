package com.sxt.myclinema.bean;
/**
 * 赠票类
*@author Ryan
*@version 创建时间:2017年6月25日
*/
public class FreeTicket extends Ticket{
	private String customerName;

	public FreeTicket() {
		super();
		customerName = "活动赠送";
	}

	public FreeTicket(ScheduleItem scheduleItem, Seat seat, String customerName) {
		super(scheduleItem, seat);
		this.customerName = customerName;
	}

	public String getCustomerName() {
		calPrice();
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FreeTicket other = (FreeTicket) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("**************************\r\n");
		sb.append("\t超人气影院(赠票)\r\n");
		sb.append("--------------------------\r\n");
		sb.append("电影名:" + scheduleItem.getMovie().getMovieName() + "\r\n");
		sb.append("时间:" + scheduleItem.getTime() + "\r\n");
		sb.append("座位号:" + seat.getSeatNum() + "\r\n");
		sb.append("姓名:" + customerName + "\r\n");// StudentTicket
		sb.append("---------------------------\r\n");
		return sb.toString();
	}
	/**
	 * 计算票价
	 */
	public void calPrice() {
		price = 0;
	}
}
