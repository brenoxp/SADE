package br.unb.cic.sma.sade.fipa

case class NotUnderstood(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.NOT_UNDERSTOOD)
  override def performative = Performative.NOT_UNDERSTOOD  
}