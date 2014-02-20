import java.util.*;

public class PercolationStats {
	private double[] x=null;
	private int time=0;
	private Percolation P=null;
	private Random rand = new Random();
	public PercolationStats(int N, int T){
		if (N<=0||T<=0) 
		{     
			throw new java.lang.IllegalArgumentException();        
		} 

		x=new double[T];
		
		
		for(int a=0;a<T;a++)
		  x[a]=0.0;
		
		time=T;
		int r=1;
		int c=1;
		for(int i=0; i<T; i++){
			P=new Percolation(N);
			while(!P.percolates()){
				r = rand.nextInt(N) + 1;				
				c= rand.nextInt(N) + 1;
				if(!P.isOpen(r, c)){
					P.open(r, c);
					x[i]++;
				}
			}
			x[i]=x[i]/(N*N);
			P=null;
		}		
	}
	
	public double mean(){
		double mean=0.0;
	    double sum=0.0;
	    for(int i=0;i<time;i++){
			sum=sum+x[i];
		}
		mean=sum/time;
		
		return mean;
	}
	
	public double stddev(){
		double sum1=0.0;
		double dev2=0.0;
		double dev= 0.0;
		for(int i=0;i<time;i++){
			sum1=sum1+Math.pow((x[i]-mean()),2);
		}
		dev2=sum1/(time-1);
		dev=Math.sqrt(dev2);
		
		return dev;
	}
	
	public double confidenceLo(){
		double time_s=Math.sqrt(time);
		
		double result= mean()-(1.96*stddev())/time_s;
		return result;
	}
	
	public double confidenceHi(){
        double time_s=Math.sqrt(time);
		
		double result=  mean()+(1.96*stddev())/time_s;
		return result;
	}
	
	public static void main(String[] args) {
		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);	 
		PercolationStats P=new PercolationStats(N,T);		
		System.out.println("mean                    = "+P.mean());
		System.out.println("stddev                  = "+P.stddev());
		System.out.println("95% confidence interval = "+ P.confidenceLo() +"," + P.confidenceHi());
		
      return;
	}

}
