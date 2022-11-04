package prr.core;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

import prr.core.exception.SameClientKeyException;
import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.SameTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.UnkTerminalIdException;


/**
 * Class Network implements a serializable.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private List <Client> _clients;
  private List <Terminal> _terminals;
  private List <Communication> _comunications;
  private List <Notification> _waitingNotifications;

  /**
   * Constructor.
   */ 

  public Network(){
    _clients = new ArrayList<>();
    _terminals = new ArrayList<>();
    _comunications = new ArrayList<>();
    _waitingNotifications = new ArrayList<>();
  }

 /**
   * Register client on the network.
   * 
   * @param key "Client ID"
   * @param name "Client name"
   * @param taxNumber "Client Tax Number"
   * 
   * @throws SameClientKeyException if Client Id already exists
   * 
   * @return void
   */ 
  
  public void registerClient(String key, String name, int taxNumber) throws SameClientKeyException{
    Client _clientTemp = new Client(name, key ,taxNumber);
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        throw new SameClientKeyException();
      }
    }
    _clients.add(_clientTemp);
  }


  public void setCommunication(Communication c){
    _comunications.add(c);
  }

  public List<Communication> getAllCommunications(){
    List<Communication> comms = new ArrayList<>();
    for(Communication c: _comunications){
      comms.add(c);
    }
    return comms;
  }


 /**
   * Shows a client by his id.
   * 
   * @param key "Client ID"
   * 
   * @throws UnknownClientKeyException if Client Id doesn't exists
   * 
   * @return Client in String format
   */ 

  public String showClientById(String key) throws UnidentifiedClientKeyException {
  
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        return(c.toString());
      }  
    }throw new UnidentifiedClientKeyException();
  }


  public Client findClientById(String key) throws UnidentifiedClientKeyException{
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        return(c);
      }  
    }
    throw new UnidentifiedClientKeyException();
  }




  public void addWaitingNot(Notification n){
    _waitingNotifications.add(n);
  }


  public void changeClientStatus(Client c){

    switch(c.getClientLevel().name()){
      case("NORMAL"):{
        if((c.getPayments()-c.getDebts())>0){
          c.setClientLevel("GOLD");
          return;
        }
      }
      case("GOLD"):{
        if((c.getPayments()-c.getDebts())<0){
          c.setClientLevel("NORMAL");
          return;
        }
        else{
          for(Terminal t:c.getTerminalList()){
            if(t.getCommunications().size()>=5){
              List <Communication> tmp = t.getCommunications().subList(t.getCommunications().size()-5, t.getCommunications().size());
              int i=0;
              for(Communication c2: tmp){
                if(c2.getType().equals("VIDEO")){
                  i++;
                }
              }
              if(i == 5 && (c.getPayments()-c.getDebts())>0){
                c.setClientLevel("PLATINUM");
                }
              }
            }
          }
        }
        case("PLATINUM"):{
          if((c.getPayments()-c.getDebts())<0){
            c.setClientLevel("NORMAL");
            return;
          }
          for(Terminal t:c.getTerminalList()){
              if(t.getCommunications().size()>=2){
              List <Communication> tmp = t.getCommunications().subList(t.getCommunications().size()-5, t.getCommunications().size());
              int i=0;
              for(Communication c2: tmp){
                if(c2.getType().equals("TEXT")){
                  i++;
                }
              }
              if(i == 2 && (c.getPayments()-c.getDebts())>0){
                c.setClientLevel("GOLD");
              }
            }
          }
        }
      }
    }



 /**
   * Adds a notification to a specific terminal.
   * 
   * @param t "Terminal"
   * @param notification "notification"
   * 
   * @return notification in terminal t
   */ 
 /**
   * Clear all notifications from a specific terminal.
   * 
   * @param t "Terminal"
   * 
   * @return all notifications erased
   */ 

  public void clearNotifications(Terminal t){
    t.clearAllNotifications();
  }

  public void clearCLientNotifications(String key) throws UnidentifiedClientKeyException{
    for(Terminal t : findTerminalsListByCliendId(key)){
      clearNotifications(t);
    }
  }

 /**
   * Get all notifications from a specific terminal.
   * 
   * @param key "Terminal Client ID"
   * 
   * @return all notifications
   */ 

  public List<String> getNotifications(String key){


    List<String> getNots = new ArrayList<>();

    for(Terminal t: _terminals){
      if(t.getTerminalClientID().equals(key)){
        getNots.add(t.getNotificiations().toString());
        clearNotifications(t);
      }
    }
    return getNots;
  }


  public List<String> getNotificationsWaiting(String key){


    List<String> getNots = new ArrayList<>();

    for(Terminal t: _terminals){
      if(t.getTerminalClientID().equals(key)){
        getNots.add(t.getNotificiationsWaiting().toString());
        clearNotifications(t);
      }
    }
    return getNots;
  }

   /**
   * Show all clients from the network.
   * 
   * @return all clients
   */ 


  public List<String> showAllClients(){
    List<String> list = new ArrayList<>();
    Collections.sort(_clients);
    for (Client c: _clients){
      list.add(c.toString());
    }
    return list;
  }


 /**
   * Register client on the network.
   * 
   * @param mode "Terminal Mode"
   * @param idTerminal "Terminal ID"
   * @param idClient "Terminal Client ID"
   * 
   * @throws UnidentifiedClientKeyException if the client id doesn't exist
   * @throws SameTerminalKeyException if the terminal id already exists
   * @throws InvTerminalKeyException if the terminal id is invalid
   * @throws UnkTerminalIdException if terminal id doesn't exist
   * 
   * @return adds the terminal to the list of terminals in network
   * and to client list of terminals
   */ 


  public Terminal registerTerminal(String mode,String idTerminal,String idClient) throws 
  UnidentifiedClientKeyException, SameTerminalKeyException, InvTerminalKeyException,UnkTerminalIdException{

    if(idTerminal.length() != 6){
      throw new InvTerminalKeyException();
    }
    if(!idTerminal.matches("[0-9]+")){  /* Verifica se cada caracter pertence ao grupo de inteiros de 0 a 9  */
      throw new InvTerminalKeyException();
    }
    
    for(Terminal t: _terminals){
      if(t.getTerminalID().equals(idTerminal)){
        throw new SameTerminalKeyException();
      }
    }

    for(Client c: _clients){
      if(c.getClientID().equals(idClient)){
        switch(mode){
          case "BASIC":
            _terminals.add(new BasicTerminal(idTerminal, idClient));
            c.addTerminal(new BasicTerminal(idTerminal, idClient));
            return showTerminal(idTerminal);
          case "FANCY":
            _terminals.add(new FancyTerminal(idTerminal, idClient));
            c.addTerminal(new FancyTerminal(idTerminal, idClient));
            return showTerminal(idTerminal);
          default:
            return null;
        }
      } 
    }
    throw new UnidentifiedClientKeyException();
  }


   /**
   * Show the terminal by its id
   * 
   * @param idTerminal "Terminal ID"
   * 
   * @throws UnkTerminalIdException if terminal id doesn't exist
   * 
   * @return the terminal with the specific id
   */ 


  public Terminal showTerminal(String idTerminal) throws UnkTerminalIdException{
    for(Terminal t : _terminals){
      if(t.getTerminalID().equals(idTerminal)){
        return t;
      }
    }
    throw new UnkTerminalIdException();
  }


  public void TurnOffTerminal(String idTerminal) throws UnkTerminalIdException{
    showTerminal(idTerminal).turnOff();
  } 

  public void setTerminalIdle(String idTerminal) throws UnkTerminalIdException{
    Notification temp = null;
    switch(showTerminal(idTerminal).getTerminalMode().name()){
      case("OFF"):{
        if(_waitingNotifications != null){
          for(Notification n : _waitingNotifications){
            if(n.getNotificationType().name().equals("O2I") && n.getNotificationArrivalId().equals(idTerminal)){
              showTerminal(n.getNotificationDepartureId()).addNotification(n);
              temp = n;
            }
          }
          _waitingNotifications.remove(temp);
          showTerminal(idTerminal).setOnIdle();
          return;
        }
        else{
          showTerminal(idTerminal).setOnIdle();
          return;
        }
      }
      case("SILENCE"):{
        if(_waitingNotifications != null){
          for(Notification n : _waitingNotifications){
            if(n.getNotificationType().name().equals("S2I") && n.getNotificationArrivalId().equals(idTerminal)){
              showTerminal(n.getNotificationDepartureId()).addNotification(n);
              temp = n;
            }
          }
          _waitingNotifications.remove(temp);
          showTerminal(idTerminal).setOnIdle();
          return;
        }
        else{
          showTerminal(idTerminal).setOnIdle();
          return;
        }
      }
      case("BUSY"):{
        if(_waitingNotifications != null){
          for(Notification n : _waitingNotifications){
            if(n.getNotificationType().name().equals("B2I") && n.getNotificationArrivalId().equals(idTerminal)){
              showTerminal(n.getNotificationDepartureId()).addNotification(n);
              temp = n;
            }
          }
          _waitingNotifications.remove(temp);
          showTerminal(idTerminal).setOnIdle();
          return;
        }
        else{
          showTerminal(idTerminal).setOnIdle();
          return;
        }
      }
    }

  }

  public void setTerminalSilence(String idTerminal) throws UnkTerminalIdException{
    Notification temp=null;
    if(showTerminal(idTerminal).getTerminalMode().name().equals("OFF")){
      for(Notification n : _waitingNotifications){
        if(n.getNotificationType().name().equals("B2I") && n.getNotificationArrivalId().equals(idTerminal)){
          showTerminal(n.getNotificationDepartureId()).addNotification(n);
          temp = n;
          
        }
      }
      _waitingNotifications.remove(temp);
      showTerminal(idTerminal).setOnSilent();
      return;
      }
      else{
        showTerminal(idTerminal).setOnSilent();
        return;
      }
    }


   /**
   * Show all the terminals
   * 
   * @return the list of all terminals sorted
   */ 
  
  public List<String> showAllTerminals(){
    List<String> list = new ArrayList<>();
    Collections.sort(_terminals);
    for (Terminal t: _terminals){
      list.add(t.toString());
    }
    return list;
  }

   /**
   * Show all unused terminals
   * 
   * @return the list of all terminals 
   */

  public List<String> showUnusedTerminal(){
    List<String> list = new ArrayList<>();
    for(Terminal t : _terminals){
      if(t.getCommunications().isEmpty()){
        list.add(t.toString());
      }
    }
    return list;
  }

   /**
   * Adds a terminal friend to a terminal
   * 
   * @param s1 "Terminal ID from the one that will receive the friend"
   * @param s2 "Terminal ID from the one that will be added as a friend"
   */ 

  public void addFriend(String s1, String s2) throws InvTerminalKeyException,UnkTerminalIdException{

    if(s2.length() != 6){
      throw new InvTerminalKeyException();
    }
    if(!s2.matches("[0-9]+")){ 
      throw new InvTerminalKeyException();
    }
    for(Terminal t: _terminals){
      if (t.getTerminalID().equals(s1)){
        if(_terminals.contains(showTerminal(s2))){
          if(!t.getFriends().contains(s2)){
            t.getFriends().add(s2);
            return;
          }
          else{
            return;
          }
        }else{
          throw new UnkTerminalIdException();
        }
      }
    }throw new UnkTerminalIdException();
  }


  public void removeFriend(String s1, String s2) throws InvTerminalKeyException,UnkTerminalIdException{

    if(s2.length() != 6){
      throw new InvTerminalKeyException();
    }
    if(!s2.matches("[0-9]+")){ 
      throw new InvTerminalKeyException();
    }
    for(Terminal t: _terminals){
      if (t.getTerminalID().equals(s1)){
        if(_terminals.contains(showTerminal(s2))){
          if(t.getFriends().contains(s2)){
            t.getFriends().remove(s2);
            return;
          }
          else{
            return;
          }
        }else{
          throw new UnkTerminalIdException();
        }
      }
    }throw new UnkTerminalIdException();
  }


  public double getNetworkDebts(){
    
    double debts = 0;

    for(Terminal t: _terminals){
      debts += t.getTerminalDebts();
    } 

    return debts;
  }


  public double getNetworkPayments(){
    
    double payments = 0;

    for(Terminal t: _terminals){
      payments += t.getTerminalPayments();
    }

    return payments;
  }

  public void activateClientNotifications(String ClientID) throws UnidentifiedClientKeyException{
    for(Client c: _clients){
      if(c.getClientID().equals(ClientID)){
        c.activateNotificationsReception();
        return;
      }
    }throw new UnidentifiedClientKeyException();
  }

  public void desactivateClientNotifications(String ClientID) throws UnidentifiedClientKeyException{
    for(Client c: _clients){
      if(c.getClientID().equals(ClientID)){
        c.desactivateNotificationsReception();
        return;
      }
    }throw new UnidentifiedClientKeyException();
  }


  public boolean existsTerminal (String TerminalID){
    for(Terminal t: _terminals){
      if(t.getTerminalID().equals(TerminalID)){
        return true;
      }
    }return false;
  }





  public List<String> showAllCommunications(){
    List <String> communications = new ArrayList<>();
    for(Communication c: _comunications){
      communications.add(c.toString());
    }
    return communications;
  }


  /* PQ N FUNCIONA NO TERMINAL???????????????????????????????????????????? */
  public List<Communication> commsMadeByClient(String id){
    List<Communication> coms = new ArrayList<>();
    for(Communication c: _comunications){
      if(c.returnIDPartida().equals(id)){
        coms.add(c);
      }
    }
    return coms;
  }


  public List<Communication> commsReceivedByClient(String ClientID){
    List<Communication> coms = new ArrayList<>();
    for(Communication c: _comunications){
      if(c.returnIDChegada().equals(ClientID)){
        coms.add(c);
      }
    }
    return coms;
  }



  public List<String> showClientMadeComunications(String clientId) throws UnidentifiedClientKeyException{
    List <String> communications = new ArrayList<>();
    for(Terminal t: findTerminalsListByCliendId(clientId)){
      for(Communication c: commsMadeByClient(t.getTerminalID())){
        communications.add(c.toString());
      }
    }
    return communications;
  }



  public List<String> showClientReceivedComunications(String clientId) throws UnidentifiedClientKeyException{
    List <String> communications = new ArrayList<>();
    for(Terminal t: findTerminalsListByCliendId(clientId)){
      for(Communication c: commsReceivedByClient(t.getTerminalID())){
        communications.add(c.toString());
      }
    }
    return communications;
  }

  

  public List<String> showClientsWithDebts(){
    List <String> clientsWithDebts = new ArrayList<>();
    for(Client c : _clients){
      if (c.getDebts() > 0){
        clientsWithDebts.add(c.toString());
      }
    }
    Collections.sort(clientsWithDebts);
    return clientsWithDebts;
  }

  public List<String> showClientsWithoutDebts(){
    List <String> clientsNoDebts = new ArrayList<>();
    for(Client c : _clients){
      if (c.getDebts() == 0){
        clientsNoDebts.add(c.toString());
      }
    }
    Collections.sort(clientsNoDebts);
    return clientsNoDebts;
  }

  public List <String> showTerminalsWithPositiveBalance(){
    List <String> terminals = new ArrayList<>();
    for(Terminal t: _terminals){
      if(t.getTerminalPayments() - t.getTerminalDebts() > 0){
        terminals.add(t.toString());
      }
    }
    Collections.sort(terminals);
    return terminals;
  }




  public List<Terminal> findTerminalsListByCliendId(String clientID) throws UnidentifiedClientKeyException{
    return findClientById(clientID).getTerminalList();
  }
  



  public double getClientPayments(String ID) throws UnidentifiedClientKeyException{
    return findClientById(ID).getPayments();
  }



  public double getClientDebts(String ID) throws UnidentifiedClientKeyException{
    return findClientById(ID).getDebts();
  }




  public List<String> getOngoingCommunications(Terminal t){
    List <String> comms = new ArrayList<>();
    for(Communication c: t.getCommunications()){
      if(c.isOngoing()){
        comms.add(c.toString());
      }
    }
    return comms;
  }



  public void sendTextCommunication(Terminal t,String key, String msg) throws UnkTerminalIdException, UnidentifiedClientKeyException{
    Communication c = t.makeSMS(findClientById(t.getTerminalClientID()),showTerminal(key), msg);
    if(t.getFriends().contains(key)){
      findClientById(t.getTerminalClientID()).setDebtClient(c.getCost()/2);
      t.setDebtTerminal(c.getCost()/2);
      c.setCost(c.getCost()/2);
      _comunications.add(c);
      return;
    }
    else{
      findClientById(t.getTerminalClientID()).setDebtClient(c.getCost());
      t.setDebtTerminal(c.getCost());
      _comunications.add(c);
      return;
    }
  }



  public void sendVoiceCommunication(Terminal t,String key, int duration) throws UnkTerminalIdException, UnidentifiedClientKeyException{
    t.setInicialTerminalMode(t.getTerminalMode());
    showTerminal(key).setInicialTerminalMode(showTerminal(key).getTerminalMode());

    Communication c = t.makeVoiceCall(findClientById(t.getTerminalClientID()),showTerminal(key), duration);
    c.setStatus("ONGOING");
    c.setOnGoing(true);
    t.setOnBusy();
    showTerminal(key).setOnBusy();
    _comunications.add(c);
  }



  public void sendVideoCommunication(Terminal t,String key, int duration) throws UnkTerminalIdException, UnidentifiedClientKeyException{
    t.setInicialTerminalMode(t.getTerminalMode());
    showTerminal(key).setInicialTerminalMode(showTerminal(key).getTerminalMode());

    Communication c = t.makeVideoCall(findClientById(t.getTerminalClientID()),showTerminal(key), duration);
    c.setStatus("ONGOING");
    c.setOnGoing(true);
    t.setOnBusy();
    showTerminal(key).setOnBusy();
    _comunications.add(c);
  }

  public void endInteractiveCommunication(String s1, Terminal t,int duration) throws UnidentifiedClientKeyException,UnkTerminalIdException{
    
    switch(s1){
      case("VOICE"):{
        for(Communication c: _comunications){
          if(c.getType().equals("VOICE")){
            VoiceCommunication vc = (VoiceCommunication)c;
            if(c.getStatus().equals("ONGOING") && c.returnIDPartida().equals(t.getTerminalID())){
              c.setOnGoing(false);
              c.setDuration(duration);
              c.setStatus("FINISHED");
              vc.calculateVoiceCost(findClientById(t.getTerminalClientID()),duration);
              if(t.getFriends().contains(s1)){
                t.setDebtTerminal(c.getCost()/2);
                findClientById(t.getTerminalClientID()).setDebtClient(c.getCost()/2);
                c.setCost(c.getCost()/2);
              }
              else{
              t.setDebtTerminal(c.getCost());
              findClientById(t.getTerminalClientID()).setDebtClient(c.getCost());
              }
              t.setOnInicialTerminalMode(t.getInicialTerminalMode());
              showTerminal(c.returnIDChegada()).setOnInicialTerminalMode(showTerminal(c.returnIDChegada()).getInicialTerminalMode());
            }
          }
        }
      }case("VIDEO"):{
        for(Communication c: _comunications){
          if(c.getType().equals("VIDEO")){
            VideoCommunication vc = (VideoCommunication)c;
            if(c.getStatus().equals("ONGOING") && c.returnIDPartida().equals(t.getTerminalID())){
              c.setOnGoing(false);
              c.setDuration(duration);
              c.setStatus("FINISHED");
              vc.calculateVideoCost(findClientById(t.getTerminalClientID()),duration);
              if(t.getFriends().contains(s1)){
                t.setDebtTerminal(c.getCost()/2);
                findClientById(t.getTerminalClientID()).setDebtClient(c.getCost()/2);
                c.setCost(c.getCost()/2);
              }
              else{
              t.setDebtTerminal(c.getCost());
              findClientById(t.getTerminalClientID()).setDebtClient(c.getCost());
              }
              t.setOnInicialTerminalMode(t.getInicialTerminalMode());
              showTerminal(c.returnIDChegada()).setOnInicialTerminalMode(showTerminal(c.returnIDChegada()).getInicialTerminalMode());
            }
          }
        }
      }
    }
  }



  public void MakePayment(int id) throws UnidentifiedClientKeyException, UnkTerminalIdException{
    for(Communication c: _comunications){
      if(c.getIDComms() == id){
        showTerminal(c.returnIDPartida()).setPaymentTerminal(c.getCost());
        findClientById(showTerminal(c.returnIDPartida()).getTerminalClientID()).setPaymentClient(c.getCost());
        c.setPayment();
        changeClientStatus(findClientById(showTerminal(c.returnIDPartida()).getTerminalClientID()));
        return;
      }
    }
  }
  

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * 
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */

  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    try{
      parser.parseFile(filename);
    } catch(IOException | UnrecognizedEntryException e){
      throw e;
    } 
  }


    /**
   * @return Returns the network we are working in.
   */

  public Network getNetwork() {
    return this;
  }

}

