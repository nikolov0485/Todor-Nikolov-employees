import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class GUI {
    JFrame frame;
    JPanel panel;
    JButton button;

    JTable table;

    DefaultTableModel tableModel;

    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Upload CSV File");
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 25, 50));
        panel.setLayout(new GridLayout());
        panel.add(button);
        panel.add(table);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Common Projects");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".csv") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "CSV Files (*.csv)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                HashMap<EmployeePair, Long> finalPair = Main.longestPair(selectedFile);
                Vector<String> empVector = new Vector<>();
                for (var i : finalPair.keySet()) {
                    empVector.add(String.valueOf(i.getEmp1().getId()));
                    empVector.add(String.valueOf(i.getEmp2().getId()));
                    empVector.add(String.valueOf(i.getEmp1().getProjectId()));
                    empVector.add(String.valueOf(finalPair.get(i)));
                }
                tableModel.addColumn("Emp1 ID");
                tableModel.addColumn("Emp2 ID");
                tableModel.addColumn("Project ID");
                tableModel.addColumn("Days worked");
                tableModel.addRow(empVector);


            }
        });
    }


    public static void main(String[] args) {
        new GUI();
    }
}
