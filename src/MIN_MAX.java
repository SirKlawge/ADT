
public enum MIN_MAX {
	MIN("MIN"), MAX("MAX");
	private final String minMAX;
	
	MIN_MAX(String minMax) {
		this.minMAX = minMax;
	}
	
	public String getMinMax() {
		return this.minMAX;
	}
}
