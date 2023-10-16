import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Ventana {

    public Ventana() {
        // lo primero es crear el JFrame
        JFrame frame = new JFrame();

        // ------------- ETIQUETAS
        JLabel label1 = new JLabel("Unidad: ");
        JLabel label2 = new JLabel("Directorio: ");
        JLabel label3 = new JLabel("Archivos: ");

        // ------------- COMBO BOX
        // este es el ComboBox, le mandamos un objeto Explorador, que por defecto empezará en C:\
        Explorador raiz = new Explorador("C:\\");
        String[] unidades = raiz.getRootList();
        JComboBox<String> cmbBox = new JComboBox<>(unidades);

        // ------------- JSPINNER
        // el JSpinner comienza en los subdirectorios de C:\
        SpinnerListModel spinnerListModel = new SpinnerListModel(raiz.listarDirectorios());
        JSpinner pDirectorio = new JSpinner(spinnerListModel);

        // ------------- JLIST
        // este es el listado de documentos que hay en el directorio seleccionado en el JSpinner
        JList<String> jList = new JList<>(new Explorador(unidades[0]).listarArchivos());

        cmbBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) cmbBox.getSelectedItem();
                Explorador nuevaRaiz = new Explorador(selectedItem);
                // Cambiar el modelo del Spinner en función de la selección
                SpinnerListModel nuevoModelo = new SpinnerListModel(nuevaRaiz.listarDirectorios());
                pDirectorio.setModel(nuevoModelo);
            }
        });

        pDirectorio.addChangeListener(e -> {


        });


        // luego hay que crear el panel que tendrá el contenido
        JPanel panel = new JPanel();
        // este es el borde de la ventana
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        // este es el layout de la ventana, tiene tres filas y dos columnas
        panel.setLayout(new GridLayout(3, 2));

        panel.add(label1);
        panel.add(cmbBox);
        panel.add(label2);
        panel.add(pDirectorio);
        panel.add(label3);
        panel.add(jList);


        // ------------ Estos son unos parámetros por defecto de buenas prácticas
        frame.setSize(400, 400);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // para que se cierre
        frame.setTitle("Navegador de archivos: "); // el título de la ventana
        frame.pack(); // con este método, "junta" todos los elementos
        frame.setResizable(false); // así no se podrá redimensionar
        frame.setVisible(true); // para que se vea la ventana


    }


}
