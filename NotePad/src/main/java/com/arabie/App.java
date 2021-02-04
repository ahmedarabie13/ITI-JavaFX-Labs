package com.arabie;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class App extends Application {


    final Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent clipboardContent = new ClipboardContent();
    private TextArea textArea;
    private boolean isTextChanged = false;
    private File file;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        var fileMenu = new Menu("File");
        var newItem = new MenuItem("New");
        var openItem = new MenuItem("Open");
        var saveItem = new MenuItem("Save");
        var exitItem = new MenuItem("Exit");
        SeparatorMenuItem seperator = new SeparatorMenuItem();

        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));

        fileMenu.getItems().addAll(newItem, openItem, saveItem, new SeparatorMenuItem(), exitItem);


        var editMenu = new Menu("Edit");
        var undoItem = new MenuItem("Undo");
        var cutItem = new MenuItem("Cut");
        var copyItem = new MenuItem("Copy");
        var pasteItem = new MenuItem("Paste");
        var deleteItem = new MenuItem("Delete");
        var selectAllItem = new MenuItem("Select All");

        editMenu.getItems().addAll(undoItem, new SeparatorMenuItem(), cutItem, copyItem, pasteItem, deleteItem,
                new SeparatorMenuItem(), selectAllItem);


        var helpMenu = new Menu("Help");
        var aboutItem = new MenuItem("About Notepad");
        helpMenu.getItems().add(aboutItem);

        var menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        textArea = new TextArea();

        var root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);


        newItem.setOnAction((ActionEvent e) -> {
            if (isTextChanged) {
                checkSaved();


            }
            textArea.clear();
            file = null;

        });

        openItem.setOnAction((e) -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            if (isTextChanged) {

//                writeChanges();
                checkSaved();

            }
            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                readFromFileReader();
//                readFromFileInputStream();
            }

        });

        saveItem.setOnAction((ActionEvent e) -> {

            writeChanges();

        });

        exitItem.setOnAction((ActionEvent e) -> {
//            if (changeFlag)
                checkSaved();
//                writeChanges();
            Platform.exit();
        });

        undoItem.setOnAction((ActionEvent e) -> {
            textArea.undo();
        });

        copyItem.setOnAction((e) -> {

            String str = textArea.getSelectedText();

            clipboardContent.put(DataFormat.PLAIN_TEXT, str);
            clipboard.setContent(clipboardContent);
        });

        cutItem.setOnAction((e) -> {

            String str = textArea.getSelectedText();
            IndexRange cutRange = textArea.getSelection();
            textArea.deleteText(cutRange);
            clipboardContent.put(DataFormat.PLAIN_TEXT, str);
            clipboard.setContent(clipboardContent);
        });

        pasteItem.setOnAction((e) -> {

            if (textArea.getSelectedText().equals("")) {

                int caretPosition = textArea.getCaretPosition();
                textArea.insertText(caretPosition, clipboardContent.getString());
            } else {
                textArea.replaceSelection(clipboard.getString());
            }

        });
//        pasteItem.setOnAction(textArea::paste);
        deleteItem.setOnAction((e) -> {
            textArea.deleteText(textArea.getSelection());
        });
        aboutItem.setOnAction((ActionEvent e) -> {
            InputStream stream = null;

            Image image = new Image("image.jpeg");
            //Creating the image view
            ImageView imageView1 = new ImageView(image);
            //Setting the image view parameters
            imageView1.setX(30);
            imageView1.setY(60);
            imageView1.setFitWidth(270);
            imageView1.setPreserveRatio(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setGraphic(imageView1);
            alert.setTitle("About NotePad");
            alert.setHeaderText("My NotePad App");
            alert.setContentText("Made by Ahmed Arabie");
            alert.show();
        });

        selectAllItem.setOnAction((e) -> {
            textArea.selectAll();
        });


        textArea.textProperty().addListener((observableValue, oldString, newString) -> {

            if (!oldString.equals(newString))
                isTextChanged = true;

        });


        var scene = new Scene(root, 640, 480);
        textArea.selectAll();


        stage.setScene(scene);
        stage.show();
    }

    private void readFromFileReader() {
        try (FileReader fileReader = new FileReader(file)) {
            int c;
            textArea.clear();
            while ((c = fileReader.read()) != -1) {
                textArea.appendText((char) c + "");
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void readFromFileInputStream() {
        try (FileInputStream fileReader = new FileInputStream(file)) {
            int c;
            textArea.clear();
            while ((c = fileReader.read()) != -1) {
                textArea.appendText((char) c + "");
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private void checkSaved() {
        if (isTextChanged) {
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");
            Alert alert = new Alert(Alert.AlertType.NONE, "Save The File", yes, no);
            alert.setTitle("File not saved");
            alert.setHeaderText("File not saved");
            alert.setContentText("Do you want to save this file");
            alert.showAndWait().ifPresent(response -> {
                if (response == yes) {
                    writeChanges();
                } else if (response == no) {
                    isTextChanged =false;
                }
            });

        }
    }

    private void writeChanges() {
        if(isTextChanged)
        {
            if (file == null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Resource File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                file = fileChooser.showOpenDialog(mainStage);
            }
            if (file != null) {
                writeFromFileWriter();
//                writeFromFileOutputStream();
            }
        }
        isTextChanged =false;
    }

    private void writeFromFileWriter() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            int c;
            fileWriter.write(textArea.getText());
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void writeFromFileOutputStream() {
        try (FileOutputStream fileWriter = new FileOutputStream(file)) {
            String str =textArea.getText();
            byte[] arr =str.getBytes();
//            int c;

            fileWriter.write(arr);

        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}