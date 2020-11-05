package org.vdgo.contract.fxui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vdgo.contract")
public class FxUiApplication {

	public static void main(String[] args) {
		Application.launch(MainApplication.class, args);
	}
}
