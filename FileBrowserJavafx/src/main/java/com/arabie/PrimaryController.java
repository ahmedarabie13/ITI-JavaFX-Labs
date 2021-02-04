package com.arabie;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;

public class PrimaryController implements Initializable {

    @FXML
    private TreeView<AbstractFileSystemItem> treeViewContainer;
    @FXML
    private ListView<AbstractFileSystemItem> lstView;
    @FXML
    private TextField txtField;
    @FXML
    private Button genBtn;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {



        treeViewContainer.getSelectionModel().selectedItemProperty().addListener((observable,oldVale,newValue)->{
            lstView.getItems().clear();
            if(newValue.getValue() instanceof FolderItem) {
                ObservableList<AbstractFileSystemItem> list =FXCollections.observableList(((FolderItem) newValue.getValue()).getChildren());
                System.out.println(((FolderItem)newValue.getValue()).getChildren());
                lstView.setItems(list);
                lstView.setCellFactory(new Callback<ListView<AbstractFileSystemItem>, ListCell<AbstractFileSystemItem>>() {
                    @Override
                    public ListCell<AbstractFileSystemItem> call(ListView<AbstractFileSystemItem> abstractFileSystemItemListView) {
                        return new ListCell<AbstractFileSystemItem>(){
                            @Override
                            protected void updateItem(AbstractFileSystemItem abstractFileSystemItem, boolean b) {
                                super.updateItem(abstractFileSystemItem, b);
                                if(b==true){
                                    setText(null);
                                    setGraphic(null);
                                }else {
                                    if (abstractFileSystemItem instanceof FolderItem) {
                                        FontIcon fIcon = new FontIcon("mdi2f-folder");
                                        fIcon.setIconColor(Color.web("#807bb8"));
                                        setText(abstractFileSystemItem.getName());
                                        setGraphic(fIcon);
                                    } else if (abstractFileSystemItem instanceof FileItem) {
                                        FontIcon fIcon = new FontIcon("mdi2f-file");
                                        fIcon.setIconColor(Color.web("#807bb8"));
                                        setText(abstractFileSystemItem.getName());
                                        setGraphic(fIcon);
                                    }
                                }
                            }
                        };
                    }
                });
            }
            else {

                lstView.getItems().clear();
                lstView.getItems().add(newValue.getValue());
                lstView.setCellFactory(new Callback<ListView<AbstractFileSystemItem>, ListCell<AbstractFileSystemItem>>() {
                    @Override
                    public ListCell<AbstractFileSystemItem> call(ListView<AbstractFileSystemItem> abstractFileSystemItemListView) {
                        return new ListCell<AbstractFileSystemItem>(){
                            @Override
                            protected void updateItem(AbstractFileSystemItem abstractFileSystemItem, boolean b) {
                                super.updateItem(abstractFileSystemItem, b);
                                if(b==true){
                                    setText(null);
                                    setGraphic(null);
                                }else if (abstractFileSystemItem instanceof FileItem) {
                                    FontIcon fIcon = new FontIcon("mdi2f-file");
                                    fIcon.setIconColor(Color.web("#807bb8"));
                                    setText(abstractFileSystemItem.getName());
                                    setGraphic(fIcon);
                                }
                            }
                        };
                    }
                });
            }
        });


    }

    @FXML
    private void onClickGenHandler(){
        DirectoryChooser chooser = new DirectoryChooser();

        File file = chooser.showDialog(lstView.getScene().getWindow());
        if (file != null) {
            txtField.setText(file.getPath());
        }
        FolderItem rootFolder =new FolderItem("Root");
        TreeItem<AbstractFileSystemItem> rootTreeItem =new TreeItem<>(rootFolder);
        getStructure(file.getPath(),rootFolder,rootTreeItem);
        System.out.println(rootFolder);

        treeViewContainer.setRoot(rootTreeItem);
        treeViewContainer.setShowRoot(false);

    }
    private void getStructure(String root, FolderItem folderItem, TreeItem treeItem) {
        File f = null;
        String[] items;

        try {
            f = new File(root);
            String path=f.getPath();
            items = f.list();
            for(String item:items) {
//                TreeItem<FileSystemItem> newTreeItem=new TreeItem<>(new FolderItem(item));
                f = new File(path + "/" + item);
                TreeItem<AbstractFileSystemItem> newTreeItem;
                if (f.isFile()) {
                    newTreeItem=new TreeItem<>(new FileItem(item));
                    folderItem.addChild(new FileItem(item));
                    FontIcon fIcon= new FontIcon("mdi2f-file");
                    fIcon.setIconColor(Color.web("#807bb8"));
                    newTreeItem.setGraphic(fIcon);
                    treeItem.getChildren().add(newTreeItem);


                } else if(f.isDirectory()) {

                    FolderItem folder =new FolderItem(item);
                    newTreeItem=new TreeItem<>(folder);
                    folderItem.addChild(folder);
                    getStructure(root+"/"+item,folder,newTreeItem);
                    FontIcon fIcon =new FontIcon("mdi2f-folder");
                    fIcon.setIconColor(Color.web("#807bb8"));
                    newTreeItem.setGraphic(fIcon);
                    treeItem.getChildren().add(newTreeItem);
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
