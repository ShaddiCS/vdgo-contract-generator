package org.vdgo.contract.fxui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.vdgo.contract.fxui.MainApplication.StageReadyEvent;
import org.vdgo.contract.wordservice.service.WordService;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Autowired
    private WordService wordService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Parent parent;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI.fxml"));
            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            parent = loader.load();

            Stage stage = event.getStage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
