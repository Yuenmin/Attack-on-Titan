package com.attackontitan;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.Scanner;

public class ScoreBoard{

    private ObservableList<Record> recordList;
    private String filepath;
    private String newName;
    private int newScore;

    public ScoreBoard() {
        recordList = FXCollections.observableArrayList();
        filepath="Score.txt";
        try {
            File file=new File(filepath);
            if(!file.exists()){
                file.createNewFile();
            }
            Scanner in = new Scanner(new FileInputStream(filepath));
            while (in.hasNextLine()) {
                String record = in.nextLine();
                String[] details = record.split(" ");
                recordList.add(new Record(details[0], Integer.parseInt(details[1])));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sort(){
        recordList.sort((o1, o2) -> o2.getScore() - o1.getScore());
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(filepath));
            for(Record record: recordList) {
                out.println(record.getName()+" "+record.getScore());
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void newRecord(){
        recordList.add(new Record(newName,newScore));
        sort();
    }

    public TableView getScoreBoard(){
        TableView<Record> tableView=new TableView<>();

        TableColumn<Record, Number> indexColumn = new TableColumn<>("#");
        indexColumn.setSortable(false);
        indexColumn.setMinWidth(10);
        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(1+tableView.getItems().indexOf(column.getValue())));

        TableColumn<Record,String> nameColumn=new TableColumn<>("NAME");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Record,Integer> scoreColumn=new TableColumn<>("SCORE");
        scoreColumn.setMinWidth(50);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.setItems(recordList);
        tableView.getColumns().addAll(indexColumn,nameColumn,scoreColumn);
        tableView.setPrefSize(800,410);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    public void setNewScore(int newScore) {
        this.newScore = newScore;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}

