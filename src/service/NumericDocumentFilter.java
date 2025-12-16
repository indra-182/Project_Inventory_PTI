package service;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;

        String newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                .insert(offset, string)
                .toString();

        if (newText.isEmpty() || newText.matches("\\d+")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text == null) return;

        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(oldText)
                .replace(offset, offset + length, text)
                .toString();

        if (newText.isEmpty() || newText.matches("\\d+")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(oldText)
                .delete(offset, offset + length)
                .toString();

        if (newText.isEmpty() || newText.matches("\\d+")) {
            super.remove(fb, offset, length);
        }
    }
}
