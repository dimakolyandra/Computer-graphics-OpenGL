package openglexample;

import java.util.*;

/**
 * Class that stores the points
 * of polygon and coordinates of normal
 * @author dimakolyandra
 */
public class Edges {
	
	/** First point */
	private float[] A = new float[3];
	
	/** Second point */
	private float[] B = new float[3];
	
	/** Third point */
	private float[] C = new float[3];
	
	/** Fourth point */
	private float[] D = new float[3];
	
	/** Coordinates of normal */
	private float [] vectNorm = new float[3];
	
	/** Boolean that defines visibility */
	public boolean isVisible = false;
	
	public float[] getFirstPoint(){
		return A;
	}
	
	public float[] getSecondPoint(){
		return B;
	}
	
	public float[] getThirdPoint(){
		return C;
	}
	
	public float[] getFourPoint(){
		return D;
	}
	
	public float[] getVectNorm(){
		return vectNorm;
	}
	
	public void setPointsEdge(float[] a,float []b, float []c,float[] d){
		for(int i = 0; i < 3; i++){
			A[i] = a[i];
			B[i] = b[i];
			C[i] = c[i];
			D[i] = d[i];
		}
	}
	
	/** Scalar product of two vectors */
	public float scalarMult(float [] a,float [] b){
		float res = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
		return res;
	}
	
	/** Calculate normal vector by three points 
	 * @param A Array of coordinates of first point
	 * @param B Array of coordinates of second point
	 * @param C Array of coordinates of third point
	 */
	public void calkNormal (float [] A,float []B,float[]C){
		float [] nA = normalizeVector(A,false);
		float [] nB = normalizeVector(B,false);
		float [] nC = normalizeVector(C,false);
		vectNorm[0] = (nB[1] - nA[1])*(nC[2] - nA[2]) - (nB[2] - nA[2])*(nC[1] - nA[1]);
		vectNorm[1] = (nB[2] - nA[2])*(nC[0] - nA[0]) - (nB[0] - nA[0])*(nC[2] - nA[2]);
		vectNorm[2] = (nB[0] - nA[0])*(nC[1] - nA[1]) - (nB[1] - nA[1])*(nC[0] - nA[0]);
	}
	
	/** 
	 * Roberts algorithm of rendering invisible faces 
	 * @param camera Coordinates of camera
	 **/
	public float robertsAlgo(float [] camera){
		float [] nvectNorm = normalizeVector(vectNorm,false);
		float [] ncamera = normalizeVector(camera,false);
		
		float mult = scalarMult(nvectNorm,ncamera);
		if(mult > 0){
			isVisible = true;
		}
		else{
			isVisible = false;
		}
		return scalarMult(vectNorm,camera);
	}
	
	/** Calculates length of vector
	 * @param vect Coordinates of vector
	 **/
	public float calkLenghtVector(float [] vect){
		return (float)Math.sqrt(vect[0]*vect[0] + vect[1]*vect[1] + vect[2]*vect[2]);
	}
	
	/**
	 * Method that normalize vectors
	 * @param vect Coordinates of vector
	 * @param isLightBeam Boolean that determine whether the vector
	 * coordinates of the light source 
	 * @return Normalized coordinates of input vector
	 */
	public float[] normalizeVector(float [] vect, boolean isLightBeam){
		float[] result = new float[3];
		float lenghtOfVect = calkLenghtVector(vect);
		if(isLightBeam){
			float pointX = (A[0] + B[0])/2; 
			float pointY = (A[1] + B[1])/2; 
			float pointZ = (A[2] + (-1)*A[2])/2;
			result[0] = (vect[0] - pointX) /lenghtOfVect;
			result[1] = (vect[1] - pointY) / lenghtOfVect;
			result[2] = (vect[2] - pointZ) / lenghtOfVect;					
		}
		else{
			result[0] = vect[0] / lenghtOfVect;
			result[1] = vect[1] / lenghtOfVect;
			result[2] = vect[2] / lenghtOfVect;							
		}
		return result;
	}
	
	/** Method that realize diffusion reflection model
	 *	@param lightBeam Coordinates of the light source
	 *	@return Diffusion coefficient
	 **/
	public float diffusReflection(float [] lightBeam){
		float[] light = new float[3];
		float[] normalizedVectNorm = new float[3];
		
		light = normalizeVector(lightBeam,true);
		normalizedVectNorm = normalizeVector(vectNorm,false);
		
		float scalMultNormAndLight = scalarMult(normalizedVectNorm,light);
		
		if(scalMultNormAndLight > 0){
			return scalMultNormAndLight;
		}
		else {
			return 0;
		}
	}
	
	/** Method that realize diffusion reflection model
	 *	@param lightBeam Coordinates of the light source
	 *	@return Diffusion coefficient
	 **/
	public float mirrorReflection(float [] lightBeam,float [] camera){
		float [] reflBeam = new float[3];
		float[] lightNorm = new float[3];
		float[] normalizedVectNorm = new float[3];
		float[] cameraNormalized = new float[3];
		
		lightNorm = normalizeVector(lightBeam,true);
		normalizedVectNorm =  normalizeVector(vectNorm,false);
		cameraNormalized = normalizeVector(camera,false);
		
		reflBeam[0] = 2 * normalizedVectNorm[0] * scalarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[0];
		reflBeam[1] = 2 * normalizedVectNorm[1] * scalarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[1];
		reflBeam[2] = 2 * normalizedVectNorm[2] * scalarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[2];
		if(scalarMult(lightNorm,reflBeam)> 0){
			return scalarMult(lightNorm,reflBeam);			
		}
		else{
			return 0;
		}
	}
	
	/** Calculates summ of vectors 
	 * @param one First vector
	 * @param two second vector
	 * @return Result vector 
	 **/
	public float[] plusVect(float[] one,float[] two){
		float[] result = new float[3];
		result[0] = one[0] + two[0];
		result[1] = one[1] + two[1];
		result[2] = one[2] + two[2];
		return result;
	}
	
	/** Multiplication of a vector by a number 
	 * @param one Number
	 * @param two Vector
	 * @return Result vector
	 **/ 
	public float[] multVectOnScal(float one,float[] two){
		float[] result = new float[3];
		result[0] = one * two[0];
		result[1] = one * two[1];
		result[2] = one * two[2];
		return result;
	}
	
	/** Fonts model of lighting 
	 * @param light Coordinates of light source
	 * @param camera Coordinates of camera
	 * @return Color in RGB view
	 **/
	public float[] Font(float[] light,float[] camera){
		float[] diffColor = {0.5f,0f,0f};
		float[] specColor = {0.7f,0.7f,0.0f};
		float[] diffuse = new float[3];
		float[] mirror = new float[3];
		diffuse =  multVectOnScal(diffusReflection(light),diffColor);
		mirror = multVectOnScal((float)Math.pow(mirrorReflection(light,camera),20),specColor);
		return plusVect(diffuse,mirror);
	}
}
