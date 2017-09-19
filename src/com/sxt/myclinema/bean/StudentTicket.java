package com.sxt.myclinema.bean;

/**
 * ѧ��Ʊ��
 * 
 * @author Ryan
 * @version ����ʱ��:2017��6��25��
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
		sb.append("\t������ӰԺ(ѧ��)\r\n");
		sb.append("--------------------------\r\n");
		sb.append("��Ӱ��:" + scheduleItem.getMovie().getMovieName() + "\r\n");
		sb.append("ʱ��:" + scheduleItem.getTime() + "\r\n");
		sb.append("��λ��:" + seat.getSeatNum() + "\r\n");
		sb.append("�۸�:" + getPrice() + "\r\n");// StudentTicket
		sb.append("---------------------------\r\n");
		return sb.toString();
	}
	/**
	 * ����Ʊ��
	 */
	public void calPrice() {
		price = getScheduleItem().getMovie().getPrice() * discount / 10;
	}

}
