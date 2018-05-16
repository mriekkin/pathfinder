package pathfinder.gui.preferences;

import javax.swing.JComponent;

/**
 * Allows editing of a set of user related preferences. Implementations of this
 * interface do not define a "stand-alone" GUI, but rather a component (usually
 * a <code>JPanel</code>) which can be used by the caller in any way they want.
 * One can use this component, for instance, inside a <code>JDialog</code> to
 * create a dialog for user preferences. Another way to use this class would be
 * to place a set of <code>PreferencesEditor</code> objects in a
 * <code>JTabbedPane</code>, one per pane.
 * <p>
 * Based on <a href="http://www.javapractices.com/topic/TopicAction.do?Id=154">Preferences dialogs</a>.
 */
public interface PreferencesEditor {

    JComponent getUI();

    String getTitle();

    void savePreferences();

    void matchGuiToDefaultPreferences();

}
