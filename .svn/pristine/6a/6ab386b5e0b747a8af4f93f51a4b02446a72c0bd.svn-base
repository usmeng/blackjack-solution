package strategy.hit;

public class Condition {
	
	public int pointA;
	public int pointB;
	public int openPoint;
	
	public Condition() {
	}

	public Condition(int pointA, int pointB, int openPoint) {
		super();
		this.pointA = pointA;
		this.pointB = pointB;
		this.openPoint = openPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + openPoint;
		result = prime * result + pointA;
		result = prime * result + pointB;
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
		Condition other = (Condition) obj;
		if (openPoint != other.openPoint)
			return false;
		if (pointA != other.pointA)
			return false;
		if (pointB != other.pointB)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Condition [pointA=" + pointA + ", pointB=" + pointB
				+ ", openPoint=" + openPoint + "]";
	}
	
}
