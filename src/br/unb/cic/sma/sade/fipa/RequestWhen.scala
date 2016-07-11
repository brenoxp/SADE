package br.unb.cic.sma.sade.fipa

case class RequestWhen(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.REQUEST_WHEN)
  override def performative = Performative.REQUEST_WHEN  
}