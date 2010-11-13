package net.sourceforge.myjorganizer.gui;

import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;

public class NullableFormattedTextField extends JFormattedTextField {
    /**
     * 
     */
    private static final long serialVersionUID = 8773097791687695787L;

    public NullableFormattedTextField(Object object) {
        super(object);
    }

    protected void processFocusEvent(FocusEvent e) {
        if (e.getID() == FocusEvent.FOCUS_LOST) {
            if (getText().length() == 0)
                setValue(null);
        }
        super.processFocusEvent(e);
    }
}
