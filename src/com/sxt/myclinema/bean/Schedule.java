package com.sxt.myclinema.bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有放映场次类
*@author Ryan
*@version 创建时间:2017年6月25日
*/
public class Schedule implements Serializable{
	private Map<String,ScheduleItem> items;

	//time作为key
	public Schedule() {
		items = new HashMap<String,ScheduleItem>();
		loadItems();
	}

	public Map<String, ScheduleItem> getItems() {
		return items;
	}
	
	public void loadItems(){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("file/movie.txt"));
			String num=br.readLine();
			int count=Integer.parseInt(num);
			for(int i=0;i<count;i++){
				String movieName=br.readLine();
				String actor=br.readLine();
				String director=br.readLine();
				String type=br.readLine().toUpperCase();
				float price=Float.parseFloat(br.readLine());
				MovieType movieType=MovieType.valueOf(type);
				String poster=br.readLine();
				String time1=br.readLine();
				String time2=br.readLine();
				
				Movie movie = new Movie(actor, director, movieName, movieType, poster, price);
				ScheduleItem item1 = new ScheduleItem();
				item1.setMovie(movie);
				item1.setTime(time1);
				ScheduleItem item2 = new ScheduleItem();
				item2.setMovie(movie);
				item2.setTime(time2);
				
				items.put(time1, item1);
				items.put(time2, item2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Schedule s = new Schedule();
		for (String key : s.getItems().keySet()) {
			System.out.println(key+"==>"+s.getItems().get(key).toString());
		}
	}
	
}
