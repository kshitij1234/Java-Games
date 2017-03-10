package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Snake extends JPanel implements ActionListener,KeyListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon titleImage;
	
	int snakeXLength[]=new int[750];
	int snakeYLength[]=new int[750];
	int length=3;
	int score=0;
	
	boolean left=false;
	boolean right=false;
	boolean up=false;
	boolean down=false;
	
	int enemyXPos[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    
    int enemyYPos[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	ImageIcon enemy;
	
	Random random=new Random();
	boolean flag=true;
	
	int moves=0;
	
	ImageIcon leftmouth,rightmouth,upmouth,downmouth;
	
	Timer timer ;					//Used for scheduling the game
    int delay=100;
    
    int xpos=random.nextInt(34);	
    int ypos=random.nextInt(23);		
    
    ImageIcon snakeimage;
    
    String location="src/snake/images/";
    
    public Snake()
    {
    //	System.out.println("inside cons");
        addKeyListener(this);	
        setFocusable(true);	
        setFocusTraversalKeysEnabled(false);
        
        timer=new Timer(delay,this);
        timer.start();
    }
    
    public void paint(Graphics g)
    {
    	if(moves==0)			//What to do if game just begins
        {			
            snakeXLength[2]=50;
            snakeXLength[1]=75;
            snakeXLength[0]=100;
            
            snakeYLength[2]=100;
            snakeYLength[1]=100;
            snakeYLength[0]=100;
            
        }
    	
    	//draw titleImage border
        titleImage=new ImageIcon(location+"snaketitle.jpg");         //draw the titleImage
        titleImage.paintIcon(this, g, 25, 11);
        
        //Now draw border for gaming area
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 557);
        
        //let's set background for gaming area 
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);
        
		//draw scores you triumphed
        g.setColor(Color.white);    
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("SCORE : "+score, 780, 30);
        
		//draw length of our snake
        g.setColor(Color.white);    
        g.setFont(new Font("arial",Font.BOLD,14));
        g.drawString("LENGTH : "+length, 780, 50);
        
		//Initial setting of mouth of snake (towards right)
        rightmouth=new ImageIcon(location+"rightmouth.png");
        rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        
      //Now we've to change direction of mouth as time proceeds 
        for(int a=0;a<length;a++)
        {
            if(a==0&&right)
            {
                rightmouth=new ImageIcon(location+"rightmouth.png");
                rightmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
            if(a==0&&left)
            {
                leftmouth=new ImageIcon(location+"leftmouth.png");
                leftmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
            if(a==0&&up)
            {
                upmouth=new ImageIcon(location+"upmouth.png");
                upmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
            if(a==0&&down)
            {
                downmouth=new ImageIcon(location+"downmouth.png");
                downmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
            if(a!=0)
            {
                snakeimage=new ImageIcon(location+"snakeimage.png");
                snakeimage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
        }
        
        enemy=new ImageIcon(location+"enemy.png");
        
        if(enemyXPos[xpos]==snakeXLength[0] && enemyYPos[ypos]==snakeYLength[0])
        {	
            length++;
            score++;
            
            xpos=random.nextInt(34);	//we've to generate next cheese's co-ordinates
            ypos=random.nextInt(23);
        }
        enemy.paintIcon(this, g, enemyXPos[xpos], enemyYPos[ypos]);
        
        //when the snake dies
        for(int b=1;b<length;b++)
        {
            if(snakeXLength[b]==snakeXLength[0]&&snakeYLength[0]==snakeYLength[b])
            {
                right=left=up=down=false;
                flag=true;
                g.setColor(Color.red);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("GAME OVER!",300,300);
                
                
                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,25));
                g.drawString("PRESS KEY(OR SPACE) TO RESTART",260,400);
            }
        }
        g.dispose();			//dispose the graphics
        
      //  System.out.println("painted");
    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
		try
        {
            if(e.getKeyCode()==KeyEvent.VK_SPACE)
            {
			// if one presses spacebar, we've to restart game
                moves=score=0;
                length=3;
                repaint();
            }
            
            if(!left&&!right&&!up&&!down&&flag)
            {
                moves=score=0;
                length=3;
                repaint();
            }
           // else
            { 	
            if(e.getKeyCode()== KeyEvent.VK_RIGHT)
            {
                moves++;
                right=true;		

                if(!left)
                {
                    right=true;
                }

                else
                {
                    left=true;
                    right=false;
                }

                up=down=false;
            }
			
			
            if(e.getKeyCode()== KeyEvent.VK_LEFT)
            {
                moves++;
                left=true;

                if(!right)
                {
                    left=true;
                }

                else
                {
                    right=true;
                    left=false;
                }

                up=down=false;
            }

            if(e.getKeyCode()== KeyEvent.VK_UP)
            {
                moves++;
                up=true;

                if(!down)
                {
                    up=true;
                }

                else
                {
                    down=true;
                    up=false;
                }

                left=right=false;
            }

            if(e.getKeyCode()== KeyEvent.VK_DOWN)
            {
                moves++;
                down=true;

                if(!up)
                {
                    down=true;
                }

                else
                {
                    up=true;
                    down=false;
                }

                left=right=false;
            }
            }
        }catch(Exception ex){
        	System.out.println("error");
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("action");
		try
        {
			//System.out.print("H");
            timer.start();
            
            if(right)
            {
                for(int r=length-1;r>=0;r--)
                {
                    snakeYLength[r+1]=snakeYLength[r];
                }
                
                for(int r=length;r>=0;r--)
                {
                    if(r==0)
                    {
                        snakeXLength[r]+=25;
                    }
                    else
                    {
                        snakeXLength[r]=snakeXLength[r-1];
                    }
                    
                    if(snakeXLength[r]>850)
                    {
                        snakeXLength[r]=25;
                    }
                    
                    repaint();
                }
            }
            
            if(up)
            {
                for(int r=length-1;r>=0;r--)
                {
                    snakeXLength[r+1]=snakeXLength[r];
                }
                
                for(int r=length;r>=0;r--)
                {
                    if(r==0)
                    {
                        snakeYLength[r]-=25;
                    }
                    else
                    {
                        snakeYLength[r]=snakeYLength[r-1];
                    }
                    
                    if(snakeYLength[r]<75)
                    {
                        snakeYLength[r]=625;
                    }
                    
                    repaint();
                }
            }
            
            if(down)
            {
                for(int r=length-1;r>=0;r--)
                {
                    snakeXLength[r+1]=snakeXLength[r];
                }
                
                for(int r=length;r>=0;r--)
                {
                    if(r==0)
                    {
                        snakeYLength[r]+=25;
                    }
                    else
                    {
                        snakeYLength[r]=snakeYLength[r-1];
                    }
                    
                    if(snakeYLength[r]>625)
                    {
                        snakeYLength[r]=75;
                    }
                    
                    repaint();
                }
            }
            
            if(left)
            {
                for(int r=length-1;r>=0;r--)
                {
                    snakeYLength[r+1]=snakeYLength[r];
                }
                
                for(int r=length;r>=0;r--)
                {
                    if(r==0)
                    {
                        snakeXLength[r]-=25;
                    }
                    else
                    {
                        snakeXLength[r]=snakeXLength[r-1];
                    }
                    
                    if(snakeXLength[r]<25)
                    {
                        snakeXLength[r]=850;
                    }
                    
                    repaint();
                }
            }
        }
        catch(Exception exp)
        {
            System.out.println("Error");
        }
		
	}
	
}
