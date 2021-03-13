package com.elekes.codewarsvisual.apimodel.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Languages{

	@JsonProperty("coffeescript")
	private Coffeescript coffeescript;

	@JsonProperty("javascript")
	private Javascript javascript;

	@JsonProperty("ruby")
	private Ruby ruby;

	public Coffeescript getCoffeescript(){
		return coffeescript;
	}

	public Javascript getJavascript(){
		return javascript;
	}

	public Ruby getRuby(){
		return ruby;
	}

	@Override
 	public String toString(){
		return 
			"Languages{" + 
			"coffeescript = '" + coffeescript + '\'' + 
			",javascript = '" + javascript + '\'' + 
			",ruby = '" + ruby + '\'' + 
			"}";
		}
}