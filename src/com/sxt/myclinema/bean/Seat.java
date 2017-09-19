package com.sxt.myclinema.bean;

import java.awt.Color;
import java.io.Serializable;

/**
 * 座位类
*@author Ryan
*@version 创建时间:2017年6月25日
*/
public class Seat implements Serializable{
	private String seatNum;
	private Color color;
	
	public Seat() {
		color=Color.YELLOW;
	}
	
	public Seat(String seatNum,Color color) {
		super();
		this.seatNum = seatNum;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((seatNum == null) ? 0 : seatNum.hashCode());
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
		Seat other = (Seat) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (seatNum == null) {
			if (other.seatNum != null)
				return false;
		} else if (!seatNum.equals(other.seatNum))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Seat [color=" + color + ", seatNum=" + seatNum + "]";
	}
	
	
}
