package org.vdgo.contract.fxui.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vdgo.contract.wordservice.service.WordService;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class UIController implements Initializable {
    @FXML
    private JFXTextField shortName;
    @FXML
    private JFXTextField orgName;
    @FXML
    private JFXTextField clientAddress;
    @FXML
    private JFXTextField clientEmail;
    @FXML
    private JFXTextField clientPhone;
    @FXML
    private JFXDatePicker contractDate;
    @FXML
    private JFXTextField actNum;
    @FXML
    private JFXDatePicker actDate;
    @FXML
    private JFXTextField fullName;
    @FXML
    private AnchorPane parent;
    @FXML
    private Button generateButton;
    @Autowired
    private WordService wordService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final RequiredFieldValidator validator = new RequiredFieldValidator("обязательное поле для заполнения");

        setValidatorAndListener1(validator,
                shortName, orgName, clientAddress, clientEmail,
                clientPhone, actNum, fullName, actDate, contractDate);

        final RegexValidator emailValidator = getEmailValidator();
        setValidatorAndListener1(emailValidator, clientEmail);

        generateButton.disableProperty().bind(
                        bindToValidators(shortName, fullName, orgName, clientAddress,
                        clientEmail, clientPhone, actNum, actDate, contractDate)

                        .or(bindTextFields(shortName, fullName, orgName, clientAddress,
                                clientEmail, clientPhone, actNum))
                        .or(contractDate.valueProperty().isNull())
                        .or(actDate.valueProperty().isNull()));
    }

    @FXML
    private void generateWord(MouseEvent mouseEvent) throws FileNotFoundException, Docx4JException {
        wordService.doSomething();
    }

    private void setValidatorAndListener1(ValidatorBase validatorBase, IFXValidatableControl... fields) {
        if (fields.length == 0) {
            return;
        }
        Arrays.stream(fields)
                .forEach(ctr -> {
                    final ObservableList<ValidatorBase> validators = ctr.getValidators();
                    validators.add(validatorBase);

                    if (ctr instanceof JFXTextField) {
                        ((JFXTextField) ctr).textProperty().addListener((observable, oldValue, newValue) -> {
                            if (!oldValue.equals(newValue)) {
                                ctr.validate();
                            }
                        });
                    }

                    ((Control) ctr).focusedProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            ctr.validate();
                        }
                    });
                });
    }

    private BooleanBinding bindToValidators(IFXValidatableControl... fields) {
        if (fields.length == 0) {
            return null;
        }

        return Arrays.stream(fields)
                .filter(Objects::nonNull)
                .map(field -> field.getValidators().stream().map(vld -> Bindings.selectBoolean(vld.hasErrorsProperty())).reduce(BooleanExpression::or).orElse(Bindings.createBooleanBinding(() -> false)))
                .reduce(BooleanExpression::or)
                .orElse(null);
    }

    private BooleanBinding bindTextFields(JFXTextField... fields) {
        if (fields.length == 0) {
            return null;
        }

        return Arrays.stream(fields)
                .filter(Objects::nonNull)
                .map(field -> field.textProperty().isEmpty())
                .reduce(BooleanExpression::or)
                .orElse(Bindings.createBooleanBinding(() -> false));
    }

    private RegexValidator getEmailValidator() {
        RegexValidator emailValidator = new RegexValidator("некорретный email");
        emailValidator.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return emailValidator;
    }
}
