package com.example.quran.APIS.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RadioResponse{

	@SerializedName("radios")
	private List<RadiosItem> radios;

	public void setRadios(List<RadiosItem> radios){
		this.radios = radios;
	}

	public List<RadiosItem> getRadios(){
		return radios;
	}

	@Override
 	public String toString(){
		return 
			"RadioResponse{" + 
			"radios = '" + radios + '\'' + 
			"}";
		}
}