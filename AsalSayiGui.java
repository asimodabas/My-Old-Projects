package senktron.arttırma;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;



public class SenktronArttırma {

    static private JFrame frame;
    static JButton btn1 = new JButton("2");
    static JButton btn2 = new JButton("3");
    static JButton btn3 = new JButton("5");
    static JButton btn4 = new JButton("7");
    static JButton btn5 = new JButton("11");
    static boolean durum = true;
    public static Thread t1;
    public static Thread t2;

    static private int asal(int last) {
        
        int asalSayi = -1;
        int temp = last;
        temp++;
        while (true) {

            int temp2 = temp;

            for (int i = 2; i < temp; i++) {
                if (temp % i == 0) {
                    temp++;
                    break;
                }

            }
            if (temp == temp2) {
                asalSayi = temp;
                break;
            }
        }
        return asalSayi;
    }

    public static void main(String[] args) {

        t1 = new Thread(new Runnable() {
            public void run() {
                initialize();
            }
        });

        t1.start();
        dongu();
    }

    static void dongu() {
        
        t2 = new Thread(new Runnable() {
            public void run() {
                while (durum) {
                    islem();
                    try {

                        t2.sleep(1000);
                        
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                        
                    }
                }
            }
        });
        
        t2.start();
    }

    private static synchronized void islem() {

        // butonlar arasında veri geçişi yapılan kısım
        btn1.setText(btn2.getText());
        btn2.setText(btn3.getText());
        btn3.setText(btn4.getText());
        btn4.setText(btn5.getText());

        String btn5m = btn5.getText();
        int k = Integer.parseInt(btn5m);
        k = asal(k);
        btn5m = Integer.toString(k);
        btn5.setText(btn5m);
    }

    private static synchronized void initialize() {

        // Butonlar bu bölümde oluşturuluyor ve action eventler veriliyor.
        frame = new JFrame();
        frame.setBounds(100, 100, 661, 210);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        btn1.setBounds(59, 43, 55, 50);
        frame.getContentPane().add(btn1);
        btn1.addActionListener(new buttonListener());
        btn2.setBounds(155, 43, 55, 50);
        frame.getContentPane().add(btn2);
        btn2.addActionListener(new buttonListener());

        btn3.setBounds(255, 43, 55, 50);
        frame.getContentPane().add(btn3);
        btn3.addActionListener(new buttonListener());

        btn4.setBounds(353, 43, 55, 50);
        frame.getContentPane().add(btn4);
        btn4.addActionListener(new buttonListener());

        btn5.setBounds(451, 43, 55, 50);
        frame.getContentPane().add(btn5);
        btn5.addActionListener(new buttonListener());
        
    }

}

class buttonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SenktronArttırma.durum) {
            SenktronArttırma.durum = false;
        } else {
            SenktronArttırma.durum = true;
            SenktronArttırma.dongu();
        }
    }
}
