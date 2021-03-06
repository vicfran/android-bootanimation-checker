package com.bq.bootanimationtest.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.bq.bootanimationtest.animation.AnimationPart;

import android.text.TextUtils;
import android.widget.Toast;

public class DescParser {
	
	private List<AnimationPart> animationParts;
	
	public DescParser() {		
		animationParts = new ArrayList<AnimationPart>();
	}
	
	public DescModel getDescModel(File file) {
		if ((file == null) || (!file.exists())) return null;
		
		return parse(file.getPath());
	}
	
	/*
	 * Parse desc model text file to get its data:
	 * 	- Animation width
	 *  - Animation height
	 *  - Animation fps
	 *  - Animation parts
	 */
	
	private DescModel parse(String file) {
		if (TextUtils.isEmpty(file)) return null;
		
		DescModel descModel = new DescModel();

		BufferedReader bufferedReader = null;
	    try {
			bufferedReader = new BufferedReader(new FileReader(file));

	        String line = bufferedReader.readLine();
	        String[] parts = line.split(" ");
	        
	        descModel.setAnimationWidth(Integer.parseInt(parts[0]));
	        descModel.setAnimationHeight(Integer.parseInt(parts[1]));
	        descModel.setAnimationFps(Integer.parseInt(parts[2]));
	        
	        line = bufferedReader.readLine();

	        while (line != null) {
	        	parts = line.split(" ");
	        	
	        	AnimationPart animationPart = new AnimationPart(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);

	        	animationParts.add(animationPart);
	            
	            line = bufferedReader.readLine();
	        }
	        
	        bufferedReader.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    descModel.setAnimationParts(animationParts);
	    
	    return descModel;
	}

}