package ejb.domain;


public class Round {
	
	public boolean isHit;
	public String coords;
	
	/**
	 * @param isHit
	 * @param coords
	 */
	public Round(boolean isHit, String coords) {
		this.isHit = isHit;
		this.coords = coords;
	}
	
	/**
	 * @return
	 */
	public boolean isHit() {
		return isHit;
	}

	/**
	 * @param isHit
	 */
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	/**
	 * @return
	 */
	public String getCoords() {
		return coords;
	}

	/**
	 * @param coords
	 */
	public void setCoords(String coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return "Round [isHit=" + isHit + ", coords=" + coords + "]";
	}
	
	

}
