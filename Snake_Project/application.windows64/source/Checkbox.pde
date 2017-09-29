class Checkbox
{
 int x,y;
 int size = 15;
 boolean checked = false;
 String label = null;
 
 Checkbox(int X, int Y)
 {
   x = X;
   y = Y;
 }
 
 Checkbox(int X, int Y, String lbl)
 {
   x = X;
   y = Y;
   label = lbl;
 }
 
 void click(){
  checked = !checked; 
 }
 
 void display(){
   stroke(0);
   fill(240);
   rect(x,y,size,size,5,5,5,5);
   textSize(size);
   char symbol;
   if (checked) symbol = '✓';
   else symbol = ' ';
   fill(0);
   text(symbol,x+2,y+size);
   
   if (label != null){
     fill(0);
     float txtWidth = textWidth(label);
     text(label,x-txtWidth-5,y+size);
   }
 }
}