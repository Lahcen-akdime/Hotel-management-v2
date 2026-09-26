package policy;

import Enums.PayementMethod;
import Model.Payement;

public interface PayementMethodStrategy {

    public void pay(Payement payement , PayementMethod payementMethod) ;

}
