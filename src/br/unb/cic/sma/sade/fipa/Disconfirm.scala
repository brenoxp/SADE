package br.unb.cic.sma.sade.fipa

case class Disconfirm(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.DISCONFIRM)
  override def performative = Performative.DISCONFIRM  
}