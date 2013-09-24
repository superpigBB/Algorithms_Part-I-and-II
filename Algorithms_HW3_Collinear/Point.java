/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER=new SOrder();       // YOUR DEFINITION HERE
    
    
    private class SOrder implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            double slopeP1, slopeP2;
            slopeP1=slopeTo(p1);
            slopeP2=slopeTo(p2);
            if(slopeP1==slopeP2)return 0;
            if(slopeP1<slopeP2)return -1;
            return 1;
        }
    }
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
    	//StdDraw.setXscale(0, 32768);
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
    	
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	
    	 double a,b;
         a=that.y-this.y;
         b=that.x-this.x;
         if(a==0&&b==0) return Double.NEGATIVE_INFINITY;
         if(a==0)return a;
         if(b==0)return Double.POSITIVE_INFINITY;
         return a/b;
 	   
 	   
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	 
 	   if(this.y>that.y) {
 		   return 1;
 	   }else{
 	       if(this.y==that.y){
 		        if(this.x>that.x) 
 			        return 1;
 	            else if(this.x==that.x)
 		            return 0;
 	        }
 	       
 	       
 	   }
 	   
 	   return -1;
 	   
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

   /* 
    // unit test
    public static void main(String[] args) {
    
    Point p1=new Point(5,5);
    	Point p2=new Point(10000,10000);
    	Point p3=new Point(4,3);
    	
    	int s=p1.compareTo(p2);
    	//String str=p1.toString();
    	s=p1.SLOPE_ORDER.compare(p3, p2);
    	//double tan=p1.slopeTo(p2);
    	System.out.println(s);
    	//StdDraw.setXscale(0, 32768);
    	//StdDraw.setYscale(0, 32768);
    	//p1.drawTo(p2);
    	
    	
    }*/
}
