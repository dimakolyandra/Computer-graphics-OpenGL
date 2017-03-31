package openglexample;

import java.util.*;

public class Edges {
	private float[] A = new float[3];
	private float[] B = new float[3];
	private float[] C = new float[3];
	private float[] D = new float[3];
	
	private float [] vectNorm = new float[3];
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
	public void setPointsEdge(float[] a,float []b, float []c){
		for(int i = 0; i < 3; i++){
			A[i] = a[i];
			B[i] = b[i];
			C[i] = c[i];
		}
	}	
	
	public float scalyarMult(float [] a,float [] b){
		float res = a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
		return res;
	}
	public void calkNormal (float [] A,float []B,float[]C){
		float [] nA = normalizeVector(A,false);
		float [] nB = normalizeVector(B,false);
		float [] nC = normalizeVector(C,false);
		
		vectNorm[0] = (nB[1] - nA[1])*(nC[2] - nA[2]) - (nB[2] - nA[2])*(nC[1] - nA[1]);
		vectNorm[1] = (nB[2] - nA[2])*(nC[0] - nA[0]) - (nB[0] - nA[0])*(nC[2] - nA[2]);
		vectNorm[2] = (nB[0] - nA[0])*(nC[1] - nA[1]) - (nB[1] - nA[1])*(nC[0] - nA[0]);
	}
	public float robertsAlgo(float [] camera){
		float [] nvectNorm = normalizeVector(vectNorm,false);
		float [] ncamera = normalizeVector(camera,false);
		
		float mult = scalyarMult(nvectNorm,ncamera);
		if(mult > 0){
			isVisible = true;
		}
		else{
			isVisible = false;
		}
		return scalyarMult(vectNorm,camera);
	}
	public float calkLenghtVector(float [] vect){
		return (float)Math.sqrt(vect[0]*vect[0] + vect[1]*vect[1] + vect[2]*vect[2]);
	}
	
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
	public float diffusReflection(float [] lightBeam){
		float[] light = new float[3];
		float[] normalizedVectNorm = new float[3];
		
		light = normalizeVector(lightBeam,true);
		normalizedVectNorm = normalizeVector(vectNorm,false);
		
		float scalMultNormAndLight = scalyarMult(normalizedVectNorm,light);
		
		if(scalMultNormAndLight > 0){
			return scalMultNormAndLight;
		}
		else {
			return 0;
		}
	}
	public float mirrorReflection(float [] lightBeam,float [] camera){
		float [] reflBeam = new float[3];
		float[] lightNorm = new float[3];
		float[] normalizedVectNorm = new float[3];
		float[] cameraNormalized = new float[3];
		
		lightNorm = normalizeVector(lightBeam,true);
		normalizedVectNorm =  normalizeVector(vectNorm,false);
		cameraNormalized = normalizeVector(camera,false);
		
		reflBeam[0] = 2 * normalizedVectNorm[0] * scalyarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[0];
		reflBeam[1] = 2 * normalizedVectNorm[1] * scalyarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[1];
		reflBeam[2] = 2 * normalizedVectNorm[2] * scalyarMult(normalizedVectNorm,cameraNormalized)-cameraNormalized[2];
		if(scalyarMult(lightNorm,reflBeam)> 0){
			return scalyarMult(lightNorm,reflBeam);			
		}
		else{
			return 0;
		}
	}
	public float[] plusVect(float[] one,float[] two){
		float[] result = new float[3];
		result[0] = one[0] + two[0];
		result[1] = one[1] + two[1];
		result[2] = one[2] + two[2];
		return result;
	}
	public float[] multVectOnScal(float one,float[] two){
		float[] result = new float[3];
		result[0] = one * two[0];
		result[1] = one * two[1];
		result[2] = one * two[2];
		return result;
	}
	
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
