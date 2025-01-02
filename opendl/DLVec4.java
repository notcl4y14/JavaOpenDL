package opendl;

public class DLVec4 {
	public double v1;
	public double v2;
	public double v3;
	public double v4;

	public DLVec4(double v1, double v2, double v3, double v4) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
	}

	public DLVec4 (DLVec3 vector, double v) {
		this.v1 = vector.v1;
		this.v2 = vector.v2;
		this.v3 = vector.v3;
		this.v4 = v;
	}

	public DLVec4 add (double v) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 + v;
		result.v2 = this.v2 + v;
		result.v3 = this.v3 + v;
		result.v4 = this.v4 + v;
		return result;
	}

	public DLVec4 add (DLVec4 vector) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 + vector.v1;
		result.v2 = this.v2 + vector.v2;
		result.v3 = this.v3 + vector.v3;
		result.v4 = this.v4 + vector.v4;
		return result;
	}

	public DLVec4 sub (double v) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 - v;
		result.v2 = this.v2 - v;
		result.v3 = this.v3 - v;
		result.v4 = this.v4 - v;
		return result;
	}

	public DLVec4 sub (DLVec4 vector) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 - vector.v1;
		result.v2 = this.v2 - vector.v2;
		result.v3 = this.v3 - vector.v3;
		result.v4 = this.v4 - vector.v4;
		return result;
	}

	public DLVec4 mul (double v) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 * v;
		result.v2 = this.v2 * v;
		result.v3 = this.v3 * v;
		result.v4 = this.v4 * v;
		return result;
	}

	public DLVec4 mul (DLVec4 vector) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 * vector.v1;
		result.v2 = this.v2 * vector.v2;
		result.v3 = this.v3 * vector.v3;
		result.v4 = this.v4 * vector.v4;
		return result;
	}

	public DLVec4 div (double v) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 / v;
		result.v2 = this.v2 / v;
		result.v3 = this.v3 / v;
		result.v4 = this.v4 / v;
		return result;
	}

	public DLVec4 div (DLVec4 vector) {
		DLVec4 result = new DLVec4(0, 0, 0, 0);
		result.v1 = this.v1 / vector.v1;
		result.v2 = this.v2 / vector.v2;
		result.v3 = this.v3 / vector.v3;
		result.v4 = this.v4 / vector.v4;
		return result;
	}
}
