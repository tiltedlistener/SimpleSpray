package math;

public class Vector3 {

	public double x;
	public double y;
	public double z;
	
	/**
	 * Millington explains this is padding to ensure a 4 word alignment in some systems
	 */
	private double pad;
	
	/**
	 * Default Constructor
	 */
	public Vector3() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	/**
	 * Build constructor
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return magnitude of the vector
	 */
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Convenience method for the square of the magnitude
	 * @return Essentially magnitude squared
	 */
	public double squareMagnitude() {
		return x*x + y*y + z*z;
	}
	
	/**
	 * Get Direction
	 * @return
	 */
	public double direction() {
		// TODO
		return 0;
	}
	
	/**
	 * Normalizes the vector
	 */
	public void normalize() {
		double mag = this.magnitude();
		if (mag > 0) {
			this.x = this.x/mag;
			this.y = this.y/mag;
			this.y = this.z/mag;
		}
	}
	
	/**
	 * Helpful to get normal vectors without altering the vector itself
	 * @return computed normal or empty vector
	 */
	public Vector3 getNormal() {
		double mag = this.magnitude();
		if (mag > 0) {
			return new Vector3(
					this.x/mag,
					this.y/mag,
					this.z/mag
					);
		} else {
			return new Vector3();
		}
	}
	
	/**
	 * Adds a vector to this vector
	 * @param addition
	 */
	public void addTo(Vector3 addition) {
		this.x += addition.x;
		this.y += addition.y;
		this.z += addition.z;
	}
	
	/**
	 * Creates new vector after addition
	 * @param addition
	 * @return
	 */
	public Vector3 addAndCreate(Vector3 addition) {
		return new Vector3(addition.x + this.x, addition.y + this.y, addition.z + this.z);
	}
	
	/**
	 * Use this function to add a scaled vector. 
	 * Useful when we're considering interpolation.
	 * @param addition
	 * @param scale
	 */
	public void addScaledVector(Vector3 addition, double scale) {
		this.x += addition.x * scale;
		this.y += addition.y * scale;
		this.z += addition.z * scale;
	}
	
	/**
	 * Subtracts a vector from this one. 
	 * @param subtrahend
	 */
	public void difference(Vector3 subtrahend) {
		this.x -= subtrahend.x;
		this.y -= subtrahend.y;
		this.z -= subtrahend.z;
	}
	
	/**
	 * Subtracts from this vector and creates a new one.
	 * @param subtrahend
	 * @return new Vector that's the result of the current operation
	 */
	public Vector3 differenceAndCreate(Vector3 subtrahend) {
		return new Vector3(this.x - subtrahend.x, this.y - subtrahend.y, this.z - subtrahend.z);
	}
	
	/**
	 * Scalar operation
	 * @param scalar to grow by
	 */
	public void scalar(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
	}
	
	/**
	 * times, desired overload operator for Vec3 * Vec3
	 * @param multiplicand
	 * @return Multiplied vector
	 */
	public Vector3 times(Vector3 multiplicand) {
		return new Vector3(multiplicand.x * this.x, multiplicand.y * this.y, multiplicand.z * this.z);
	}
	
	/**
	 * Result of the vector (or rather Cross) product
	 * @param vector
	 * @return new vector from the result of the operation
	 */
	public Vector3 vectorProduct(Vector3 vector) {
		return new Vector3(
				this.y * vector.z - this.z * vector.y, 
				this.z * vector.x - this.x * vector.z,
				this.x * vector.y - this.y * vector.x 
		);
	}
	
	/**
	 * Helpful method to just get the vector product set to current vector
	 * @param vector
	 */
	public void vectorProductToSelf(Vector3 vector) {
		this.setAsSelf(this.vectorProduct(vector));
	}
	
	/**
	 * Computes the standard vector dot product
	 * @param vec
	 * @return 
	 */
	public double dotProduct(Vector3 vec) {
		return this.x*vec.x + this.y*vec.y + this.z*vec.z;
	}
	
	/**
	 * Finds the orthonormal basis
	 * @param a
	 */
	public void makeOrthonormalBasis(Vector3 a) {
		this.normalize();
		Vector3 b = new Vector3();
		b.setAsSelf(this.vectorProduct(a));
		if (b.squareMagnitude() == 0) return;
		b.normalize();
		a.setAsSelf(b.vectorProduct(this));
	}
	
	/**
	 * Based off Millington's Example. 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void makeOrthnormalBasisWithProvided(Vector3 a, Vector3 b, Vector3 c) {
		a.normalize();
		c.setAsSelf(a.vectorProduct(b));
		if(c.squareMagnitude() == 0) return;
		c.normalize();
		b.setAsSelf(c.vectorProduct(a));
	}
	
	/**
	 * Support methods for copying, resetting
	 */
	public void setAsSelf(Vector3 vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public Vector3 clone() {
		return new Vector3(this.x, this.y, this.z);
	}
	
}
