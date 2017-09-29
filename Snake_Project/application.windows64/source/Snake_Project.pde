Snake s;
Food f;
int points = 0;
int SPEED = 5; // 1-10

void setup()
{
  size(900,900);
  s = new Snake();
  f = new Food();
}

int backCounter = 0;
void draw()
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

void mousePressed()
{
  
}

void keyPressed(){
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