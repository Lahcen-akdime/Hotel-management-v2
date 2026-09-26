package policy.impl;

import Enums.PayementMethod;
import Model.Payement;
import policy.PayementMethodStrategy;

public class PayementByCard implements PayementMethodStrategy {

    @Override
    public void pay(Payement payement, PayementMethod payementMethod) {

    }

}
