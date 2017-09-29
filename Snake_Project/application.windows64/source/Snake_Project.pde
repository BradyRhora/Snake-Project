Snake s; //<>//
Food f;
int points = 0;
int SPEED = 5; // 1-10
ArrayList<Checkbox> chkBoxes= new ArrayList<Checkbox>();
Checkbox penorBox;
boolean paused = false;

void setup()
{
  size(900,900);
  s = new Snake();
  f = new Food();
  
  penorBox = new Checkbox(width-20,5,"Penor mode");
  chkBoxes.add(penorBox);
}

int backCounter = 0;
void draw()
{
  background(0,0,255);
  
  if (!paused){
  s.update();
  s.display();
  f.display();
  } else {
   textSize(50);
   text("PAUSED.\nSPACEBAR TO UNPAUSE.",width/2-300,height/2+20);
  }
  
  //menu
  fill(200);
  rect(0,0,width,30);
  
  //menu items
  fill(0);
  textSize(20);
  text("POINTS: " + points ,0,20);
  
  for(Checkbox chk : chkBoxes)
  {
    chk.display();
  }
  
  
  
  
  //else
  if (s.dead) {
   textSize(50);
   text("YOU DED. "+ points +" POINTS.\nDO BETTER NEXT TIME.\nPRESS ENTER TO RESTART",(width/2)-300,(height/2)-75);
  }
  
}

void mousePressed()
{
  for (Checkbox chk : chkBoxes)
  {
   if (mouseX >= chk.x && mouseX <= chk.x + chk.size && mouseY >= chk.y && mouseY <= chk.y+chk.size) chk.click();
  }
}

void keyPressed(){
  if (keyCode == UP) s.facing[0] = direction.up;
  else if (keyCode == DOWN) s.facing[0] = direction.down;
  else if (keyCode == LEFT) s.facing[0] = direction.left;
  else if (keyCode == RIGHT) s.facing[0] = direction.right;
  else if (key == ' ') paused = !paused;
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