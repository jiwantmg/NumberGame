package numbergame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import numbergame.event.MenuActionListener;
import numbergame.event.PointListener;

/**
 *
 * @author g1
 */
public class GamePanel extends JPanel{
    
    private GameArea gameArea;
    private ScorePanel scorePanel;
    
    private int point = 0;
    
    private MenuActionListener menuActionListener;
    private PointListener listener;
    public GamePanel(){
        setLayout(new BorderLayout());
        
        listener = new PointListener() {
            @Override
            public void pointGained(List<Integer> points) {
                int total_gained_point = 0;
                for(Integer p : points){
                    total_gained_point = total_gained_point + p;
                }
                
                point = point + total_gained_point;
                
                scorePanel.updateScore(Integer.toString(point));
            }

            @Override
            public void lostPoint(int loss_point) {
                point = point - loss_point;
            }
        };
        
        
        
        menuActionListener = new MenuActionListener() {
            @Override
            public void buttonClicked(String command) {
                if(command.equalsIgnoreCase("restart")){
                    gameArea.resetGame();
                }
            }
        };
                
        
        gameArea = new GameArea();
        gameArea.setPointListener(listener);
                        
        scorePanel = new ScorePanel();
        scorePanel.setActionListener(menuActionListener);
        
        
        add(gameArea,BorderLayout.CENTER);
        add(scorePanel,BorderLayout.SOUTH);
                
        
    }
 
           
}