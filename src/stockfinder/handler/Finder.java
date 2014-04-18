/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockfinder.handler;

import common.answer.bean.dto.SearchRecord;
import common.answer.logic.Stock2DB;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stockfinder.*;

/**
 *
 * @author doyin
 */
public class Finder implements Initializable {

    @FXML
    private TextField weekp1;
    @FXML
    private TextField weekp2;
    @FXML
    private TextField weekp3;
    @FXML
    private RadioButton weekup;
    @FXML
    private RadioButton weekdown;
    @FXML
    private TextField dayp1;
    @FXML
    private TextField dayp2;
    @FXML
    private TextField dayp3;
    @FXML
    private RadioButton dayup;
    @FXML
    private RadioButton daydown;
    @FXML
    private TextField kp1;
    @FXML
    private TextField kp2;
    @FXML
    private TextField kp3;
    @FXML
    private TextField kp4;
    @FXML
    private TextField kp5;
    @FXML
    private RadioButton kup;
    @FXML
    private RadioButton kdown;
    @FXML
    private TextField ckp1;
    @FXML
    private TextField ckp2;
    @FXML
    private TextField ckp3;
    @FXML
    private CheckBox red;
    @FXML
    private CheckBox blue;
    @FXML
    private TableView resulttable;
    @FXML
    private TableColumn stock_cd;
    @FXML
    private TableColumn record_date;
    @FXML
    private TableColumn stock_name;
    @FXML
    private TableColumn fullup_cnt;
    @FXML
    private TableColumn liutongguben;
    @FXML
    private TableColumn yeji;
    @FXML
    private TableColumn liutonggudongbl;
    @FXML
    private Button search;
    @FXML
    private Button historysearch;
    @FXML
    private Button insert2db;
    @FXML
    private Label msglabel;
    @FXML
    private TextField searchdate;
    private Thread thr1;
    @FXML
    private RadioButton dayk;
    @FXML
    private RadioButton weekk;
    @FXML
    private CheckBox liangrbd;
    @FXML
    private TextField dayago;
    @FXML
    private TextField rinei;
    @FXML
    private TextField gdc;

    @FXML
    private void handleSearchAction(ActionEvent event) {
        if (thr1 != null && thr1.isAlive()) {
            return;
        }
        thr1 = new Thread() {

            public void run() {
                search.setDisable(true);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        msglabel.setText("正在查询，请稍后.....");
                    }
                });

                int weekdirection = -1;
                if (weekup.isSelected()) {
                    weekdirection = 1;
                }
                int daydirection = -1;
                if (dayup.isSelected()) {
                    daydirection = 1;
                }
                int kdirection = -1;
                if (kup.isSelected()) {
                    kdirection = 1;
                }
                int sr = -1;
                if (red.isSelected()) {
                    sr = 1;
                }
                int sb = -1;
                if (blue.isSelected()) {
                    sb = 1;
                }
                int dwk = -1;
                if (dayk.isSelected()) {
                    dwk = 1;
                }
                int lr = -1;
                if (liangrbd.isSelected()) {
                    lr = 1;
                }
                List<SearchRecord> lst = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).getSearchResultList(Integer.parseInt(weekp1.getText()), Integer.parseInt(weekp2.getText()), Integer.parseInt(weekp3.getText()), weekdirection,
                        Integer.parseInt(dayp1.getText()), Integer.parseInt(dayp2.getText()), Integer.parseInt(dayp3.getText()), daydirection,
                        Integer.parseInt(kp1.getText()), Integer.parseInt(kp2.getText()), kdirection, kp3.getText(), Integer.parseInt(kp4.getText()), Integer.parseInt(kp5.getText()),
                        sr, sb, Integer.parseInt(ckp1.getText()), Integer.parseInt(ckp2.getText()), Integer.parseInt(ckp3.getText()), dwk, lr, Integer.parseInt(dayago.getText()), Integer.parseInt(rinei.getText()), Integer.parseInt(gdc.getText()));

                if (stock_cd.getCellValueFactory() == null) {
                    stock_cd.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("stock_cd"));
                    record_date.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("record_date"));
                    stock_name.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("stock_name"));
                    fullup_cnt.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("fullup_cnt"));
                    liutongguben.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("liutongguben"));
                    yeji.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("yeji"));
                    liutonggudongbl.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("liutonggudongbl"));

                }


                ObservableList<SearchRecord> data = FXCollections.observableArrayList(lst);

                resulttable.setItems(data);

                search.setDisable(false);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        msglabel.setText("");
                    }
                });


            }
        };
        thr1.start();

    }

    @FXML
    private void handleSearchHistoryRecordAction(ActionEvent event) {
        if (thr1 != null && thr1.isAlive()) {
            return;
        }
        thr1 = new Thread() {

            public void run() {
                historysearch.setDisable(true);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        msglabel.setText("正在读取数据，请稍后.....");
                    }
                });
                List<SearchRecord> lst = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).gethistoryRecordFromDB(searchdate.getText());

                if (stock_cd.getCellValueFactory() == null) {
                    stock_cd.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("stock_cd"));
                    record_date.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("record_date"));
                    stock_name.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("stock_name"));
                    fullup_cnt.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("fullup_cnt"));
                    liutongguben.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("liutongguben"));
                    yeji.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("yeji"));
                    liutonggudongbl.setCellValueFactory(new PropertyValueFactory<SearchRecord, String>("liutonggudongbl"));

                }


                ObservableList<SearchRecord> data = FXCollections.observableArrayList(lst);

                resulttable.setItems(data);

                historysearch.setDisable(false);

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        msglabel.setText("");
                    }
                });

            }
        };
        thr1.start();

    }

    @FXML
    private void handleEditAction(ActionEvent event) {
    }

    @FXML
    private void handleInsertToDBAction(ActionEvent event) {
        if (thr1 != null && thr1.isAlive()) {
            return;
        }
        thr1 = new Thread() {

            public void run() {
                insert2db.setDisable(true);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        msglabel.setText("正在入库，请稍后.....");
                    }
                });
                String rst = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).insertsearchResult(resulttable.getItems());
                if (rst != null) {
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            msglabel.setText("");
                        }
                    });

                }
                insert2db.setDisable(false);
            }
        };
        thr1.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleStrongMarketAction(ActionEvent event) {
        dayp3.setText("100");
        dayk.setSelected(true);
        kup.setSelected(true);
        kp1.setText("0");
        kp2.setText("1");
        kp3.setText("5");
        ckp1.setText("0");
        ckp2.setText("9");
        ckp3.setText("11");
    }

    @FXML
    private void handleMiddleStrongMarketAction(ActionEvent event) {
        dayp3.setText("100");
        dayk.setSelected(true);
        kup.setSelected(true);
        kp1.setText("0");
        kp2.setText("2");
        kp3.setText("5,10");
        ckp1.setText("0");
        ckp2.setText("5");
        ckp3.setText("11");
    }

    @FXML
    private void handleTurnPointMarketAction(ActionEvent event) {
        dayp3.setText("80");
        dayk.setSelected(true);
        kup.setSelected(true);
        kp1.setText("0");
        kp2.setText("4");
        kp3.setText("5,10,20");
        ckp1.setText("0");
        ckp2.setText("5");
        ckp3.setText("11");
    }

    @FXML
    private void handleWeekMarketAction(ActionEvent event) {
        dayp3.setText("0");
        weekk.setSelected(true);
        kdown.setSelected(true);
        kp1.setText("0");
        kp2.setText("3");
        kp3.setText("20");
        ckp1.setText("0");
        ckp2.setText("-11");
        ckp3.setText("11");
    }
}
