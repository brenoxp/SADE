package br.unb.cic.sma.sade.fipa

case class Refuse(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.REFUSE)
  override def performative = Performative.REFUSE  
}