module dev.ua.ikeepcalm {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens dev.ua.ikeepcalm to javafx.fxml;
    opens dev.ua.ikeepcalm.controllers.menu to javafx.fxml;
    opens dev.ua.ikeepcalm.controllers.files to javafx.fxml;
    opens dev.ua.ikeepcalm.controllers.records to javafx.fxml;

    exports dev.ua.ikeepcalm;
    exports dev.ua.ikeepcalm.controllers.menu;
    exports dev.ua.ikeepcalm.controllers.files;
    exports dev.ua.ikeepcalm.controllers.records;
    exports dev.ua.ikeepcalm.data.source;
    exports dev.ua.ikeepcalm.data;

}