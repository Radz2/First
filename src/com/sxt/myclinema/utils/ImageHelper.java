package com.sxt.myclinema.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ͼƬ������
*@author Ryan
*@version ����ʱ��:2017��6��4��
*/
public class ImageHelper {
	
	public static ImageIcon sclaredImg(String url,int width,int height){
		ImageIcon img1=new ImageIcon(url);
		Image  img2=img1.getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH);
		return new ImageIcon(img2);
	}

}
