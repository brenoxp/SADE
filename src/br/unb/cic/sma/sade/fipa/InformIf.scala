package br.unb.cic.sma.sade.fipa

case class InformIf(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.INFORM_IF)
  override def performative = Performative.INFORM_IF  
}