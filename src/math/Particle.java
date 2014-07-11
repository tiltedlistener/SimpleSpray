package math;

public class Particle {

	/**
	 * Position and motion variables.
	 */
	public Vector3 position;
	public Vector3 velocity;
	public Vector3 acceleration;
	
	/**
	 * Millington's rough approximation of drag. Page 49.
	 */
	public Double damping;
	
	/**
	 * Inverse mass is more convenient since it is better
	 * to have objects with immovable infinite mass than
	 * zero mass. 
	 */
	public Double inverseMass;
	
	/**
	 * Per Millington - this uses the Newton-Euler integration method, which is a linear approximation
	 * @param duration
	 */
	public void integrate(double duration) {
		
		if (this.inverseMass <= 0) return;
		
		if (duration > 0) {
			// Update position what we have for the velocity
			this.position.addScaledVector(this.velocity, duration);
			
			// Temp
			Vector3 resultingAcc = acceleration;
			velocity.addScaledVector(velocity, duration);
			
			// Impose our drag
			velocity.scalar(Math.pow(damping, duration));
			
			clearAccumulator();
		}
	}
	
	public void clearAccumulator() {
		// At this point Millington has not defined the accumulator
	}
	
	/**
	 * kinetic energy equation based on exercise 3.3
	 * @return a Vector3 of this particle's kinetic energy
	 */
	public Vector3 kineticEnergy() {
		Vector3 currentVelocity = this.velocity.clone();
		
		double currentValueOfMass = 0.5 * (1/this.inverseMass);
		currentVelocity.x = Math.pow(Math.abs(currentVelocity.x), 2);
		currentVelocity.y = Math.pow(Math.abs(currentVelocity.y), 2);
		currentVelocity.z = Math.pow(Math.abs(currentVelocity.z), 2);
		currentVelocity.scalar(currentValueOfMass);
		
		return currentVelocity;
	}
	
}
