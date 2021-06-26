package com.skilldistillery.filmquery.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Inventory {
	private Map<String, Integer> inventory = new HashMap<>();
	private int filmId;
	
	public Map<String, Integer> getInventory() {
		return inventory;
	}
	public void setInventory(Map<String, Integer> inventory) {
		this.inventory = inventory;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public void addAllToInventory(Map<String, Integer> inventory) {
		this.inventory.putAll(inventory);
	}
	
	@Override
	public String toString() {
		Set<String> s = inventory.keySet();
		Iterator<String> it = s.iterator();
		StringBuilder sb = new StringBuilder();
		sb.append("Inventory -- ");
		while (it.hasNext()) {
			String key = it.next();
			if (key != null) {
				sb.append(key + ": ");
			} else {sb.append("Unknown: ");}
			sb.append(inventory.get(key) + ", ");
		}
		sb.delete(sb.length()-1, sb.length());
		String result = sb.toString();
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + filmId;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (filmId != other.filmId)
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}
	
	
	
}
