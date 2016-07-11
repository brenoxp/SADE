package br.unb.cic.sma.sade.fipa

case class Subscribe(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) extends ACLMessage(parameters: Map[ACLMessageParameter.ACLMessageParameter, Any]) {
  override def getParameters = (parameters - ACLMessageParameter.PERFORMATIVE) + (ACLMessageParameter.PERFORMATIVE -> Performative.SUBSCRIBE)
  override def performative = Performative.SUBSCRIBE  
}