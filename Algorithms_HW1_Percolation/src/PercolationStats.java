import java.util.*;

public class PercolationStats {
	private double mean=0;
	private double dev=0;
	private double confidenceLo=0;
	private double confidenceHi=0;
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
			//System.out.println("jjjjjjjjjjjjjjjjjj");
			while(!P.percolates()){
				r = rand.nextInt(N) + 1;				
				c= rand.nextInt(N) + 1;
				//System.out.println("aaaaa");
				if(!P.isOpen(r, c)){
					//System.out.println("dfg");
					P.open(r, c);
					x[i]++;
				}
			}
			x[i]=x[i]/(N*N);
			
		}
		
		//for mean()
		double sum=0;
	    for(int i=0;i<time;i++){
			sum=sum+x[i];
		}
		mean=sum/time;
	
		//for stddev()
		double sum1=0;
		double dev2=0;
		for(int i=0;i<time;i++){
			sum1=(x[i]-mean)*(x[i]-mean);
		}
		dev2=sum1/(time-1);
		dev=Math.sqrt(dev2);
		
		//for
        double time_s=Math.sqrt(time);
		confidenceLo= mean()-(1.96*dev)/time_s;
		confidenceHi=  mean()+(1.96*dev)/time_s;
		
	}
	
	public double mean(){	
		return mean;
	}
	
	public double stddev(){
		return dev;
	}
	
	public double confidenceLo(){
		return confidenceLo;
	}
	
	public double confidenceHi(){
       return confidenceHi;
	}
	
	public static void main(String[] args) {
		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		double mean=0.0;
		double dev=0.0;
		double Lo=0.0;
		double Hi=0.0;
		 
		PercolationStats P=new PercolationStats(N,T);
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
