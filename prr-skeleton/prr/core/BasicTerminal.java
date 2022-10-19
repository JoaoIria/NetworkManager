package prr.core;

public class BasicTerminal extends Terminal{

    public BasicTerminal(String id, String clientId){ //não sei se precisa de ter o clientID
        super(id, clientId);
    }


/*
    @Override
    public String toString(){
        if (_friends == null){
            return("BASIC"+"|"+this._id+"|"+this._clientId+"|"+this._mode.name()+
            "|"+Double.valueOf(this._payments)+"|"+Double.valueOf(this._debt));
        }
        else{
            return("Temp");
        }
    }

    @Override
    public int compareTo(Terminal t){
        return Integer.valueOf(this._key).compareTo(Integer.valueOf(c._key));
    }
*/
}