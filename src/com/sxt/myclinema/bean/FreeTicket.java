package com.sxt.myclinema.bean;
/**
 * ��Ʊ��
*@author Ryan
*@version ����ʱ��:2017��6��25��
*/
public class FreeTicket extends Ticket{
	private String customerName;

	public FreeTicket() {
		super();
		customerName = "�����";
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
		sb.append("\t������ӰԺ(��Ʊ)\r\n");
		sb.append("--------------------------\r\n");
		sb.append("��Ӱ��:" + scheduleItem.getMovie().getMovieName() + "\r\n");
		sb.append("ʱ��:" + scheduleItem.getTime() + "\r\n");
		sb.append("��λ��:" + seat.getSeatNum() + "\r\n");
		sb.append("����:" + customerName + "\r\n");// StudentTicket
		sb.append("---------------------------\r\n");
		return sb.toString();
	}
	/**
	 * ����Ʊ��
	 */
	public void calPrice() {
		price = 0;
	}
}
