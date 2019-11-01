package teamProject_Projects;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument{
    
    @Override
    public void insertString(int offset, String str, AttributeSet attribute)
            throws BadLocationException {
        if (str == null) {
                return;
            } 
        else {
            String newValue;
            int length = getLength();
            if (length == 0) {
                newValue = str;
            } else {
                String currentContent = getText(0, length);
                StringBuffer currentBuffer = new StringBuffer(currentContent);
                currentBuffer.insert(offset, str);
                newValue = currentBuffer.toString();
            }
            
            try {
                Integer.parseInt(newValue);
                super.insertString(offset, str, attribute);
            } catch (NumberFormatException exception) {
                //System.out.println("숫자를 입력하세요");
                JOptionPane.showMessageDialog(null, "8자리의 숫자만 입력하세요\n" +"예) 20191231");
            }
        }
    }
} 

//[출처] Swing JTextField 숫자만 입력 받기|작성자 체르니
