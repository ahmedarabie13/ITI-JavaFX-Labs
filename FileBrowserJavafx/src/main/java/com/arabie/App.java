package com.arabie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends Application {

    private static Scene scene;
//    private ArrayList<Item> data =new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
//        FolderItem rootFolder =new FolderItem("Root");
//        TreeItem rootTreeItem =new TreeItem("Root");
//        getStructure("D:\\iTi\\DB Fundamentals",rootFolder,rootTreeItem);
////        data.stream().forEach(System.out::println);
//        System.out.println(rootFolder);
//        TreeView treeView=new TreeView(rootTreeItem);
//        treeView.setCellFactory(ChoiceBoxTreeCell.forTreeView());

//        scene=new Scene(treeView);
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

//    private void getStructure(String root, FolderItem folderItem, TreeItem treeItem) {
//        File f = null;
//        String[] items;
//
//        try {
//            f = new File(root);
//            String path=f.getPath();
//            items = f.list();
//            for(String item:items) {
//                TreeItem newItem=new TreeItem<>(item);
//                f = new File(path + "/" + item);
//
//                if (f.isFile()) {
//                    folderItem.addChild(new FileItem(item));
//                    newItem.setGraphic(new FontIcon());
//
//                } else if(f.isDirectory()) {
//                    FolderItem folder =new FolderItem(item);
//                    folderItem.addChild(folder);
//                    getStructure(root+"/"+item,folder,newItem);
//                    newItem.setGraphic(new FontIcon());
//                }
//                treeItem.getChildren().add(newItem);
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}