package sample;

import javafx.beans.Observable;
import javafx.beans.value.ObservableBooleanValue;

public interface BlobCommand {
    boolean undo();
    boolean redo();


    ObservableBooleanValue undoAvailableProperty();
    boolean isUndoAvailable();

    ObservableBooleanValue redoAvailableProperty();
    boolean isRedoAvailable();

    void preventMerge();

    void forgetHistory();

    void mark();
    UndoPosition getCurrentPosition();

    ObservableBooleanValue atMarkedPositionProperty();
    boolean isAtMarkedPosition();

    void close();

    interface UndoPosition {
        void mark();
        boolean isValid();
    }

}
