package com.sample;

public class CarPool {
	CarModel smallClass;
	CarModel middleClass;
	CarModel compactClass;
	CarModel upperClass;
	
	int smallCount;
	int compactCount;
	int middleCount;
	int upperCount;
	
	public CarPool(){
		smallClass = new CarModel(CarModel.smallClass);
		middleClass = new CarModel(CarModel.middleClass);
		compactClass = new CarModel(CarModel.compactClass);
		upperClass = new CarModel(CarModel.upperClass);
		
		smallCount = 2;
		compactCount = 2;
		middleCount = 2;
		upperCount = 2;
	}
	
	public CarModel getCar(int price) {
		switch(price){
			case CarModel.smallClass:
				if(smallCount > 0){
					this.smallCount--;
					return new CarModel(CarModel.smallClass);
				} else{
					return getNewModel(CarModel.smallClass);
				}
			case CarModel.compactClass:
				if(compactCount > 0){
					this.compactCount--;
					return new CarModel(CarModel.compactClass);
				} else{
					return getNewModel(CarModel.compactClass);
				}
			case CarModel.middleClass:
				if(middleCount > 0){
					this.middleCount--;
					return new CarModel(CarModel.middleClass);
				} else{
					return getNewModel(CarModel.middleClass);
				}
			case CarModel.upperClass:
				if(upperCount > 0){
					this.upperCount--;
					return new CarModel(CarModel.upperClass);
				} else{
					return getNewModel(CarModel.upperClass);
				}
		}
		return null;
	}
	
	//behandelt sowohl upgrade als auch downgrade
	public CarModel getNewModel (int carModel) {
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
				if(middleCount > 0){
					this.middleCount--;
					return new CarModel(CarModel.middleClass);
				}
				break;
		}
		return null;	
	}
	
	public void addCarModel(int carModel){
		switch(carModel) {
		case CarModel.smallClass:
			this.smallCount++;
			break;
		case CarModel.compactClass:
			this.compactCount++;
			break;
		case CarModel.middleClass:
			this.middleCount++;
			break;
		case CarModel.upperClass:
			this.upperCount++;
			break;
		}
	}
	
}
