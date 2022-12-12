module com.furu.entregahibernatefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;

    opens com.furu.entregahibernatefx to javafx.fxml;
    opens entity to org.hibernate.orm.core, javafx.base;

    exports com.furu.entregahibernatefx;
}