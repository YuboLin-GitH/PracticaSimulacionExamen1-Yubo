module com.yubo.practicasimulacionexamen1yubo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.yubo.practicasimulacionexamen1yubo to javafx.fxml;
    exports com.yubo.practicasimulacionexamen1yubo;
}