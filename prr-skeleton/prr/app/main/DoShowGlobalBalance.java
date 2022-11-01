package prr.app.main;

import java.lang.Math; 
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

  DoShowGlobalBalance(Network receiver) {
    super(Label.SHOW_GLOBAL_BALANCE, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command

    double debts = _receiver.getNetworkDebts();
    double payments = _receiver.getNetworkPayments();

    _display.popup(Message.globalPaymentsAndDebts(Math.round(payments),Math.round(debts)));

  }
}
