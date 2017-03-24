package breakout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class Board {
	
	private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];
    private boolean ingame = true;

    public Board() {

        initBoard();
    }
	private void initBoard() {
		// TODO Auto-generated method stub
		
	}
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
}
