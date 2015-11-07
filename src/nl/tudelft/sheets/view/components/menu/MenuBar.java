package nl.tudelft.sheets.view.components.menu;

import nl.tudelft.sheets.view.Application;
import nl.tudelft.sheets.view.components.table.SheetsTable;
import nl.tudelft.sheets.view.components.table.input.ColumnHeaderHighlight;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by jasperketelaar on 11/1/15.
 */
public class MenuBar extends JMenuBar {

    final Application app;

    public MenuBar(final Application app) {
        this.add(new FileMenu());
        try {
            this.add(new FormulaMenu());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.app = app;
    }

    public class FormulaMenu extends JMenu {

        private final ImageIcon formula;

        public FormulaMenu() throws URISyntaxException, IOException {
            super("Formula");
            final File file = new File(getClass().getResource("../../../resources/Formula-icon.png").toURI());
            final Image img = ImageIO.read(file).getScaledInstance(20, 20, 0);

            this.formula = new ImageIcon(img);
            add(new JMenuItem("SUM", formula));
            add(new JMenuItem("ADD", formula));
        }
    }

    public class FileMenu extends JMenu {

        public FileMenu() {
            super("File");
            add(newSheet());
            add(load());
            add(save());
        }

        public JMenuItem load() {
            final JMenuItem item = new JMenuItem("Load");
            item.addActionListener(e -> {
                final JFileChooser chooser = new JFileChooser();
                final FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "xml", "XML");
                chooser.setFileFilter(filter);
                final int val = chooser.showOpenDialog(app);
                if (val == JFileChooser.APPROVE_OPTION) {
                    final File file = chooser.getSelectedFile();

                    final SheetsTable table = new SheetsTable();
                    table.load(file.getName());
                    app.getJTabbedPane().addTab(file.getName().replace(".xml", ""), table.getScrollPane());
                }

            });

            return item;
        }

        public JMenuItem newSheet() {
            final JMenuItem item = new JMenuItem("New");
            item.addActionListener(e -> {
                final SheetsTable table = new SheetsTable();

                app.getJTabbedPane().addTab("Tab", table.getScrollPane());

            });
            return item;
        }

        public JMenuItem save() {
            final JMenuItem item = new JMenuItem("Save");
            return item;
        }
    }

    public class EditMenu extends JMenu {

        public EditMenu() {

        }

    }
}
