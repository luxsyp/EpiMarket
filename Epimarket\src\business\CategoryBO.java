/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.CategoryDAO;
import ORM.model.Category;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class CategoryBO
{
    private CategoryDAO categoryDAO;
    
    public List<Category>    find()
    {
       return (this.categoryDAO.find(null)); 
    }
    public boolean                  save(Category o)
    {
        this.categoryDAO.save(o);
        return (true);
    }
    public boolean                  update(Category o)
    {
        this.categoryDAO.update(o);
        return (true);
    }
    public boolean                  delete(Category o)
    {
        this.categoryDAO.delete(o);
        return (true);
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
}
