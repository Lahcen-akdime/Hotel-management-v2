package policy.impl;

import Enums.PayementMethod;
import Model.Payement;
import policy.PayementMethodStrategy;

public class PayementByCash implements PayementMethodStrategy {

    @Override
    public void pay(Payement payement, PayementMethod payementMethod) {

    }
}
