package nl.tudelft.sheets.view.components.menu;

import nl.tudelft.sheets.model.data.SheetsCell;
import nl.tudelft.sheets.view.Application;
import nl.tudelft.sheets.view.components.ContentPanel;
import nl.tudelft.sheets.view.components.table.SheetsTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 11/1/15.
 */
public class MenuBar extends JMenuBar {

    final Application app;

    public MenuBar(final Application app) {
        this.add(new FileMenu());
        this.app = app;
    }

    public class FileMenu extends JMenu {

        public FileMenu() {
            super("File");
            add(newSheet());
            add(load());
        }

        public JMenuItem load() {
            final JMenuItem item = new JMenuItem("Load");
            item.addActionListener(e -> {
                final JFileChooser chooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "xml", "XML");
                chooser.setFileFilter(filter);
                final int val = chooser.showOpenDialog(app);
                if(val == JFileChooser.APPROVE_OPTION) {
                    final File file = chooser.getSelectedFile();

                    final ContentPanel panel = new ContentPanel();
                    panel.getTable().load(file.getName());

                    app.getJTabbedPane().addTab(file.getName().replace(".xml", ""), panel);
                }

            });

            return item;
        }

        public JMenuItem newSheet() {
            final JMenuItem item = new JMenuItem("New");
            item.addActionListener(e -> {
                final ContentPanel panel = new ContentPanel();
                app.getJTabbedPane().addTab("New tab", panel);
            });
            return item;
        }
    }
}
