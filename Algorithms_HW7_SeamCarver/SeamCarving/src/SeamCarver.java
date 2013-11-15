import java.awt.Color;

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
	   if (x<0||y<0||x>width-1||y>height-1)  
   		throw new IndexOutOfBoundsException();
	   
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
	   int a[]=new int[height];
	   int b[]=new int[height];
	     
	   double sum=0;
	   double min_sum=Double.POSITIVE_INFINITY;;
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

           if(sum<min_sum) 
           {
        	   min_sum=sum;
        	   //b=new int[height]; shallow copy is wrong!!!
        	   System.arraycopy( a, 0, b, 0, a.length );  //USE DEEP COPY!
           }
       }
	   
	   return b;
   }
   
   
   
   
   public int[] findHorizontalSeam(){
	   // sequence of indices for vertical seam
	  int a[]=new int[width];
	   int b[]=new int[width];
	     
	   double sum=0;
	   double min_sum=Double.POSITIVE_INFINITY;;
	   double energy[][]= new double[height][width];
	   for(int i=0;i<width;i++){
		   for(int j=0;j<height;j++){
			   energy[j][i]=energy(i,j);
		   }
	   }
	   
	   //brute force
       for(int w=0; w<height;w++){
           sum=energy[w][0];
           a[0]=w;
           int y=0;
           int x=w;
           while(y<width-1){
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
               }else if(x==height-1){ //boarder case
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

           if(sum<min_sum) 
           {
        	   min_sum=sum;
        	   //b=new int[height]; shallow copy is wrong!!!
        	   System.arraycopy( a, 0, b, 0, a.length );  //USE DEEP COPY!
           }
       }
	   
	   return b;
   }
   
   
   
   public void removeHorizontalSeam(int[] a){
	   // remove horizontal seam from picture
	   if(a.length!=width) throw new IndexOutOfBoundsException();
	   if(width<=1) throw new RuntimeException();
	   
	   
	   Color[][] color_dst=new Color[width][height-1];
	  
       Color[][] color_src=new Color[width][height];
	   
	   for(int i=0;i<height;i++){
		   for(int j=0;j<width;j++){
			  
			   color_src[j][i]=pic.get(j, i);
		   }
	   }
	   
	   for(int x=0;x<width; x++){
		   int y=0;
		   int y1=0;
		   while(y1<height){
		       if(y1!=a[x]){	   
		       
		       color_dst[x][y]=color_src[x][y1];
	           y++;
	           y1++;
	           }else{
	        	   //Color c=Color.RED;
	        	   //pic.set(x, y, c);
	        	   y1++;
	           }
	        
		   }
	   }
	   
	  
      pic=new Picture(width, height-1);
	     
	   for (int i = 0; i <height-1 ; i++)
           for (int j = 0; j < width; j++)
           {
               pic.set(j, i, color_dst[j][i]);
           }

	   
	   height=height-1;
   }
   
   
   public void removeVerticalSeam(int[] a){
	   // remove vertical seam from picture
	   
	   if(a.length!=height) throw new IndexOutOfBoundsException();
	   if(height<=1) throw new RuntimeException();
	   
	  
	   Color[][] color_dst=new Color[width-1][height];
	   
       Color[][] color_src=new Color[width][height];
	   
	   for(int i=0;i<height;i++){
		   for(int j=0;j<width;j++){
			  
			   color_src[j][i]=pic.get(j, i);
		   }
	   }
	   
	   for(int y=0;y<height; y++){
		   int x=0;
		   int x1=0;
		   while(x1<width){
		       if(x1!=a[y]){	   
		       color_dst[x][y]=color_src[x1][y];
	           x++;
	           x1++;
	           }else{
	        	   //Color c=Color.green;
	        	   //pic.set(x, y, c);
	        	   x1++;
	           }
	        
		   }
	   }
	   //return energy_dst;
	   
	   pic=new Picture(width-1, height);
	   
	   
	   for (int i = 0; i < height; i++)
           for (int j = 0; j < width-1; j++)
           {
               pic.set(j, i,color_dst[j][i]);
           }
	   
	   width=width-1;
	   
   }
   
   public static void main(String[] args) {
	   
	   Picture inputImg = new Picture(args[0]);
	   SeamCarver sc=new  SeamCarver(inputImg);
	   System.out.println(sc.height);
	   System.out.println(sc.width);
	   //System.out.println(sc.energy(1,1));
	   //sc.pic.show();
	   //sc.findVerticalSeam();
	   //sc.findHorizontalSeam();
   }

}
