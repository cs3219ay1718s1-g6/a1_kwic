package gui;

import component.MasterController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.*;

public class Controller {

    //-----------------------------------------------
    // Outlets
    //-----------------------------------------------
    @FXML private ListView<String> titleListView;
    @FXML private ListView<String> shiftedTitleListView;
    @FXML private Label titleEmptyStateLabel;
    @FXML private Label shiftedTitleEmptyStateLabel;
    @FXML private TextField addTitleTextField;
    @FXML private Button addTitleButton;

    //-----------------------------------------------
    // Properties
    //-----------------------------------------------
    private ObservableList<String> titles;
    private ObservableList<String> shiftedTitles;
    private MasterController masterController;

    //-----------------------------------------------
    // Initialization
    //-----------------------------------------------
    @FXML
    private void initialize() {
        titles = FXCollections.observableArrayList();
        shiftedTitles = FXCollections.observableArrayList();

        titleListView.setItems(titles);
        shiftedTitleListView.setItems(shiftedTitles);

        // Add empty state visibility listeners
        titles.addListener(createEmptyStateListChangeListener(titleEmptyStateLabel));
        shiftedTitles.addListener(createEmptyStateListChangeListener(shiftedTitleEmptyStateLabel));

        // Initialize Master Controller
        masterController = new MasterController(shiftedTitles);
    }

    private static ListChangeListener<String> createEmptyStateListChangeListener(final Label emptyStateLabel) {
        return c -> {
            if (c.getList().size() > 0) {
                if (emptyStateLabel.isVisible()) {
                    emptyStateLabel.setVisible(false);
                }
            } else if (!emptyStateLabel.isVisible()) {
                emptyStateLabel.setVisible(true);
            }
        };
    }

    //-----------------------------------------------
    // Actions
    //-----------------------------------------------
    @FXML
    private void onAddTitleButtonClicked() {
        addCurrentTitle();
    }

    @FXML
    private void onAddTitleTextFieldActionPerformed() {
        addCurrentTitle();
    }

    @FXML
    private void onAddTitleTextFieldKeyReleased() {
        addTitleButton.setDisable(!isCurrentTitleValidForAdding());
    }

    private boolean isCurrentTitleValidForAdding() {
        return (addTitleTextField.getText().trim().length() > 0);
    }

    private void addCurrentTitle() {
        // Does not add title if is invalid
        if (!isCurrentTitleValidForAdding()) {
            return;
        }

        // Do things to title
        titles.add(addTitleTextField.getText().trim());
        triggerMasterController();

        // Clear title and refocus on add title
        addTitleTextField.setText("");
        addTitleButton.setDisable(true);
        addTitleTextField.requestFocus();
    }

    private void triggerMasterController() {
        masterController.triggerUpdate(titles);
    }

    //-----------------------------------------------
    // Save and Open Actions
    //-----------------------------------------------
    @FXML
    private void onSaveButtonClicked() {
        if (titles.size() == 0) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Plain Text File (*.txt)", "*.txt")
        );
        File file = fileChooser.showSaveDialog(MainApplication.getCurrentPrimaryStage());

        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                for (String line : titles) {
                    bufferedWriter.write(line);
                    bufferedWriter.write('\n');
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onOpenButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Text File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Plain Text File (*.txt)", "*.txt")
        );
        File file = fileChooser.showOpenDialog(MainApplication.getCurrentPrimaryStage());

        if (file != null) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;

                titles.clear();
                while ((line = bufferedReader.readLine()) != null) {
                    titles.add(line);
                }

                bufferedReader.close();

                //start line processing in the master controller
                triggerMasterController();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onClearButtonClicked() {
        this.titles.clear();
        this.shiftedTitles.clear();
    }

    //-----------------------------------------------
    // List View Editing
    //-----------------------------------------------
    @FXML
    private void onTitleListViewEditStart() {
    }
}
