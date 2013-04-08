package miniprojekti;

import UI.GUI;
import controllers.Logic;
import controllers.LogicInterface;

public class App 
{
    public static void main( String[] args )
    {
        LogicInterface logic = new Logic();
        GUI gui = new GUI(logic);
        //testi
    }
}
