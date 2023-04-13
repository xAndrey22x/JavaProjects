package model;

import enums.GiftType;
import exceptions.InvalidInputException;

public class Gift implements Comparable<Gift> {
	private String name;
	private GiftType giftType;

	public Gift(String name, GiftType giftType) throws InvalidInputException {
		if (name.length() >= 10)
			this.name = name;
		else
			throw new InvalidInputException("GiftName");
		this.giftType = giftType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GiftType getGiftType() {
		return giftType;
	}

	public void setGiftType(GiftType giftType) {
		this.giftType = giftType;
	}

	@Override
	public int compareTo(Gift o) {
		if (this.name.compareTo(o.getName()) < 0)
			return -1;
		else if (this.name.compareTo(o.getName()) > 0)
			return 1;
		else if (this.giftType.equals(o.giftType)) // Astfel cadourile sunt considerate diferite daca au si tip diferit,
			return 0; // acuma putem insera acelasi cadou de tip diferit, dar nu acelasi cadou
						// de acelasi tip.
		else
			return 1;
	}

}
