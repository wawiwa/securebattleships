package ejb.domain;

import java.util.Arrays;


public class ShipLayout {
	
	public String name;
	public String[] shipCords;
	
	/**
	 * @param name
	 * @param shipCords
	 */
	public ShipLayout(String name, String[] shipCords) {
		this.name = name;
		this.shipCords = shipCords;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the shipCords
	 */
	public String[] getShipCords() {
		return shipCords;
	}
	
	/**
	 * @param shipCords the shipCords to set
	 */
	public void setShipCords(String[] shipCords) {
		this.shipCords = shipCords;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShipLayout [name=" + name + ", shipCords="
				+ Arrays.toString(shipCords) + "]";
	}
	
	

}
