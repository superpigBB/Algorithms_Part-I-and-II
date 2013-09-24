import java.util.Arrays;


public class Brute {
	public static void main(String[] args){
		String s;
		int number=0;
		int i=0;
		int x_axis;
 		int y_axis;
 		Point[] point=null;
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
		   
		    Arrays.sort(point); //sort point
		    
		}catch(Exception exc){
			System.out.println("Io error"+ exc);
		}
		//System.out.println("start");
		for(int x=0;x<number;x++){
			//Point p1=new Point(x_axis[x],y_axis[x]);
			for(int y=x+1;y<number;y++){
				 //Point p2=new Point(x_axis[y],y_axis[y]);
			     double s1=point[x].slopeTo(point[y]);
				for(int z=y+1;z<number;z++){
					 //Point p3=new Point(x_axis[z],y_axis[z]);
					 double s2=point[y].slopeTo(point[z]);
					  if(s1==s2){
					   for(int w=z+1;w<number;w++){
			           
			           // Point p2=new Point(x_axis[y],y_axis[y]);
			           
			           // Point p4=new Point(x_axis[w],y_axis[w]);
						   double s3=point[z].slopeTo(point[w]);
			            if(s1==s3){
			            
			            	//System.out.println("equal");
			            	System.out.println(point[x].toString()+"->"
			            +point[y].toString()+"->"+point[z].toString()
			            		 +"->"+point[w].toString());
			            	
			            	point[x].drawTo(point[w]);
				        
				           // p3.drawTo(p4);
				            
			             }
					   } 
				    }  
				}
			 }
		}		
		//System.out.println("("+x_axis[x]+","+y_axis[x]+")");
		//System.out.println("end");
		
	}

}
