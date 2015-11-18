package com.sample;

public class CarPool {
	CarModel smallClass;
	CarModel middleClass;
	CarModel compactClass;
	CarModel upperClass;
	
	int smallCount;
	int middleCount;
	int compactCount;
	int upperCount;
	
	public CarPool(){
		smallClass = new CarModel(CarModel.smallClass);
		middleClass = new CarModel(CarModel.middleClass);
		compactClass = new CarModel(CarModel.compactClass);
		upperClass = new CarModel(CarModel.upperClass);
		
		smallCount = 2;
		middleCount = 2;
		compactCount = 2;
		upperCount = 2;
	}
	
	public CarModel getCar(int price) {
		switch(price){
			case CarModel.smallClass:
				if(smallCount > 0){
					this.smallCount--;
					return new CarModel(CarModel.smallClass);
				} else{
					return getHigherModel(CarModel.smallClass);
				}
			case CarModel.compactClass:
				if(compactCount > 0){
					this.compactCount--;
					return new CarModel(CarModel.compactClass);
				} else{
					return getHigherModel(CarModel.compactClass);
				}
			case CarModel.middleClass:
				if(middleCount > 0){
					this.middleCount--;
					return new CarModel(CarModel.middleClass);
				} else{
					return getHigherModel(CarModel.middleClass);
				}
			case CarModel.upperClass:
				if(upperCount > 0){
					this.upperCount--;
					return new CarModel(CarModel.upperClass);
				} else{
					return getHigherModel(CarModel.upperClass);
				}
		}
		return null;
	}
	
	public CarModel getHigherModel (int carModel) {
		switch(carModel) {
			case CarModel.smallClass:
				if(compactCount > 0){
					this.compactCount--;
					return new CarModel(CarModel.compactClass);
				}
				break;
			case CarModel.compactClass:
				if(middleCount > 0){
					this.middleCount--;
					return new CarModel(CarModel.middleClass);
				} 
				break;
			case CarModel.middleClass:
				if(upperCount > 0){
					this.upperCount--;
					return new CarModel(CarModel.upperClass);
				} 
				break;
			case CarModel.upperClass:
				break;
		}
		return null;	
	}
}
