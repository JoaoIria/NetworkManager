package prr.core;

public class Notification{

	private NotificationType _notificationType;


	/**
	 * Constructor.
	 */

	public Notification(NotificationType type) {
		_notificationType = type;
	}


	/**
     * Gets the Notification Type
     * 
     * @return the Notification Type
     */

	public NotificationType getNotificationType(){
		return _notificationType;
	}

	/**
     * Transforms the Notification into a String
     * 
     * @return the string corresponding to the Notification
     */

	@Override
	public String toString() {
		return (this._notificationType.name());
	}
}