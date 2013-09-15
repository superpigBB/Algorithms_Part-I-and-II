import java.util.*;

public class PercolationStats {
	private double mean=0;
	private double dev=0;
	private double[] x=null;
	private int time=0;
	private Percolation P=null;
	private Random rand = new Random();
	private boolean[][] record=null;
	public PercolationStats(int N, int T){
		if (N<=0||T<=0) 
		{     
			throw new java.lang.IllegalArgumentException();        
		} 

		x=new double[T];
		
		for(int a=0;a<T;a++)
		  x[a]=0;
		
		time=T;
		int r=1;
		int c=1;
		for(int i=0; i<T; i++){
			P=new Percolation(N);
			record=new boolean[N][N];
			
			for(int x=0;x<N;x++)
				for(int y=0;y<N;y++)
					record[x][y]=false;
			
			
			while(P.percolates()==false){
				r = rand.nextInt(N) + 1;				
				c= rand.nextInt(N) + 1;
				
				if(record[r-1][c-1]==false){
					record[r-1][c-1]=true;
					P.open(r, c);
					x[i]++;
				}
			}
			x[i]=x[i]/(N*N);
			
		}		
	}
	
	public double mean(){
	    double sum=0;
	    for(int i=0;i<time;i++){
			sum=sum+x[i];
		}
		mean=sum/time;
		
		return mean;
	}
	
	public double stddev(){
		double sum1=0;
		double dev2=0;
		for(int i=0;i<time;i++){
			sum1=(x[i]-mean)*(x[i]-mean);
		}
		dev2=sum1/(time-1);
		dev=Math.sqrt(dev2);
		
		return dev;
	}
	
	public double confidenceLo(){
		double time_s=Math.sqrt(time);
		
		double result= mean-(1.96*dev)/time_s;
		return result;
	}
	
	public double confidenceHi(){
        double time_s=Math.sqrt(time);
		
		double result= mean+(1.96*dev)/time_s;
		return result;
	}
	
	public static void main(String[] args) {
		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		double mean=0;
		double dev=0;
		double Lo=0;
		double Hi=0;
		 
		PercolationStats P=new  PercolationStats(N,T);
		
		mean=P.mean();
		dev=P.stddev();
		Lo=P.confidenceLo();
		Hi=P.confidenceHi();		
		System.out.println("mean                    = "+mean);
		System.out.println("stddev                  = "+dev);
		System.out.println("95% confidence interval = "+Lo +"," +Hi);
		
      return;
	}

}
