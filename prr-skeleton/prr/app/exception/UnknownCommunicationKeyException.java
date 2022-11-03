package prr.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** Exception for unknown terminals. */
public class UnknownCommunicationKeyException extends CommandException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	/** @param key Unknown terminal to report. */
	public UnknownCommunicationKeyException(int key) {
		super(Message.unknownCommunicationKey(key));
	}

}
