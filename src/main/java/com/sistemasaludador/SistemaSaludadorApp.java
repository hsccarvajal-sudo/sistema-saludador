package com.sistemasaludador;

import com.sistemasaludador.modelo.DatosSaludo;
import com.sistemasaludador.servicio.GeneradorSaludo;
import com.sistemasaludador.servicio.ValidadorEntrada;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Ventana principal del prototipo Sistema Saludador.
 */
public class SistemaSaludadorApp extends Application {

    private final ValidadorEntrada validador = new ValidadorEntrada();
    private final GeneradorSaludo generador = new GeneradorSaludo();

    private StackPane raiz;
    private VBox vistaInicio;
    private VBox vistaFormulario;
    private VBox vistaResultado;

    private TextField campoNombre;
    private TextField campoEdad;
    private ComboBox<Integer> comboHora;
    private ComboBox<String> comboMinutos;
    private ComboBox<String> comboAmPm;
    private Label etiquetaErrores;
    private Label etiquetaSaludo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        construirVistas();

        raiz = new StackPane(vistaInicio, vistaFormulario, vistaResultado);
        mostrarVista(vistaInicio);

        Scene escena = new Scene(raiz, 560, 460);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        stage.setTitle("Sistema Saludador");
        stage.setScene(escena);
        stage.setMinWidth(480);
        stage.setMinHeight(400);
        stage.show();
    }

    private void construirVistas() {
        construirInicio();
        construirFormulario();
        construirResultado();
    }

    private void construirInicio() {
        Label titulo = new Label("Sistema Saludador");
        titulo.getStyleClass().add("titulo");

        Label descripcion = new Label("Pulsa el botón para solicitar un saludo personalizado.");
        descripcion.setWrapText(true);

        Button botonSolicitar = new Button("Solicitar saludo");
        botonSolicitar.setDefaultButton(true);
        botonSolicitar.setOnAction(evento -> {
            limpiarFormulario();
            mostrarVista(vistaFormulario);
        });

        vistaInicio = new VBox(16, titulo, descripcion, botonSolicitar);
        vistaInicio.setAlignment(Pos.CENTER);
        vistaInicio.setPadding(new Insets(32));
        vistaInicio.getStyleClass().add("panel");
    }

    private void construirFormulario() {
        Label titulo = new Label("Datos para el saludo");
        titulo.getStyleClass().add("titulo");

        campoNombre = new TextField();
        campoNombre.setPromptText("Escribe tu nombre");

        campoEdad = new TextField();
        campoEdad.setPromptText("Edad entre 1 y 120");

        comboHora = new ComboBox<>();
        for (int hora = 1; hora <= 12; hora++) {
            comboHora.getItems().add(hora);
        }
        comboHora.setPromptText("Hora");

        comboMinutos = new ComboBox<>();
        for (int minuto = 0; minuto <= 59; minuto++) {
            comboMinutos.getItems().add(String.format("%02d", minuto));
        }
        comboMinutos.setPromptText("Minutos");

        comboAmPm = new ComboBox<>();
        comboAmPm.getItems().addAll("AM", "PM");
        comboAmPm.setPromptText("AM/PM");

        GridPane tabla = new GridPane();
        tabla.setHgap(12);
        tabla.setVgap(12);
        tabla.add(new Label("Nombre"), 0, 0);
        tabla.add(campoNombre, 1, 0);
        tabla.add(new Label("Edad"), 0, 1);
        tabla.add(campoEdad, 1, 1);
        tabla.add(new Label("Hora (1 a 12)"), 0, 2);
        tabla.add(comboHora, 1, 2);
        tabla.add(new Label("Minutos (00 a 59)"), 0, 3);
        tabla.add(comboMinutos, 1, 3);
        tabla.add(new Label("AM / PM"), 0, 4);
        tabla.add(comboAmPm, 1, 4);

        etiquetaErrores = new Label();
        etiquetaErrores.getStyleClass().add("error");
        etiquetaErrores.setWrapText(true);
        etiquetaErrores.setVisible(false);
        etiquetaErrores.setManaged(false);

        Button botonAceptar = new Button("Aceptar");
        botonAceptar.setDefaultButton(true);
        botonAceptar.setOnAction(evento -> aceptarFormulario());

        Button botonVolver = new Button("Volver");
        botonVolver.setOnAction(evento -> mostrarVista(vistaInicio));

        HBox botones = new HBox(12, botonAceptar, botonVolver);
        botones.setAlignment(Pos.CENTER_LEFT);

        vistaFormulario = new VBox(16, titulo, tabla, etiquetaErrores, botones);
        vistaFormulario.setPadding(new Insets(32));
        vistaFormulario.getStyleClass().add("panel");
    }

    private void construirResultado() {
        Label titulo = new Label("Saludo");
        titulo.getStyleClass().add("titulo");

        etiquetaSaludo = new Label();
        etiquetaSaludo.getStyleClass().add("saludo");
        etiquetaSaludo.setWrapText(true);

        Button botonOtro = new Button("Solicitar otro saludo");
        botonOtro.setDefaultButton(true);
        botonOtro.setOnAction(evento -> {
            limpiarFormulario();
            mostrarVista(vistaFormulario);
        });

        Button botonInicio = new Button("Ir al inicio");
        botonInicio.setOnAction(evento -> mostrarVista(vistaInicio));

        HBox botones = new HBox(12, botonOtro, botonInicio);
        botones.setAlignment(Pos.CENTER_LEFT);

        vistaResultado = new VBox(16, titulo, etiquetaSaludo, botones);
        vistaResultado.setPadding(new Insets(32));
        vistaResultado.getStyleClass().add("panel");
    }

    private void aceptarFormulario() {
        String nombre = campoNombre.getText();
        String edadTexto = campoEdad.getText();
        Integer hora12 = comboHora.getValue();
        Integer minutos = comboMinutos.getValue() == null ? null : Integer.valueOf(comboMinutos.getValue());
        String amPm = comboAmPm.getValue();

        List<String> errores = validador.validar(nombre, edadTexto, hora12, minutos, amPm);
        if (!errores.isEmpty()) {
            etiquetaErrores.setText(String.join("\n", errores));
            etiquetaErrores.setVisible(true);
            etiquetaErrores.setManaged(true);
            return;
        }

        etiquetaErrores.setVisible(false);
        etiquetaErrores.setManaged(false);

        DatosSaludo datos = new DatosSaludo(
                nombre.trim(),
                Integer.parseInt(edadTexto.trim()),
                hora12,
                minutos,
                "PM".equals(amPm)
        );
        etiquetaSaludo.setText(generador.generar(datos));
        mostrarVista(vistaResultado);
    }

    private void limpiarFormulario() {
        campoNombre.clear();
        campoEdad.clear();
        comboHora.getSelectionModel().clearSelection();
        comboMinutos.getSelectionModel().clearSelection();
        comboAmPm.getSelectionModel().clearSelection();
        etiquetaErrores.setText("");
        etiquetaErrores.setVisible(false);
        etiquetaErrores.setManaged(false);
    }

    private void mostrarVista(VBox vista) {
        vistaInicio.setVisible(vista == vistaInicio);
        vistaFormulario.setVisible(vista == vistaFormulario);
        vistaResultado.setVisible(vista == vistaResultado);
        vistaInicio.setManaged(vista == vistaInicio);
        vistaFormulario.setManaged(vista == vistaFormulario);
        vistaResultado.setManaged(vista == vistaResultado);
    }
}
