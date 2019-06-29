package nz.ac.arastudent.eyeballmazeassignment2.model;

public class CoOrds {
	public int x, y;
	public PlayerDirection looking;
	
	public CoOrds(int p1, int p2, PlayerDirection dir){
		x = p1;
		y = p2;
		looking = dir;
	}
}
