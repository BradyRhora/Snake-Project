class Food{
 int x = 31;
 int y = 31;
 int size = 20;
 
 Food(){
   
   while (true){
   boolean collide = false;
   while(x%30!=0) x =  (int)random(width);
   while(y%30!=0) y = (int)random(height-30)+30;
   
   for(int i = 0; i < s.size; i++)
   {
     if (x==s.x[i] && y==s.y[i]) {
       collide = true;
       x=31;
       y=31;
       break;
     }
   }
   
   if (!collide) break;
   }
   
 }
 
 void display()
 {
   fill(255,0,0);
   rect(x+5,y+5,size,size);
 }
}