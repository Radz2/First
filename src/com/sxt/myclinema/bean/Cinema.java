package com.sxt.myclinema.bean;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * 影院类
*@author Ryan
*@version 创建时间:2017年6月26日
*/
public class Cinema {
	
	private static Schedule schedule;//放映
	private static Map<String,Seat> seats;//座位
	private static List<Ticket> soldTicket;//已售票
	
	static{
		File file = new File("file/set.bin");
		if(file.exists()){
			int result = JOptionPane.showConfirmDialog(null, "是否加载数据?");
			if(result == JOptionPane.YES_OPTION){
				try {
					schedule = new Schedule();
					seats = new HashMap<String, Seat>();
					soldTicket = load();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}else if(result == JOptionPane.NO_OPTION){
				init();
			}else{
				System.exit(0);
			}
		}else{
			init();
		}
	}
	
	public Cinema() {
		
	}

	private static void init() {
		schedule=new Schedule();
		seats=new HashMap<String,Seat>();
		soldTicket=new ArrayList<Ticket>();
	}

	public static Schedule getSchedule() {
		return schedule;
	}

	public static Map<String, Seat> getSeats() {
		return seats;
	}

	public static List<Ticket> getSoldTicket() {
		return soldTicket;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((soldTicket == null) ? 0 : soldTicket.hashCode());
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
		Cinema other = (Cinema) obj;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		if (soldTicket == null) {
			if (other.soldTicket != null)
				return false;
		} else if (!soldTicket.equals(other.soldTicket))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cinema [schedule=" + schedule + ", seats=" + seats + ", soldTicket=" + soldTicket + "]";
	}
	
	/**
	 * 反序列化
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<Ticket> load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("file/set.bin")));
		return (ArrayList<Ticket>)ois.readObject();
	}
	
	/**
	 * 序列化
	 * @throws IOException 
	 */
	public static void save() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("file/set.bin")));	
		oos.writeObject(soldTicket);
		oos.flush();
		oos.close();
	}
}
