import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


public class PointSET {
	   private SET<Point2D> set=null;
	   private List<Point2D> list_range;
	   private static final Color RED        = Color.RED;
	   public PointSET()  {
		   // construct an empty set of points
		   set=new SET<Point2D>();
		   
	   }
	   public boolean isEmpty()   {
		   // is the set empty?
		   return set.isEmpty();
	   }
	   public int size(){
		   // number of points in the set
		   return set.size();
	   }
	   public void insert(Point2D p) throws Exception{
		   // add the point p to the set (if it is not already in the set)
		   if(p==null) {
			   throw new Exception("point2d is null"); 
		   }
		   
		   if(set.contains(p)==false) 
		   set.add(p);
		   
	   }
	   public boolean contains(Point2D p){
		   // does the set contain the point p?
		  return set.contains(p);
	   }
	   
	   public void draw() {
		   // draw all of the points to standard draw
		   StdDraw.setYscale(0, 1) ;
		   StdDraw.setXscale(0, 1) ;
		   StdDraw.setPenColor(RED);
		   StdDraw.setPenRadius(0.008);
		   
		   for( Point2D temp:set)
		     StdDraw.point(temp.x(),temp.y());
		  
	   }
	   
	   public Iterable<Point2D> range(RectHV rect){
		   // all points in the set that are inside the rectangle
		   list_range=new LinkedList<Point2D>();
		   
		   for(Point2D temp:set)
			   if(rect.contains(temp))
				   list_range.add(temp);
		   
		   return list_range;
		   
	   }
   	   
	  public Point2D nearest(Point2D p){
		   // a nearest neighbor in the set to p; null if set is empty
		  
		  double nearest_point=1;
		  double temp_value=-1;
		  Point2D min=null;
		  
		  for(Point2D temp:set){
		    temp_value=p.distanceTo(temp);
		    
		    if(temp_value<nearest_point){
			  nearest_point=temp_value;
			  min=temp;
		    }
		  }
		  
		  return min;
   		   
	   }
	   
	   public static void main(String[] args) throws Exception {
		   PointSET p=new PointSET();
		   Point2D a=new Point2D(0.5, 0.5);
		   Point2D b=new Point2D(0.25, 0.35);
		   Point2D c=new Point2D(0.75, 0.85);
		   Point2D d=new Point2D(0.1, 0.1);
		   Point2D min;
		   //RectHV r=new RectHV(0.5,0.5, 1,1);
		   //List<Point2D> list=new LinkedList<Point2D>();
		   
		   //System.out.println(p.isEmpty());
		   p.insert(a);
		   p.insert(b);
		   p.insert(c);
		   //System.out.println(p.isEmpty());
		   p.draw();
		   //System.out.println(p.contains(b));
		   //System.out.println(p.contains(c));
		   //System.out.println(p.size());
		   
		   /*
		   list=(List<Point2D>) p.range(r);
		   
		   for(Point2D temp:list)
			  System.out.println(temp);
		   */
		   min=p.nearest(d);
		   System.out.println(min);
		   
	   }

	
}
