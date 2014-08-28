/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.GameDAO;
import ORM.model.Game;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class GameBO
{
    private GameDAO gameDAO;
    
    
    public List<Game>    find()
    {
       return (this.gameDAO.find(null)); 
    }
    public boolean                  save(Game o)
    {
        this.gameDAO.save(o);
        return (true);
    }
    public boolean                  update(Game o)
    {
        this.gameDAO.update(o);
        return (true);
    }
    public boolean                  delete(Game o)
    {
        this.gameDAO.delete(o);
        return (true);
    }

    public GameDAO getGameDAO() {
        return gameDAO;
    }

    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }
    
}
