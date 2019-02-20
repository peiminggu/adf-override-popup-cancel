package demo.opc.view.backing;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

public class BackingBean {
    private RichPopup popupCancelConfirm;
    private RichPopup popupMain;
    
    private ADFLogger logger = ADFLogger.createADFLogger(BackingBean.class);

    public BackingBean() {
        super();
    }

    public void setPopupCancelConfirm(RichPopup popupCancelConfirm) {
        this.popupCancelConfirm = popupCancelConfirm;
    }

    public RichPopup getPopupCancelConfirm() {
        return popupCancelConfirm;
    }

    public void setPopupMain(RichPopup popupMain) {
        this.popupMain = popupMain;
    }

    public RichPopup getPopupMain() {
        return popupMain;
    }
    
    public void onCancelConfirmDialog(DialogEvent dialogEvent) {
        if (DialogEvent.Outcome.yes.equals(dialogEvent.getOutcome())) { 
            //rollback changes         
            logger.info("Confirmed! Close main popup.");
            popupMain.hide();
        }    
    }


}
