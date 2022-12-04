package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
public class MyDictionary extends Application {
   int yLine=10;
    TextField wordTextField;
    Button searchButton;
    Label meaningLabel;
    DictionaryUsingHashMap dictionaryUsingHashMap;

    Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(300,300);
         wordTextField=new TextField();

        ListView<String>suggestedWordList =new ListView<>();
        suggestedWordList.setTranslateY(yLine+70);
        String[] suggestedList={"Bhargav","Sai","Lakshith","Shankar","Nihanya","Pavani","Jagruthi","Bharathi"};
suggestedWordList.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        String selectedWord=suggestedWordList.getSelectionModel().getSelectedItem();
        meaningLabel.setText(selectedWord);
    }
});

        suggestedWordList.getItems().addAll(suggestedList);
        wordTextField.setPromptText("Please enter a word");

        wordTextField.setTranslateX(yLine);
        wordTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
             meaningLabel.setText(wordTextField.getText());
             String word=wordTextField.getText();
             if(word.isBlank()==false && word.length()>=3){
                 //fetch suggestions
                 String []suggestions=dictionaryUsingHashMap.getSueggestions(word);

                 //Bind suggestions to the list
                 suggestedWordList.getItems().clear();
                 suggestedWordList.getItems().addAll(suggestions);

             }
            }
        });

         meaningLabel=new Label("I am meaning");
        meaningLabel.setTranslateY(yLine+30);
      dictionaryUsingHashMap=new DictionaryUsingHashMap();

         searchButton=new Button("Search");
        searchButton.setTranslateY(yLine);
        searchButton.setTranslateX(200);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word=wordTextField.getText();
                if(word.isBlank()){
                    meaningLabel.setText("Please enter a word");
                    meaningLabel.setTextFill(Color.BROWN);
                }
                else{
                    meaningLabel.setText(dictionaryUsingHashMap.findMeaning(word));
                    meaningLabel.setTextFill(Color.BLUE);
                }
            }
        });



        root.getChildren().addAll(wordTextField,meaningLabel,searchButton,suggestedWordList);
        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}