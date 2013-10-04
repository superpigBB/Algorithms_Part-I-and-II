import java.util.LinkedList;
import java.util.List;


public class KdTree {
	   private List<Point2D> list_range;   
	
	   private static class Node {
		   private Point2D p;      // the point
		   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		   private Node lb;        // the left/bottom subtree
		   private Node rt;        // the right/top subtree
		   
		   private boolean levelOdd;  //veri true or hori false 
		   
		   public Node(Point2D new_p, RectHV new_rect, boolean new_levelOdd){
			   //construct node levelodd true is veri, false is hori. 
			   this.p=new_p;
			   this.levelOdd=new_levelOdd;
			   this.rect=new_rect;
		   }
		   
		}
	   
	   private int size;
	   private Node root=null;
	   
	   public KdTree(){
		   // construct an empty set of points
		   size=0;
		   root=null;
		   
	   }
	   public boolean isEmpty(){
		   // is the set empty?
		   return root==null;
	   }
	   public int size(){
		   // number of points in the set
		   return size;
	   }
	   public void insert(Point2D p){
		   // add the point p to the set (if it is not already in the set)
		   Node temp;
		   Node parent;
		   if(this.contains(p)==false){
			   size++;  // add a node
			   if(root==null){
				   root=new Node(p,new RectHV(p.x(),0,p.x(),1),true); //root is veri
			   }
			   else{
				   
					   if(root.p.x()>p.x()){ //root compare x
						   parent=find_empty(root.lb,root,p);
						   if(parent.levelOdd==true){//veri  compare x
							   
						       if(parent.p.x()>p.x()){
						    	   
						    	   temp=new Node(p,new RectHV(0, p.y(), parent.rect.xmin(), p.y()),false); //hori
						    	   parent.lb=temp;}
						       else{
						    	   temp=new Node(p,new RectHV(parent.rect.xmax(),p.y(),1,p.y()),false);  ///hori
						    	   parent.rt=temp;}
						   }
						   else{  //parent is hori  compare y
							   
						       if(parent.p.y()>p.y()){
						    	   temp=new Node(p,new RectHV(p.x(),0,p.x(),parent.rect.ymin()),true);  //veri
						    	   parent.lb=temp;}
						       else{
						    	   temp=new Node(p,new RectHV(p.x(),parent.rect.ymax(),p.x(),1),true);  //veri
						    	   parent.rt=temp;}
						   }
					   }
					   else{ //root.p.x()<p.x()
						   parent=find_empty(root.rt,root,p);
						   if(parent.levelOdd==true){//veri  compare x
						       if(parent.p.x()>p.x()){
						    	   temp=new Node(p,new RectHV(0, p.y(), parent.rect.xmin(), p.y()),false);  //hori
						    	   parent.lb=temp;}
						       else{
						    	   temp=new Node(p,new RectHV(parent.rect.xmax(),p.y(),1,p.y()),false);   //hori
						    	   parent.rt=temp;}
						   }
						   else{//parent is hori  compare y
							  
						       if(parent.p.y()>p.y()){
						    	   temp=new Node(p,new RectHV(p.x(),0,p.x(),parent.rect.ymin()),true);
						    	   parent.lb=temp;}
						       else{
						    	   temp=new Node(p,new RectHV(p.x(),parent.rect.ymax(),p.x(),1),true);  //veri
						    	   parent.rt=temp;
						    	   }
			             }
			   }
		   
			   }
		   }
		   
	   }
	   
	   
	   private Node find_empty(Node current, Node Parent, Point2D p){
		   if(current==null) return Parent;
			   
		   if(current.levelOdd==true) { //ver comapre x;
			   if(current.p.x()>p.x()){
				  return find_empty(current.lb,current,p);
			   }else
				   return find_empty(current.rt,current,p);
		   }
		   else{ //hor compare y
			   if(current.p.y()>p.y()){
					  return find_empty(current.lb,current,p);
				   }else
					  return find_empty(current.rt,current,p);
		   }
		
	   }
	   
	   
	   public boolean contains(Point2D p){
		   // does the set contain the point p?
		   Node temp=root;
		   boolean less;
		   if(this.isEmpty()==true) return false;
		   if(p.equals(temp.p)) return true;  
		   else
			  less= less_compare(temp, p);
			     
           return less;
	   }
	   
	   private boolean less_compare(Node root, Point2D p){
		   if(root==null) return false;
		   else{
			   
			   if(root.p.equals(p)) return true;
			   
			   if(root.levelOdd==true) { //ver comapre x;
				   if(root.p.x()>p.x()){
					   return less_compare(root.lb,p);
				   }else
					   return less_compare(root.rt,p);
			   }
			   else if(root.levelOdd==false){ //hor compare y
				   if(root.p.y()>p.y()){
					   return less_compare(root.lb,p);
				   }else
					   return less_compare(root.rt,p);
			   }
		   }
		   return false;

	   }
	   
	   
	   
	   public void draw(){
		     draw(root);
	    }

	    private void draw(final Node n) {
	        if (n == null) return;
	        StdDraw.setPenColor(StdDraw.BLACK);
	        StdDraw.setPenRadius(0.008);
	        StdDraw.point(n.p.x(), n.p.y());
	        StdDraw.setPenRadius();

	        if (n.levelOdd==true) StdDraw.setPenColor(StdDraw.BLUE); // hori
	        else StdDraw.setPenColor(StdDraw.RED); //ver
	        final RectHV r = n.rect;
	        StdDraw.line(r.xmin(), r.ymin(), r.xmax(), r.ymax());
	        draw(n.lb);
	        draw(n.rt);
	    }

	 public Iterable<Point2D> range(RectHV rect){
		   // all points in the set that are inside the rectangle
		 list_range=new LinkedList<Point2D>();
		 range_find(rect, root, (LinkedList<Point2D>) list_range);
	 
		 return list_range;
	 }
	 

	private void range_find(RectHV rect, Node p, LinkedList<Point2D> l){
		 
		 if(p==null) return;
		 else{
			  if(rect.contains(p.p)==true){
				  l.add(p.p);
			      range_find(rect,p.lb,l);
			      range_find(rect,p.rt,l);
			      //System.out.println("hh");
			  }    
			  else{
				  if(rect.intersects(p.rect)==true){
					  //System.out.println("hh");
					  range_find(rect,p.lb,l);
					  range_find(rect,p.rt,l);
				  }else{
					  if(p.levelOdd==true){
					     if(rect.xmax()<p.p.x())
						    range_find(rect,p.lb,l);
					     else
					    	range_find(rect,p.rt,l);
						  
					  }else if(p.levelOdd==false){
						  if(rect.ymax()<p.p.y())
							    range_find(rect,p.lb,l);
						     else
						    	range_find(rect,p.rt,l);
						  
					  }	 
				  }  
			  }		 
		 }
     }
	 
	 public Point2D nearest(Point2D p){  
		   // a nearest neighbor in the set to p; null if set is empty
	     Point2D min=null;
	  
		 if(root==null) return null;
		 double dis_min=2;
	     min=nearest_find(p,null,dis_min,root);
	     
	     return min;	 
     }
	 
	 
	 private Point2D nearest_find(Point2D p, Point2D min,double dis_min, Node n){
		 
		 if(n==null){ 
			 
			 return min;
		 }
		 else{
			 p.x();
			 p.x();
			 p.x();
			 p.x();
			  p.y();
			 p.y();
			 p.y();
			 p.y();
			double dis=n.p.distanceTo(p);
            if(dis<dis_min){   
               min=n.p;
               dis_min=dis;
             }
		     if(n.levelOdd==true){
			   if(p.x()<n.p.x())
				min=nearest_find(p,min,dis_min,n.lb);
			   else
			    min=nearest_find(p,min,dis_min,n.rt);		  
			 }
		     else if(n.levelOdd==false){
			   if(p.y()<n.p.y())
				min=nearest_find(p,min,dis_min,n.lb);
			   else
				min=nearest_find(p,min,dis_min,n.rt);
			 }	 
		  }
		return min;
	 }		 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		KdTree t=new KdTree();
	
		Point2D a=new Point2D(0.5, 0.5);
		Point2D b=new Point2D(0.25, 0.25);
		Point2D c=new Point2D(0.125, 0.125);
		Point2D d=new Point2D(0.0625, 0.0625);
        Point2D e=new Point2D(0.3625, 0.2625);
        Point2D nearest;
		//RectHV r=new RectHV(0.1,0.1,0.2,0.2);
		//List<Point2D> list=new LinkedList<Point2D>();
		   
	  //System.out.println(p.isEmpty());
		t.insert(a);
		t.insert(b);
		t.insert(c);
	    t.insert(d);
	  //t.insert(e);
	    
	    nearest=t.nearest(e);
	    System.out.println(nearest);
	    
	  //System.out.println(p.isEmpty());
		   
		//list=(List<Point2D>) t.range(r);
		
		//for(Point2D temp:list)
			//  System.out.println(temp);

		t.draw();
		//System.out.println(t.size());
		
		return;

	}

}
