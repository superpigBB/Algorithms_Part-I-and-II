import java.awt.Color;

//import 

public class SeamCarver {
   private Picture pic;	
   private int width;
   private int height;
   private double energy;
	
   public SeamCarver(Picture picture){
	   this.pic=new Picture(picture);
	   width=pic.width();
	   height=pic.height();
	   
   }
  
   public Picture picture(){ 
   // current picture
	   return pic;
   }
   
   public int width(){
   // width of current picture
	   return width;
   }
   
   public int height(){   
   // height of current picture
	   return height;
   }
   
   public double energy(int x, int y){       
   // energy of pixel at column x and row 
	   Color c0,c1,c2,c3;
	   int rx,gx,bx,ry,gy,by;
	   double pow_diffx,pow_diffy;
	   //Calculate diffx
	   c0=pic.get(x+1, y);
	   c1=pic.get(x-1, y);
	   
	   c0.getRed();
	   c0.getGreen();
	   c0.getBlue();
	   
	   c1.getRed();
	   c1.getGreen();
	   c1.getBlue();
	  
	   rx=Math.abs(c0.getRed()-c1.getRed());
	   gx=Math.abs(c0.getGreen()-c1.getGreen());
	   bx=Math.abs(c0.getBlue()-c1.getBlue());
	   
	   pow_diffx=Math.pow(2,rx)+Math.pow(2,gx)+Math.pow(2,bx);
	   
	   //Calculate diffy
	   c2=pic.get(x, y+1);
	   c3=pic.get(x, y+1);
	   
	   c2.getRed();
	   c2.getGreen();
	   c2.getBlue();
	   
	   c3.getRed();
	   c3.getGreen();
	   c3.getBlue();
	   
	   ry=Math.abs(c2.getRed()-c3.getRed());
	   gy=Math.abs(c2.getGreen()-c3.getGreen());
	   by=Math.abs(c2.getBlue()-c3.getBlue());
	   
	   pow_diffy=Math.pow(2,ry)+Math.pow(2,gy)+Math.pow(2,by);
	   
	   energy=pow_diffx+pow_diffy;
	   
	   //System.out.println(c.getRed());
	   return energy;
   }
   
   /*
   public int[] findHorizontalSeam(){
	   // sequence of indices for horizontal seam
   }
   
   public int[] findVerticalSeam() {
	   // sequence of indices for vertical seam
   }
   
   public void removeHorizontalSeam(int[] a){
	   // remove horizontal seam from picture
   }
   
   public void removeVerticalSeam(int[] a){
	   // remove vertical seam from picture
   }
   */
   public static void main(String[] args) {
		// TODO Auto-generated method stub
	   
	   Picture inputImg = new Picture(args[0]);
	   SeamCarver sc=new  SeamCarver(inputImg);
	   System.out.println(sc.height);
	   System.out.println(sc.width);
	   System.out.println(sc.energy(100,100));
	   sc.pic.show();

   }

}
