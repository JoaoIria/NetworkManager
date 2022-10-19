package prr.core;

public class Notification{

	protected NotificationType _notificationType;

	/**
	 * Constructor.
	 */
	public Notification(NotificationType type) {
		_notificationType = type;
	}

	@Override
	public String toString() {
		return (this._notificationType.name());
	}
}