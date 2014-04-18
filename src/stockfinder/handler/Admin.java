/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockfinder.handler;

import com.sai.javafx.calendar.FXCalendar;
import com.sai.javafx.calendar.FXCalendarUtility;
import common.answer.logic.Stock2DB;
import common.answer.util.Canlendar;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import stockfinder.uitl.ViewUtils;
import stockfinder.*;
import stockfinder.checker.InputChecker;

/**
 *
 * @author doyin
 */
public class Admin implements Initializable {

    @FXML
    private RadioButton cdata;
    @FXML
    private RadioButton fromfile;
    @FXML
    private RadioButton ften;
    @FXML
    private RadioButton weekdata;
    @FXML
    private RadioButton oneweek;
    @FXML
    private RadioButton allweek;
    @FXML
    private RadioButton ztgsdata;
    @FXML
    private RadioButton liangrongupdate;
    @FXML
    private PasswordField password;
    @FXML
    private Label timer1;
    @FXML
    private Label timer2;
    @FXML
    private Label timer3;
    @FXML
    private Label timer4;
    @FXML
    private Label timer5;
    @FXML
    private Label timer6;
    @FXML
    private Label msg;
    @FXML
    private ProgressIndicator statusindx1;
    @FXML
    private ProgressIndicator statusindx2;
    @FXML
    private ProgressIndicator statusindx3;
    @FXML
    private ProgressIndicator statusindx4;
    @FXML
    private ProgressIndicator statusindx5;
    @FXML
    private ProgressIndicator statusindx6;
//    @FXML
//    private TextField startdate;
    
    @FXML
    private FXCalendar startdateCalendar;
    
    
    private Thread thr1;
    private Thread thr2;
    private boolean completeflg = false;
    @FXML
    private TextField datafile_path_sh;
    @FXML
    private TextField datafile_path_sz;
    @FXML
    private TextField f10_file_path_sh;
    @FXML
    private TextField f10_file_path_sz;
    @FXML
    private TextField ztdata_path;
    @FXML
    private TextField liangrong_path;
    @FXML
    private Label msg2;
    @FXML
    private PasswordField systemsettingpswd;

    @FXML
    private void handleUpateSysSettingAction(ActionEvent event) {

        if (!"123456".equals(systemsettingpswd.getText())) {
             Tooltip tooltip = new Tooltip();
            systemsettingpswd.setStyle("-fx-background-color: red, -fx-text-box-border, -fx-control-inner-background;");  
            tooltip.setText("密码错误，请重新输入");
            systemsettingpswd.setTooltip(tooltip);
           
            datafile_path_sh.setDisable(true);
            datafile_path_sz.setDisable(true);
            f10_file_path_sh.setDisable(true);
            f10_file_path_sz.setDisable(true);
            return;
        } else {
            msg2.setText("");

            datafile_path_sh.setDisable(false);
            datafile_path_sz.setDisable(false);
            f10_file_path_sh.setDisable(false);
            f10_file_path_sz.setDisable(false);

        }


    }

    @FXML
    private void handleUpateAction(ActionEvent event) {
        if (!"12345".equals(password.getText())) {
            Tooltip tooltip = new Tooltip();
            password.setStyle("-fx-background-color: red, -fx-text-box-border, -fx-control-inner-background;");  
            tooltip.setText("密码错误，请重新输入");
            password.setTooltip(tooltip);
            
           
            return;
        } 


        completeflg = false;

        if (cdata.isSelected() && statusindx1.getProgress() < 1) {

            setlabelTimer(timer1, statusindx1);
            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);
                    if (((Stock2DB) StockFinder.ac.getBean("Stock2DB")).getAlldata(datafile_path_sh.getText(),
                            datafile_path_sz.getText()).equals("0")) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }

        completeflg = false;

        if (fromfile.isSelected() && statusindx2.getProgress() < 1) {
            final String stdate;
            if ("2012-01-01".compareTo(startdateCalendar.getTextField().getText()) > 0) {
                stdate = "2012-01-01";
            } else {
                stdate = startdateCalendar.getTextField().getText();
            }
            setlabelTimer(timer2, statusindx2);
            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);
                    String retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).insert2dbFromFile(stdate, Canlendar.getSystemdate(), false,
                            datafile_path_sh.getText(),
                            datafile_path_sz.getText());
                    if (("done").equals(retv)) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }

        completeflg = false;

        if (ften.isSelected() && statusindx3.getProgress() < 1) {

            setlabelTimer(timer3, statusindx3);
            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);

                    String retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).insertf10FromFile(
                            f10_file_path_sh.getText(),
                            f10_file_path_sz.getText(),
                            datafile_path_sh.getText(),
                            datafile_path_sz.getText());
                    if (("done").equals(retv)) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }

        completeflg = false;

        if (weekdata.isSelected() && statusindx4.getProgress() < 1) {

            setlabelTimer(timer4, statusindx4);


            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);
                    String retv;
                    if (oneweek.isSelected()) {
                        retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).generateWeekData(true, datafile_path_sh.getText(),
                                datafile_path_sz.getText());
                    } else {
                        retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).generateWeekData(false, datafile_path_sh.getText(),
                                datafile_path_sz.getText());
                    }

                    if (("done").equals(retv)) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }

        completeflg = false;

        if (ztgsdata.isSelected() && statusindx5.getProgress() < 1) {

            setlabelTimer(timer5, statusindx5);


            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);
                    String retv;

                    retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).writeZhangTBfile(ztdata_path.getText());


                    if (("done").equals(retv)) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }
        completeflg = false;
        if (liangrongupdate.isSelected() && statusindx6.getProgress() < 1) {

            setlabelTimer(timer6, statusindx6);


            thr2 = new Thread() {

                public void run() {
                    // pb.incrementProgressBy(1);
                    String retv;

                    retv = ((Stock2DB) StockFinder.ac.getBean("Stock2DB")).insertLiangrongTargetTable(liangrong_path.getText());


                    if (("done").equals(retv)) {
                        completeflg = true;
                    }
                }
            };
            thr1.start();
            thr2.start();
        }


    }

    private void setlabelTimer(final Label timer, final ProgressIndicator pindic) {

        thr1 = new Thread() {

            public void run() {
                long startTime = System.currentTimeMillis();
                while (true) {
                    final String time = ViewUtils.timer(startTime);
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            timer.setText("用时:" + time);
                        }
                    });
                    try {
                        sleep(1000);

                        if (completeflg) {

                            pindic.setProgress(1);
                            break;
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        ;
    }

    ;   
        
    }

    @FXML
    private void handleWeekdatachangeAction(ActionEvent event) {
        oneweek.setDisable(false);

        allweek.setDisable(false);
    }

    @FXML
    private void handlenotWeekdatachangeAction(ActionEvent event) {
        oneweek.setDisable(true);

        allweek.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        oneweek.setDisable(true);
        allweek.setDisable(true);

         String fmt = "yyyy-MM-dd";
        String date = ViewUtils.dateFormater(fmt);


        startdateCalendar.setShowWeekNumber(true);
        startdateCalendar.setValue(new FXCalendarUtility().convertStringtoDate(date));
        
      
    }
}
