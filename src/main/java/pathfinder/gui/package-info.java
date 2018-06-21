/**
 * Views such as the main window and dialogs.
 * <p>
 * The design of this application is based on the model-view pattern. In this
 * pattern the model and view are separated, as in the model-view-controller
 * pattern (MVC). However, there are no separate controllers as the controller
 * and view classes are collapsed into a single class. In this application a
 * view is either the main window or one of the dialogs.
 * <p>
 * The design of the user interface packages is also based on articles at
 * javapractices.com:
 * <ul>
 * <li>The model and view classes communicate using the observer pattern. The
 * traditional implementation of this pattern is described in
 * <a href="http://www.javapractices.com/topic/TopicAction.do?Id=156">Observers and Listeners</a>,
 * but we use a more
 * <a href="http://www.baeldung.com/java-observer-pattern">modern implementation</a>.</li>
 * <li>Menu items are attached to
 * <a href="http://www.javapractices.com/topic/TopicAction.do?Id=159">actions</a>.</li>
 * <li>Dialogs employ elements of
 * <a href="http://www.javapractices.com/topic/TopicAction.do?Id=150">standardized dialogs</a> and
 * <a href="http://www.javapractices.com/topic/TopicAction.do?Id=154">preferences dialogs</a>.</li>
 * </ul>
 *
 * @see <a href="http://www.javapractices.com/home/HomeAction.do#Swing">Java Practices: Swing</a>
 */
package pathfinder.gui;
