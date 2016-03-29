package erufu.wizardo.ljimagerevert;

import java.io.PrintWriter;
import java.io.StringWriter;
import erufu.wizardo.ljimagerevert.util.LinkListReverter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {

    final LinkListReverter linkListReverter = new LinkListReverter();

    final ExecutorService daemonExecutor = Executors.newFixedThreadPool(1,
            new DaemonThreadFactory());

    private class DaemonThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable runnable) {
            final Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    }

    @FXML
    private TextArea outputArea;

    @FXML
    protected void handleMouseClick(MouseEvent event) {
        apply();
        event.consume();
    }

    private String process(String inputText) {
        try {
            return linkListReverter.revert(inputText);
        } catch (Exception e) {;
            return "Application error occured.\nPlease contact developer.\nStack trace:\n" + getMessage(e);
        }
    }

    private String getMessage(Exception exception) {
        final StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private void apply() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();

        final String inputText = clipboard.getContent(DataFormat.PLAIN_TEXT).toString();

        outputArea.setText("Processing...");

        daemonExecutor.submit(() -> {
            final String outputText = process(inputText);
            Platform.runLater(() -> updateUI(outputText));
        });

    }

    private void updateUI(String outputText) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();

        if (outputText.trim().isEmpty()) {
            outputArea.setText("No output");
        } else {
            outputArea.setText(outputText.trim());
        }

        final ClipboardContent content = new ClipboardContent();
        content.putString(outputText);
        clipboard.setContent(content);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
