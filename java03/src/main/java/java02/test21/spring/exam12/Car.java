package java02.test21.spring.exam12;

import java.util.Map;
import java.util.Set;

public class Car {
	String model;
	int cc;
	Engine engine;
	Set<Tire> tires;
	Map<String, Object> options;
	
	
	public Car(){}
	
	public Car(String model) {
		this.model = model;
	}
	
	public Car(String model, int cc){
		this.model = model;
		this.cc = cc;
	}
	


	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	





	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", cc=" + cc + ", engine=" + engine
				+ ", tires=" + tires + ", options=" + options + "]";
	}

	public Set<Tire> getTires() {
		return tires;
	}

	public void setTires(Set<Tire> tires) {
		this.tires = tires;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	
}
