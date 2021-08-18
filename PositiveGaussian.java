import java.util.Random;

/**
 * <p>Truncated positive Gaussian distribution with optimized random generator:
 *
 * <p>Reference:<br>
 * V. Mazet, D. Brie and J. Idier : Simulation of Positive Normal Variables Using Several
 * Proposal Distributions. Statistical Signal Processing, 2005 IEEE/SSP 13th Workshop,
 * pages 37-42, July 2005.
 * 
 * 
 * @version $Revision: 1.0 $ $Date: 2010/12/01 17:58:12 $
 * @author Benjamin Perret bperret@unistra.fr
 *
 */

/* This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2008, 2009, 2010 Benjamin Perret, Université de Strasbourg, LSIIT
*/

public class PositiveGaussian{

	/**
	 * Pseudo-mean of the distribution (mean of the Gaussian without truncation)
	 */
	private double mean;

	/**
	 * Pseudo-standard deviation of the distribution (standard deviation of the Gaussian without truncation)
	 */
	private double dev;

	/**
	 * Pseudo-variance of the distribution (variance of the Gaussian without truncation)
	 */
	private double dev2;

	/**
	 * Static constant used in the algorithm
	 */
	private static double A  = 1.136717791056118;
	
	/**
	 * Classical random generator
	 */
	private static final Random random= new Random();
	
	/**
	 * Print debug information
	 */
	private boolean debug=false;
	
	/**
	 * Constructor specifying pseudo mean and standard deviation of the distribution
	 */
	public PositiveGaussian(double mean, double dev) 
	{
		this.mean=mean;
		this.dev=dev;
		dev2=dev*dev;
	}

	/**
	 * Generates a random value according to this distribution
	 */
	public double getRandomValue() {

		
		double v=Double.NaN;

		double mA = (1-A*A)/A*dev;
		double mC = dev * Math.sqrt(Math.PI/2.0);

		double a,z,rho;
		while(Double.isNaN(v))
		{
			if (mean < mA) //,      % 4. Exponential distribution
			{
				if (debug) System.out.println("Exponential distribution ");
				a = (-mean + Math.sqrt(mean*mean+4.0*dev2)) / 2 / dev2;
				z = -Math.log(1.0-random.nextDouble())/a; // -log(1-rand(N,1))/a;
				rho = Math.exp( -(z-mean)*(z-mean)/2.0/dev2 - a*(mean-z+a*dev2/2.0) );
			}


			else if (mean <= 0) //,  % 3. Normal distribution truncated at the mean
				//% equality because 3 is faster to compute than the 2
			{
				if (debug) System.out.println("Normal distribution truncated at the mean ");
				z = Math.abs(random.nextGaussian())*dev + mean;
				rho = (z>=0.0)?1.0:0.0;
			}

			else if (mean < mC) //,  % 2. Normal distribution coupled with the uniform one
			{
				if (debug) System.out.println("Gaussian + uniform ");
				double r = (random.nextDouble() < mean/(mean+Math.sqrt(Math.PI/2.0)*dev))?1.0:0.0;

				double u = random.nextDouble()*mean;
				double g = Math.abs(random.nextGaussian()*dev) + mean;
				z = r*u + (1.0-r)*g;
				rho = r*Math.exp(-(z-mean)*(z-mean)/2.0/dev2) + (1.0-r);
			}

			else //,           % 1. Normal distribution
			{
				
				z = random.nextGaussian()*dev + mean;
				if (debug) System.out.println("Gaussian use " + z);
				rho = (z>=0)?1.0:0.0;
			}

			if(random.nextDouble()<=rho)
			{
				v=z;
				if (debug) System.out.println("Accepted");
			}
		}
		return v;
  
	}
	
	/**
	 * @return the pseudo-mean of the distribution
	 */
	public double getMean() {
		return mean;
	}

	/**
	 * @param mean the pseudo-mean to set
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}

	/**
	 * @return the pseudo-standard deviation of the distribution
	 */
	public double getDev() {
		return dev;
	}

	/**
	 * @param dev the pseudo-standard deviation to set
	 */
	public void setDev(double dev) {
		dev2=dev*dev;
		this.dev = dev;
	}

}
