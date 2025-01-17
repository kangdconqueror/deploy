import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorGUI {

    public static void main(String[] args) {
        // Membuat JFrame (Window)
        JFrame frame = new JFrame("BMI Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel utama dengan padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen GUI
        JLabel heightLabel = new JLabel("Tinggi badan (cm):");
        JTextField heightField = new JTextField();

        JLabel weightLabel = new JLabel("Berat badan (kg):");
        JTextField weightField = new JTextField();

        JLabel ageLabel = new JLabel("Umur:");
        JTextField ageField = new JTextField();

        JButton calculateButton = new JButton("Hitung BMI");

        // Panel untuk hasil BMI
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Margin bawah ditambahkan
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        // Tambahkan komponen ke panel utama
        mainPanel.add(heightLabel);
        mainPanel.add(heightField);
        mainPanel.add(weightLabel);
        mainPanel.add(weightField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(calculateButton);

        // Tambahkan panel utama dan panel hasil ke frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(resultPanel, BorderLayout.SOUTH);

        // Tambahkan ActionListener untuk tombol
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ambil input dari pengguna
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    int age = Integer.parseInt(ageField.getText());

                    // Hitung BMI
                    double heightInMeters = height / 100;
                    double bmi = weight / (heightInMeters * heightInMeters);

                    // Tentukan kategori BMI
                    String category;
                    if (bmi < 18.5) {
                        category = "Berat badan kurang";
                    } else if (bmi >= 18.5 && bmi < 24.9) {
                        category = "Berat badan normal";
                    } else if (bmi >= 25 && bmi < 29.9) {
                        category = "Berat badan berlebih";
                    } else {
                        category = "Obesitas";
                    }

                    // Tampilkan hasil
                    resultLabel.setText(String.format("<html>BMI: %.2f<br>Kategori: %s</html>", bmi, category));
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Input tidak valid!");
                }
            }
        });

        // Tampilkan frame
        frame.setVisible(true);
    }
}
