import java.awt.Color;
import java.util.HashMap;

//import 

public class SeamCarver {
   private Picture pic;	
   private int width;
   private int height;
   private double energy;
   private  HashMap<Double, Integer[]> HM;
   
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
	   
	   if(x==(width-1)||x==0||y==(height-1)||y==0){
		 return 195075.00;
	   }
	   
	   c0=pic.get(x+1, y);
	   c1=pic.get(x-1, y);
	   
	   rx=Math.abs(c0.getRed()-c1.getRed());
	   gx=Math.abs(c0.getGreen()-c1.getGreen());
	   bx=Math.abs(c0.getBlue()-c1.getBlue());
	 
	   pow_diffx=Math.pow(rx,2)+Math.pow(gx,2)+Math.pow(bx,2);


	   //Calculate diffy
	   c2=pic.get(x, y+1);
	   c3=pic.get(x, y-1);
	   
	   ry=Math.abs(c2.getRed()-c3.getRed());
	   gy=Math.abs(c2.getGreen()-c3.getGreen());
	   by=Math.abs(c2.getBlue()-c3.getBlue());
	   
	   pow_diffy=Math.pow(ry,2)+Math.pow(gy,2)+Math.pow(by,2);
	   energy=pow_diffx+pow_diffy;

	   return energy;
   }
   
   
   public int[] findVerticalSeam(){
	   // sequence of indices for horizontal seam
	   Integer a[]=new Integer[height];
	   int result[]=new int[height];
	   HM = new HashMap<Double, Integer[]>();   
	   double sum=0;
	   double min_sum=Double.MAX_VALUE;
	   double energy[][]= new double[width][height];
	   for(int i=0;i<height;i++){
		   for(int j=0;j<width;j++){
			   energy[j][i]=energy(j,i);
		   }
	   }
	   
	   //brute force
       for(int w=0; w<width;w++){
           sum=energy[w][0];
           a[0]=w;
           int y=0;
           int x=w;
           while(y<height-1){
             
        	   if(x==0){  //boarder case
            	 if(energy[x][y+1]<=energy[x+1][y+1]){
            		 //x=x;
            		 y=y+1;
            		 a[y]=x;
            		 sum=sum+energy[x][y];
            		
            	 }else{
            		 x=x+1;
            		 y=y+1;
            		 a[y]=x;
            		 sum=sum+energy[x][y];
            		 
            	 }
               }else if(x==width-1){ //boarder case
            	 if(energy[x][y+1]<=energy[x-1][y+1]){
            		 //x=x;
            		 y=y+1;
            		 a[y]=x;
            		 sum=sum+energy[x][y];
            		 
            	 }else{
            		 x=x-1;
            		 y=y+1;
            		 a[y]=x;
            		 sum=sum+energy[x][y];
            	 }
             }else{
            	//System.out.println("x="+x+"  y="+y);
            	 if(energy[x][y+1]<=energy[x-1][y+1]){
            		 if(energy[x][y+1]<=energy[x+1][y+1]){
            			 //x=x;
                		 y=y+1;
                		 a[y]=x;
                		 sum=sum+energy[x][y];
                		
            		 }else{
            			 x=x+1;
            		     y=y+1;
            		     a[y]=x;
            		     sum=sum+energy[x][y];
            		     
            		 }
            		
            	 }else{
            		 if(energy[x-1][y+1]<=energy[x+1][y+1]){
            			 x=x-1;
                		 y=y+1;
                		 a[y]=x;
                		 
                		 sum=sum+energy[x][y];
                		 
            		 }else{
            			 x=x+1;
            		     y=y+1;
            		     a[y]=x;
            		     sum=sum+energy[x][y];
            		     
            		 }
            	 } 
             }
        	 
              
           }
           
           if(sum<min_sum) min_sum=sum;
           HM.put(sum, a); 	   
       }
	   
	   a=HM.get(min_sum);
	   
	   for(int z=0; z<height-1; z++){
		   //System.out.println(a[z]);
		   result[z]=(int) a[z];
	   }
		   
	   
	   return result;
   }
   
   
   
   /*
   public int[] findHorizontalSeam(){
	   // sequence of indices for vertical seam
	   int a[]=new int[5];
	   pic.
	   
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
	   System.out.println(sc.energy(1,1));
	   //sc.pic.show();
	   //sc.findVerticalSeam();
   }

}
