package model;

import exceptions.InvalidInputException;

public class Sender {
	private String name;
	private int age;
	private String location;
	private boolean isGood;

	public Sender(String name, String age, String location, boolean isGood) throws InvalidInputException {
		if (name.contains(" ")) {
			String testName = name;
			testName = testName.replace(" ", "");
			if (testName.length() >= 5)
				this.name = name;
			else
				throw new InvalidInputException("Name");
		} else
			throw new InvalidInputException("Name");
		try {
			this.age = Integer.parseInt(age);
			if (this.age < 0)
				this.age = Math.abs(this.age);
		} catch (NumberFormatException ex) {
			throw new InvalidInputException("Age");
		}
		if (!location.isBlank())
			this.location = location;
		else
			throw new InvalidInputException("Location");
		this.isGood = isGood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

}
