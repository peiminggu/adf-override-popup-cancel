# Demo for override cancel event from Oracle ADF popup

###### JDeveloepr 12.2.1.3.0

When we are using ADF popup, sometims, we don't want the popup can be closed if some value is changed in the popup dialog, we would like popup another confirm dialog to ask user if want to discard the changes. This can be done in the dialogListener, however, the popup dialog still can be closed by the right up conner "X" button or "ESC" key which won't popup the close confirm dialog. 

In this case, we need to override the ADF default popup close action. If user click at the "X" or press "ESC", it would popup the confirm dialog.

For example:

In the main popup, we add a clientListener with type "popupOpened" to trigger the popupOpenedClientListener function, in which orverride the popup default cancel function. 

In the new cancel function, it would popup the confirm dialog to ask user to confirm. And we can add the discard/rollback logic in the confirm dialog dialogListener function.

```
<!--main popup-->
<af:popup childCreation="deferred" autoCancel="disabled" id="popupMain"
		  binding="#{backingBeanScope.backingBean.popupMain}">
	<af:dialog id="d2" title="Main Popup" contentWidth="400" contentHeight="300">
		<f:facet name="buttonBar"/>
		<af:panelGroupLayout id="pgl2">
			<af:outputText value="Click 'Cancel' or  press 'ESC' to open Confirm Popup." id="ot1"/>
		</af:panelGroupLayout>
	</af:dialog>
	<af:resource type="javascript">
	function popupOpenedClientListener(event) {
	   var popup = event.getSource();
	   popup.cancel = function() {
			var confPopClientId = popup.getProperty('confirmPopId');
			var confPop = AdfPage.PAGE.findComponentByAbsoluteId(confPopClientId);
			confPop.show();
	   }; 
	 }
	</af:resource>
	<af:clientListener type="popupOpened" method="popupOpenedClientListener"/>
	<af:clientAttribute name="confirmPopId"
						value="#{backingBeanScope.backingBean.popupCancelConfirm.clientId}"/>
</af:popup>
<!--confirm popup-->
<af:popup childCreation="deferred" autoCancel="disabled" id="popupCancelConfirm"
		  binding="#{backingBeanScope.backingBean.popupCancelConfirm}" clientComponent="true">
	<af:dialog id="d3" title="Confirm Popup" type="yesNo"
			   dialogListener="#{backingBeanScope.backingBean.onCancelConfirmDialog}">
		<f:facet name="buttonBar"/>
		<af:panelGroupLayout id="pgl3">
			<af:outputText value="Click 'Yes' to close Main Popup" id="ot2"/>
		</af:panelGroupLayout>
	</af:dialog>
</af:popup>
```
