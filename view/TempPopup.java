package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempPopup {

	public static void ShowTempPopup(String message ) {
        JOptionPane pane = new JOptionPane(message,
                JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = pane.createDialog(null, "Information");

        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
        dialog.dispose();
	}
  
}