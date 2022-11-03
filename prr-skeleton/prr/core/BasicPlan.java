package prr.core;

public class BasicPlan extends TariffPlan{
    
    public BasicPlan(String name){
        super(name);

    }


    public double calculateTextCost(Client c1, TextCommunication text){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                if(text.getSize() < 50){
                    return 10;
                }
                if(text.getSize() >= 50 && text.getSize() < 100){
                    return 16;
                }
                if(text.getSize() >= 100){
                    return 2*text.getSize();
                }
            }
            case("GOLD"):{
                if(text.getSize()< 50){
                    return 10;
                }
                if(text.getSize() >= 50 && text.getSize() < 100){
                    return 10;
                }
                if(text.getSize() >= 100){
                    return 2*text.getSize();
                }
            }
            case("PLATINUM"):{
                if(text.getSize() < 50){
                    return 0;
                }
                if(text.getSize() >= 50 && text.getSize() < 100){
                    return 4;
                }
                if(text.getSize() >= 100){
                    return 4;
                }
            }
        }
        return 0;
    }


    public double calculateVoiceCost(Client c1, VoiceCommunication text){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                return 20;
            }
            case("GOLD"):{
                return 10;
            }
            case("PLATINUM"):{
                return 10;
            }
        }
        return 0;
    }

    public double calculateVideoCost(Client c1, VideoCommunication text){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                return 30;
            }
            case("GOLD"):{
                return 20;
            }
            case("PLATINUM"):{
                return 10;
            }
        }
        return 0;
    }
}
