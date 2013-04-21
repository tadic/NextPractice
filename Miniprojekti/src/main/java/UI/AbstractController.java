/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarno
 */
public class AbstractController implements PropertyChangeListener {

    protected List<AbstractModel> registeredModels = new ArrayList<AbstractModel>();
    protected List<View> registeredViews = new ArrayList<View>();

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        for (View view : registeredViews) {
            view.modelPropertyChange(evt);
        }
    }

    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(View view) {
        registeredViews.add(view);
    }

    public void removeView(View view) {
        registeredViews.remove(view);
    }
}
