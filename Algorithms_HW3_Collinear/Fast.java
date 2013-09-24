import java.util.Arrays;


public class Fast {
    
	public static void main(String[] args)
   	{
		String s;
		int number=0;
		int i,j=0;
		int k,m,n=0;
		int count=0;
		double value;
		int x_axis;
 		int y_axis;
 		Point[] point=null;
 		Point[] orderedPoints=null;
 		StdDraw.setXscale(0, 32768);
    	StdDraw.setYscale(0, 32768);
 		
		try{
			In in=new In(args[0]);
			s=in.readLine();
			number=Integer.parseInt(s);
			point=new Point[number];
		    for(i=0;i<number;i++){
				
		    	
				x_axis=Integer.parseInt(in.readString());
				y_axis=Integer.parseInt(in.readString());
				point[i]=new Point(x_axis,y_axis);
				point[i].draw();
				
			}
		    
		    Arrays.sort(point);
		}catch(Exception exc){
			System.out.println("Io error"+ exc);
		}
		//TODO: fix Fast algorithms	
		 for(i=1;i<=number-3;i++)
     	{
             k=number-i;
             orderedPoints= new Point[k];
             System.arraycopy(point, 0, orderedPoints, 0, k);
             Arrays.sort(orderedPoints,0,k,point[k].SLOPE_ORDER);
             
             j=0;
             do
             {
                 count=1;
                 m=j;                    
                 value = point[k].slopeTo(orderedPoints[j++]);
                 while(j<k&&point[k].slopeTo(orderedPoints[j])==value){count++;n=j;j++;}
                 if(count>=3)
                 {
                     StdOut.print(point[k].toString()); 
                     for(int l=m;l<=n;l++)
                     {
                     	StdOut.print(" -> " + orderedPoints[l].toString()); 
							point[k].drawTo(orderedPoints[l]);
                     }
                     StdOut.println();
                 }
             }while(j<k);
         }    
     }
		
}