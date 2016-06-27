package br.unb.cic.sma.sade.fipa

object Performative extends Enumeration {
    type Performative = Value
    val ACCEPT_PROPOSAL = Value("accept-proposal")
    val AGREE = Value("agree")
    val CANCEL = Value("cancel")
    val CFP = Value("cfp")
    val CONFIRM = Value("confirm")
    val DISCONFIRM = Value("disconfirm")
    val FAILURE = Value("failure")
    val INFORM = Value("inform")
    val INFORM_IF = Value("inform-if")
    val INFORM_REF = Value("inform-ref")
    val NOT_UNDERSTOOD = Value("not-understood")
    val PROPAGATE = Value("propagate")
    val PROPOSE = Value("propose")
    val INFORMS = Value("informs")
    val QUERY_IF = Value("query-if")
    val QUERY_REF = Value("query-ref")
    val REFUSE = Value("refuse")
    val REJECT_PROPOSAL = Value("reject-proposal")
    val REQUEST = Value("request")
    val REQUEST_WHEN = Value("request-when")
    val REQUEST_WHENEVER = Value("request-whenever")
    val SUBSCRIBE = Value("subscribe")  
}