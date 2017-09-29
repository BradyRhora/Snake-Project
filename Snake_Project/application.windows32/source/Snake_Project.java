import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Snake_Project extends PApplet {

Snake s;
Food f;
int points = 0;
int SPEED = 5; // 1-10

public void setup()
{
  
  s = new Snake();
  f = new Food();
}

int backCounter = 0;
public void draw()
{
  background(0,0,255);
  
  s.update();
  s.display();
  f.display();
  
  
  //menu
  fill(200);
  rect(0,0,width,30);
  
  
  
  fill(0);
  textSize(20);
  text("POINTS: " + points ,0,20);
  
  if (s.dead) {
   textSize(50);
   text("YOU DED. "+ points +" POINTS.\nDO BETTER NEXT TIME.\nPRESS ENTER TO RESTART",(width/2)-300,(height/2)-75);
  }
  
}

public void mousePressed()
{
  
}

public void keyPressed(){
  if (keyCode == UP) s.facing[0] = direction.up;
  else if (keyCode == DOWN) s.facing[0] = direction.down;
  else if (keyCode == LEFT) s.facing[0] = direction.left;
  else if (keyCode == RIGHT) s.facing[0] = direction.right;
  else if (s.dead && keyCode == ENTER) {
   s = new Snake();
   f = new Food();
   points = 0;
  }
}

enum direction{
 left,
 right,
 up,
 down
}
class Button{
 int x,y;
 int w,h;
 
   
 
}
class Food{
 int x = 31;
 int y = 31;
 int size = 20;
 
 Food(){
   while(x%30!=0) x =  (int)random(width);
   while(y%30!=0) y = (int)random(height-30)+30;
 }
 
 public void display()
 {
   fill(255,0,0);
   rect(x+5,y+5,size,size);
 }
}
class Snake{
  int size = 1;
  int[] x;
  int[] y;
  direction[] facing;
  int counter = 0;
  boolean dead = false;
  
  Snake(){
    x = new int[870];
    y = new int[870];
    
    facing = new direction[870];
    facing[0] = direction.right;
    
    x[0] = 330;
    y[0] = 330;
  }
  
  public void display(){
    int holeX=0,holeY=0;
    int holeTrans = 10;
    int tl=0,tr=0,br=0,bl=0;
    int rad = 15;
    
    if (facing[0] == direction.left)
    {
      holeX= -holeTrans;
      tl=rad;
      bl=rad;
    }
    else if (facing[0] == direction.right)
    {
      holeX=holeTrans;
      tr=rad;
      br=rad;
    }
    else if (facing[0] == direction.up)
    {
      holeY=-holeTrans;
      tl=rad;
      tr=rad;
    }
    else if (facing[0] == direction.down)
    {
      holeY=holeTrans;
      bl=rad;
      br=rad;
    }
    
    
    
    
    int ball1x=0,ball1y=0,ball2x=0,ball2y=0;
    
    if (facing[size-1] == direction.left || facing[size-1] == direction.right )
    {
      ball1x = x[size-1]+15;
      ball1y = y[size-1]+45;
      
      ball2x = ball1x;
      ball2y = y[size-1]-15;
    }
    else if (facing[size-1] == direction.up || facing[size-1] == direction.down)
    {
      ball1x = x[size-1]-15;
      ball1y = y[size-1]+15;
      
      ball2x = x[size-1]+45;
      ball2y = ball1y;
    }
    
    for (int i = 0; i < size; i++)
    {
      fill(255,228,196);
      if (dead) fill (255,0,0);
      if (i == 0)rect(x[i],y[i],30,30,tl,tr,br,bl); 
      else rect(x[i],y[i],30,30);
    }
    
    ellipse(ball1x,ball1y,30,30);
    ellipse(ball2x,ball2y,30,30);
    
    fill(0);
    ellipse(x[0]+15+holeX,y[0]+15+holeY,5,5);
  }
  
  public void update(){
    counter++;
    check();
    if (!dead){if (counter == 10-SPEED) {
      move();
      counter = 0;
    }}
  }
  
  public void check(){
   if (x[0] == f.x && y[0] == f.y){
     eat();     
   }
   
   for (int i = 1; i < size; i++)
   {
    if (x[0] == x[i] && y[0] == y[i]) die();
   }
   
   if (x[0] < 0 || x[0] >= width || y[0] < 30 || y[0] >= height) die();
   
  }
  
  public void die()
  {
    dead = true;
  }
  
  public void eat(){
     points += size*5;
     size++; 
     x[size-1] = x[0]+1;
     y[size-1] = y[0];
     f = new Food(); 
     
  }
  
  public void move(){
    
    for(int i = size-1; i > 0; i--){
       x[i] = x[i-1];
       y[i] = y[i-1];
       facing[i] = facing[i-1];
     }
     
    if (facing[0] == direction.left)
    {
      x[0]-=30;
    }
    else if (facing[0] == direction.right)
    {
      x[0]+=30;
    }
    else if (facing[0] == direction.up)
    {
      y[0]-=30;
    }
    else if (facing[0] == direction.down)
    {
      y[0]+=30;
    }
    
    
  }
  
}
  public void settings() {  size(900,900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Snake_Project" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
