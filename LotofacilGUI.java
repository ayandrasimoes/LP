import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LotofacilGUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menuAposta;
    private JMenuItem itemAposta0a100, itemApostaAToZ, itemApostaParOuImpar;
    private JLabel labelAposta;
    private JTextField campoAposta;
    private JButton botaoApostar;
    private JTextArea areaResultado;

    private int tipoAposta;
    private Random random;

    public LotofacilGUI() {
        setTitle("Lotofácil");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(220, 182, 255));

        random = new Random();

        // Menu
        menuBar = new JMenuBar();
        menuAposta = new JMenu("Tipo de Aposta");
        itemAposta0a100 = new JMenuItem("De 0 a 100");
        itemApostaAToZ = new JMenuItem("De A à Z");
        itemApostaParOuImpar = new JMenuItem("Par ou Ímpar");
        itemAposta0a100.addActionListener(this);
        itemApostaAToZ.addActionListener(this);
        itemApostaParOuImpar.addActionListener(this);
        menuAposta.add(itemAposta0a100);
        menuAposta.add(itemApostaAToZ);
        menuAposta.add(itemApostaParOuImpar);
        menuBar.add(menuAposta);
        setJMenuBar(menuBar);

        labelAposta = new JLabel("Escolha o tipo de aposta no menu acima.");
        campoAposta = new JTextField(10);
        botaoApostar = new JButton("Apostar");
        botaoApostar.addActionListener(this);
        areaResultado = new JTextArea(2, 30);
        areaResultado.setEditable(false);

        add(labelAposta);
        add(campoAposta);
        add(botaoApostar);
        add(areaResultado);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemAposta0a100) {
            labelAposta.setText("Digite um número de 0 a 100:");
            tipoAposta = 1;
        } else if (e.getSource() == itemApostaAToZ) {
            labelAposta.setText("Digite uma letra de A à Z:");
            tipoAposta = 2;
        } else if (e.getSource() == itemApostaParOuImpar) {
            labelAposta.setText("Digite um número inteiro:");
            tipoAposta = 3;
        } else if (e.getSource() == botaoApostar) {
            realizarAposta();
        }
    }

    private void realizarAposta() {
        String apostaTexto = campoAposta.getText();
        try {
            switch (tipoAposta) {
                case 1:
                    int aposta1 = Integer.parseInt(apostaTexto);
                    int numeroSorteado1 = random.nextInt(101);
                    if (aposta1 == numeroSorteado1) {
                        areaResultado.setText("Você ganhou R$ 1.000,00 reais.");
                    } else {
                        areaResultado.setText("Que pena! O número sorteado foi: " + numeroSorteado1 + ".");
                    }
                    break;
                case 2:
                    char aposta2 = apostaTexto.toUpperCase().charAt(0);
                    if (aposta2 >= 'A' && aposta2 <= 'Z') {
                        char letraSorteada = (char) (random.nextInt(26) + 'A');
                        if (aposta2 == letraSorteada) {
                            areaResultado.setText("Você ganhou R$ 500,00 reais.");
                        } else {
                            areaResultado.setText("Que pena! A letra sorteada foi: " + letraSorteada + ".");
                        }
                    } else {
                        areaResultado.setText("Aposta inválida. Digite uma letra de A à Z.");
                    }
                    break;
                case 3:
                    int aposta3 = Integer.parseInt(apostaTexto);
                    int numeroSorteado3 = random.nextInt(101);
                    if (aposta3 % 2 == 0) {
                        areaResultado.setText("Você ganhou R$ 100,00 reais.");
                    } else {
                        areaResultado.setText("Que pena! O número digitado é ímpar e a premiação foi para números pares.");
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
            areaResultado.setText("Aposta inválida.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LotofacilGUI().setVisible(true);
            }
        });
    }
}
