package com.zoobrew.gymtracker;

public class WorkOutExercise {
	int[] repArray;
	int[] weightArray;
	String name;
	
	public WorkOutExercise(int sets, String n){
		repArray = new int[sets];
		weightArray = new int[sets];
		name = n;
	}
	
	public WorkOutExercise(String n){
		repArray = new int[4];
		weightArray = new int[4];
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public int[] getRepArray(){
		return repArray;
	}
	public int getRep(int set){
		return repArray[set];
	}
	
	public int[] getWeightArray(){
		return weightArray;
	}
	
	public int getWeight(int set){
		return weightArray[set];
	}
	
	
	public void setExcersize(int setNumber, int reps, int weight ){
		repArray[setNumber] = reps;
		weightArray[setNumber] = weight;
	}
	

}
