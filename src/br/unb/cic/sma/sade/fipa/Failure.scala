package br.unb.cic.sma.sade.fipa

case class Failure(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.FAILURE)
  override def performative = Performative.FAILURE  
}