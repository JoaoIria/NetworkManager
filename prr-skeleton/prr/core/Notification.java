package prr.core;

public class Notification{
	
	private String _arrivalId;
	private String _departureId;
	private NotificationType _notificationType;


	/**
	 * Constructor.
	 */

	public Notification(String type, String idDeparture, String idArrival) {
		setNotificationType(type);
		_arrivalId = idArrival;
		_departureId = idDeparture;
	}


	/**
     * Gets the Notification Type
     * 
     * @return the Notification Type
     */

	public NotificationType getNotificationType(){
		return _notificationType;
	}

	public String getNotificationArrivalId(){
		return _arrivalId;
	}

	public String getNotificationDepartureId(){
		return _departureId;
	}

	public void setNotificationType(String type){
		if(NotificationType.B2I.name().equals(type)){
			_notificationType = NotificationType.B2I;
		}
		if(NotificationType.B2S.name().equals(type)){
			_notificationType = NotificationType.B2S;
		}
		if(NotificationType.O2I.name().equals(type)){
			_notificationType = NotificationType.O2I;
		}
		if(NotificationType.O2S.name().equals(type)){
			_notificationType = NotificationType.O2S;
		}
	}

	/**
     * Transforms the Notification into a String
     * 
     * @return the string corresponding to the Notification
     */

	@Override
	public String toString() {
		return (this._notificationType.name()) + "|" +_arrivalId;
	}
}