package com.movemini.util;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.IndexedColors;

public class ColorUtil {

	private Random randomGenerator;
	private ArrayList<String> catalogue;
	private List<Short>  listColorsExcel;

	public ColorUtil(){
		catalogue = new ArrayList<String>();
		randomGenerator = new Random();
		
		listColorsExcel = new ArrayList<Short>();

		catalogue.add("#FFCDD2");
		catalogue.add("#EF9A9A");
		catalogue.add("#E57373");
		catalogue.add("#EF5350");
		catalogue.add("#F8BBD0");
		catalogue.add("#F48FB1");
		catalogue.add("#F06292");
		catalogue.add("#E1BEE7");
		catalogue.add("#CE93D8");
		catalogue.add("#BA68C8");
		catalogue.add("#B39DDB");
		catalogue.add("#9575CD");
		catalogue.add("#9FA8DA");
		catalogue.add("#7986CB");
		catalogue.add("#5C6BC0");
		catalogue.add("#90CAF9");
		catalogue.add("#42A5F5");
		catalogue.add("#2196F3");
		catalogue.add("#80DEEA");
		catalogue.add("#00B0FF");
		catalogue.add("#4DB6AC");
		catalogue.add("#00796B");
		catalogue.add("#00BFA5");
		catalogue.add("#A5D6A7");
		catalogue.add("#66BB6A");
		catalogue.add("#69F0AE");
		catalogue.add("#AED581");
		catalogue.add("#64DD17");
		catalogue.add("#DCE775");
		catalogue.add("#F4FF81");
		catalogue.add("#FFF176");
		catalogue.add("#FFD54F");
		catalogue.add("#FFAB00");
		catalogue.add("#FF6D00");
		catalogue.add("#A1887F");
		
		
		catalogue.add("#CD5C5C");
		catalogue.add("#F08080");
		catalogue.add("#FA8072");
		catalogue.add("#E9967A");
		catalogue.add("#FFA07A");
		catalogue.add("#DC143C");
		catalogue.add("#B22222");
		catalogue.add("#8B0000");
		catalogue.add("#FFC0CB");
		catalogue.add("#FFB6C1");
		catalogue.add("#FF69B4");
		catalogue.add("#FF1493");
		catalogue.add("#C71585");
		catalogue.add("#DB7093");
		catalogue.add("#FFA07A");
		catalogue.add("#FF7F50");
		catalogue.add("#FF6347");
		catalogue.add("#FF4500");
		catalogue.add("#FF8C00");
		catalogue.add("#FFA500");
		catalogue.add("#9370DB");
		catalogue.add("#9966CC");
		catalogue.add("#00BFA5");
		catalogue.add("#9400D3");
		catalogue.add("#4B0082");
		catalogue.add("#7B68EE");
		catalogue.add("#AED581");
		catalogue.add("#64DD17");
		catalogue.add("#DCE775");
		catalogue.add("#F4FF81");
		catalogue.add("#FFF176");
		catalogue.add("#FFD54F");
		catalogue.add("#FFAB00");
		catalogue.add("#FF6D00");
		catalogue.add("#A1887F");
		
		listColorsExcel.add(IndexedColors.BLUE.getIndex());
		listColorsExcel.add(IndexedColors.AQUA.getIndex());
		//listColorsExcel.add(IndexedColors.AUTOMATIC.getIndex());
		listColorsExcel.add(IndexedColors.BLUE_GREY.getIndex());
		listColorsExcel.add(IndexedColors.BRIGHT_GREEN.getIndex());
		//listColorsExcel.add(IndexedColors.BROWN.getIndex());
		listColorsExcel.add(IndexedColors.CORAL.getIndex());
		listColorsExcel.add(IndexedColors.CORNFLOWER_BLUE.getIndex());
		listColorsExcel.add(IndexedColors.DARK_BLUE.getIndex());
		//listColorsExcel.add(IndexedColors.DARK_GREEN.getIndex());
		listColorsExcel.add(IndexedColors.DARK_RED.getIndex());
		//listColorsExcel.add(IndexedColors.DARK_TEAL.getIndex());
		listColorsExcel.add(IndexedColors.DARK_YELLOW.getIndex());
		listColorsExcel.add(IndexedColors.GOLD.getIndex());
		listColorsExcel.add(IndexedColors.GREEN.getIndex());
		listColorsExcel.add(IndexedColors.GREY_25_PERCENT.getIndex());
		listColorsExcel.add(IndexedColors.GREY_40_PERCENT.getIndex());
		listColorsExcel.add(IndexedColors.GREY_80_PERCENT.getIndex());
		listColorsExcel.add(IndexedColors.INDIGO.getIndex());
		listColorsExcel.add(IndexedColors.LAVENDER.getIndex());
		listColorsExcel.add(IndexedColors.LEMON_CHIFFON.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_BLUE.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_GREEN.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_ORANGE.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_TURQUOISE.getIndex());
		listColorsExcel.add(IndexedColors.LIGHT_YELLOW.getIndex());
		listColorsExcel.add(IndexedColors.LIME.getIndex());
		//listColorsExcel.add(IndexedColors.MAROON.getIndex());
		listColorsExcel.add(IndexedColors.OLIVE_GREEN.getIndex());
		listColorsExcel.add(IndexedColors.ORANGE.getIndex());
		listColorsExcel.add(IndexedColors.ORCHID.getIndex());
		listColorsExcel.add(IndexedColors.PALE_BLUE.getIndex());
		listColorsExcel.add(IndexedColors.PINK.getIndex());
		listColorsExcel.add(IndexedColors.PLUM.getIndex());
		listColorsExcel.add(IndexedColors.RED.getIndex());
		listColorsExcel.add(IndexedColors.ROSE.getIndex());
		listColorsExcel.add(IndexedColors.ROYAL_BLUE.getIndex());
		listColorsExcel.add(IndexedColors.SEA_GREEN.getIndex());
		listColorsExcel.add(IndexedColors.SKY_BLUE.getIndex());
		//listColorsExcel.add(IndexedColors.TAN.getIndex());
		listColorsExcel.add(IndexedColors.TEAL.getIndex());
		listColorsExcel.add(IndexedColors.TURQUOISE.getIndex());
		listColorsExcel.add(IndexedColors.VIOLET.getIndex());
		listColorsExcel.add(IndexedColors.YELLOW.getIndex());

	}

	public String obtenerColorHexadecimal(){
		int r, g, b;
		Random ra = new Random();

		do{
			r=ra.nextInt(255);
			g=ra.nextInt(255);
			b=ra.nextInt(255);
		}while(!(r == 128 && g != 128 && b != 128 ));

		Color color = new Color(r,g,b);

		String colorHexadecimal = Integer.toHexString(color.getRGB() & 0xffffff);

		if (colorHexadecimal.length() < 6) {
			colorHexadecimal = "0" + colorHexadecimal;
		}

		colorHexadecimal = "#" + colorHexadecimal;

		return colorHexadecimal;
	}


	public String anyColor ()
	{
			int index = randomGenerator.nextInt(catalogue.size());
			String color = catalogue.get(index);
			return color;
	}
	
	public short anyColorExcel ()
	{
			int index = randomGenerator.nextInt(listColorsExcel.size());
			short color = listColorsExcel.get(index);
			return color;
	}

}
